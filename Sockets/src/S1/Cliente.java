package S1;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Cliente {

	public static void main(String args[]) throws IOException {
		//Cliente
		Socket s = new Socket("localhost", 12345);
		ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
		Objeto objeto = new Objeto();
		objeto.x = 10;
		objeto.y = " teste";
		oos.writeObject(objeto);
		s.close();
	}
	
}
