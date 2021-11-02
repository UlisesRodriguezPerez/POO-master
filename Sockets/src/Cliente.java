import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {

	public static void main(String args[]) throws UnknownHostException, IOException{
		String host = "127.0.0.1";
		int puerto = 4000;
		
		Socket cliente = new Socket(host, puerto);
		PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true);
		BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); // entrada desde teclado
		
		String cadena = "";
		String eco = "";
		System.out.println("Introduce cadena: ");
		cadena = in.readLine();

		while(!cadena.equals("*")) {
			salida.println(cadena);
			eco = entrada.readLine();
			System.out.println("Introduce cadena: ");
			cadena = in.readLine();
		}
	
		in.close();
		entrada.close();
		salida.close();
		cliente.close();
		System.out.println("Fin de conexion");
	
	}
	
	
}
