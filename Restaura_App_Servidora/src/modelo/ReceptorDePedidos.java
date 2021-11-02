package modelo;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ReceptorDePedidos extends Thread implements Observable {

	private final int puerto = 7777;
	private boolean conectado;
	public ArrayList<Pedido> pedidos;
	public ArrayList<Observador> observadores;
	public ObjectInputStream flujoEntradaObjeto;
	public ServerSocket ss;
	public InputStream flujoEntrada;
	
	public ReceptorDePedidos(ArrayList<Pedido> pedidosAdministrador) {
		this.pedidos = pedidosAdministrador;
		this.conectado = false;
		this.observadores = new ArrayList<Observador>();
	}
	
	@Override
	public void run() {
		try {
			ss = new ServerSocket(puerto);
			this.conectado = true;
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "...No se ha podido abrir el puerto: "+puerto+"...");
			this.conectado = false;	
		}
		
		while (conectado) {
        	try {
        		Socket socket;
        		System.out.println("Esperando pedidos...");
        		socket = ss.accept();
    	        System.out.println("Conexion recibida: "+socket.toString());
            	
    	        flujoEntrada = socket.getInputStream();
            	flujoEntradaObjeto = new ObjectInputStream(flujoEntrada);
        		Pedido_ pedido = (Pedido_) flujoEntradaObjeto.readObject();
            	
            	if (pedido.modo == Pedido_.MODO.EXPRESS) {
            		PedidoExpress.cantidad++;
            		this.pedidos.add(pedido.express);
            	} else if (pedido.modo == Pedido_.MODO.SITIO) {
            		PedidoSitio.cantidad++;
            		this.pedidos.add(pedido.sitio);
            	} else if (pedido.modo == Pedido_.MODO.RECOGER) {
            		PedidoRecoger.cantidad++;
            		this.pedidos.add(pedido.recoger);
            	} else {
            		System.out.println("CASTTEEEOO");
            	}
            	System.out.println("...cerrando conexión :"+socket.toString()+"...");
            	socket.close();
            	
        	} catch (IOException io) {
        		io.printStackTrace();
        		conectado = false;
        	} catch (ClassNotFoundException cnf) {
        		cnf.printStackTrace();
        		conectado = false;
        	}
		}
	}

	public void agregarPedido (Pedido nuevoPedido ) {
		this.pedidos.add( nuevoPedido );
	}
	
	public ArrayList<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(ArrayList<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	@Override
	public void notificar() {
		for (Observador o: observadores)
			o.actualizar();
	}

	@Override
	public void enlazarObservador(Observador o) {
		this.observadores.add(o);
	}
	
}