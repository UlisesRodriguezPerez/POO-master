import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client {

    public static void main(String[] args) throws IOException {

        // need host and port, we want to connect to the ServerSocket at port 7777
        Socket socket = new Socket("192.168.1.26", 7777);
        System.out.println("Connected!");

        // get the output stream from the socket.
        OutputStream outputStream = socket.getOutputStream();
        // create an object output stream from the output stream so we can send an object through it
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

        // make a bunch of messages to send.
        List<Message> messages = new ArrayList<>();

        
        messages.add(new Message(""));


        System.out.println("Sending messages to the ServerSocket");
        objectOutputStream.writeObject(messages);


        System.out.println("Closing socket and terminating program.");
        socket.close();
    
    }
}