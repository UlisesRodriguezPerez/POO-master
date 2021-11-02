import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Cliente implements Runnable {

	private Socket socket;
	private DataInputStream entrada;
	private DataOutputStream salida;
	
	public Cliente (Socket socket) {
		this.socket = socket;
		try {
			this.entrada = new DataInputStream(this.socket.getInputStream());
			this.salida = new DataOutputStream(this.socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		boolean conectado = true;
		while (conectado) {
			
		}
		
	}

	
	

}
