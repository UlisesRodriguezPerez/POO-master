import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	
	private ServerSocket socketServidor;
	private int puerto = 4000;
	
	public Servidor() {
		try  {
			// inicia el servidor con el puerto por defecto
			this.socketServidor = new ServerSocket(puerto);
		
		} catch (IOException e) {
			e.printStackTrace(); // muestra el mensaje de error de entrada
		}
	}
	
	public void mantenerEscuchando()  {
		while (true) {
			try {
				Socket cliente = this.socketServidor.accept();
				new Thread(new Cliente(cliente));
				System.out.println(":::Nuevo Usuario:::");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
