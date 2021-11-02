package modelo;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import controlador.Cliente;
import vista.VistaCliente;

public class ReceptorDeCatalogo extends Thread implements Observable {

	private Cliente cliente;
	private final int PUERTO = 7777;
	private boolean conectado;
	public VistaCliente vista;
	
	public ArrayList<Observador> observadores;
	
	public ReceptorDeCatalogo(Cliente cliente) {
		this.cliente = cliente;
		conectado = false;
		observadores = new ArrayList<Observador>();
	}
	
	public void esperarCatalogo() throws IOException, ClassNotFoundException {
		
	        ServerSocket ss = new ServerSocket(PUERTO);
	        System.out.println("Esperando conexiones");
	        ArrayList<Socket> conexiones = new ArrayList<Socket>();
	        conectado = true;
	        
	        while (conectado) {
	        	Socket socket = ss.accept();
		        conexiones.add( socket ); 
		        System.out.println("Conexion: " + socket );
		
		        InputStream inputStream = socket.getInputStream();
		        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
		
		        Catalogo catalogo = (Catalogo) objectInputStream.readObject();
		        cliente.setCatalogo(catalogo);
		        this.vista.catalogo = catalogo;
		        this.vista.cargarCatalogo();
		        notificar();
	        }
	       
	        System.out.println("...La conexión se ha cerrado...");
	        ss.close();
	    
	}
	
	
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public boolean isConectado() {
		return conectado;
	}

	public void setConectado(boolean conectado) {
		this.conectado = conectado;
	}

	public ArrayList<Observador> getObservadores() {
		return observadores;
	}

	public void setObservadores(ArrayList<Observador> observadores) {
		this.observadores = observadores;
	}

	public int getPUERTO() {
		return PUERTO;
	}

	public void desconectar() {
		if (conectado)
			conectado = !conectado;
	}
	
	@Override
	public void notificar() {
		this.observadores.forEach((o)->o.actualizar());
	}

	@Override
	public void enlazarObservador(Observador o) {
		this.observadores.add(o);
	}

	@Override
	public void run() {
		
		try {
			conectado = true;
			esperarCatalogo();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conectado = false;
		}
		
	}

}
