import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class HiloServidor extends Thread {
	
	BufferedReader entrada;
	PrintStream salida;
	Socket socket = null;
	
	public HiloServidor(Socket socket) {
		this.socket = socket;
		try {
			salida = new PrintStream(this.socket.getOutputStream(), true);
			entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		String cadena = "";
		while (!cadena.trim().equals("*")) {
			System.out.println("COMUNICO CON: "+socket.toString());
		
			try {
				cadena = entrada.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if (cadena != null)
				salida.println(cadena.trim().toUpperCase());


			
		}
		System.out.println("FIN CON:"+socket.toString());
		
		try {
			entrada.close();
			salida.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

}
