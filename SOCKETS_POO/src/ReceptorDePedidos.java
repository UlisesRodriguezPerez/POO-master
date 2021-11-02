import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class ReceptorDePedidos implements Runnable {

	int puerto = 7777;
	ArrayList<Socket> conexiones = new ArrayList<Socket>();
	ArrayList<List<Message>> infoConexiones = new ArrayList<List<Message>>(); // cambiar tipo de dato
	InputStream flujoEntrada;
	ObjectInputStream flujoEntradaObjeto;
	ServerSocket ss;
	boolean conectado = false;
	
	
	@Override
	public void run() {
		try {
			ss = new ServerSocket(puerto);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "...No se ha podido abrir el puerto: "
					+puerto+" para la conexión del servidor...");
			this.conectado = false;
			//this.finalize(); // cerrar hilo
		}
		
		while (conectado) {
        	try {
        		Socket socket = new Socket();
        		socket = ss.accept();
            	conexiones.add( socket ); 
    	        System.out.println("Conexion recibida: "+socket.toString());
            	
    	        flujoEntrada = socket.getInputStream();
            	flujoEntradaObjeto = new ObjectInputStream(flujoEntrada);
        		
//            	Pedido nuevoPedido = null;
//            	nuevoPedido = (Pedido) objectInputStream.readObject();
//            	socket.close();
            	
        	} catch (IOException io) {
        		io.printStackTrace();
        	}
	      
		}
	}

//	public void agregarPedido (Pedido nuevoPedido) {
//		
//	}
	
}
