import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // don't need to specify a hostname, it will be the current machine
        ServerSocket ss = new ServerSocket(7777);
        System.out.println("ServerSocket awaiting connections...");
        int contador = 3;
        //Socket socket = new Socket();
        ArrayList<Socket> conexiones = new ArrayList<Socket>();
        ArrayList<List<Message>> infoConexiones = new ArrayList<List<Message>>();
      
        while (contador != 0) {
        	Socket socket = ss.accept();
	        conexiones.add( socket ); // blocking call, this will wait until a connection is attempted on this port.
	        System.out.println("Connection from " + socket + "!");
	
	        // get the input stream from the connected socket
	        InputStream inputStream = socket.getInputStream();
	        // create a DataInputStream so we can read data from it.
	        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
	
	        // read the list of messages from the socket
	        List<Message> listOfMessages = (List<Message>) objectInputStream.readObject();
	        infoConexiones.add(listOfMessages);
	        System.out.println("Received [" + listOfMessages.size() + "] messages from: " + socket);
	        // print out the text of every message
	        System.out.println("All messages:");
	        listOfMessages.forEach((msg)-> System.out.println(msg.getText()));
	
	       // System.out.println("Closing sockets.\n\n\n");
	        contador++;
        }
        
        //socket.close();
       
        mostrarMensajes(infoConexiones);
        cerrarConexiones(conexiones);
        
        
        ss.close();
    }
    
    public static void cerrarConexiones(  ArrayList<Socket> conexiones ) {
    	conexiones.forEach((e)-> {
			try {
				mostrarClientes(e);
				e.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
    }
    
    public static void mostrarMensajes( ArrayList<List<Message>> infoConexiones) {
    	for (List<Message> m: infoConexiones) 
    		for (Message e: m) 
    			System.out.println(e.getText());
    }
    
    public static void mostrarClientes( Socket s) {
    	System.out.println(s.toString());
    }
    
 }