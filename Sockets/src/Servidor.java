import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	
	
	
	public static void main(String []args) throws IOException {
		
		int puerto = 4000;
		ServerSocket socketServidor;
		socketServidor = new ServerSocket(puerto);
		System.out.println("--iniciando servidor---");
		
		
		while (true ) {
			try {
				Socket cliente = new Socket();
				cliente = socketServidor.accept();
				HiloServidor hilo = new HiloServidor(cliente);
				hilo.start();
				System.out.println("...thread is running...");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		
		
		
	}

}
