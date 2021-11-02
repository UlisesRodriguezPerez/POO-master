package S1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	
	public static void main(String args[]) throws IOException {
		
		// Servidor
		ServerSocket ss = new ServerSocket(12345);
		Socket s = ss.accept();
		ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
		Objeto objeto = null;
		try {
			objeto = (Objeto) ois.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(objeto);
		ss.close();
		
	}

}
