// Starts the server and listens for incoming connections and messages.
// Receives and forwards message to the appropriate class.
// Sends back responses.

package application;

import java.io.*;
import java.net.*;

public class QuieasyServer {
	
	private static ServerSocket serverSocket = null;
	private static Socket clientSocket = null;
	private static ObjectOutputStream out = null;
	private static ObjectInputStream in = null;

    public static void main(String[] args) throws IOException {
        
        if (args.length != 1) {
            System.err.println("Usage: java EchoServer <port number>");
            System.exit(1);
        }
        
        int portNumber = Integer.parseInt(args[0]);
        
        try {
        	serverSocket = new ServerSocket(Integer.parseInt(args[0]));
        	
        	System.out.println("Quieasy server running on port " + portNumber);
        	
        	clientSocket = serverSocket.accept();
        	out = new ObjectOutputStream(clientSocket.getOutputStream());
        	in = new ObjectInputStream(clientSocket.getInputStream());
        	
        	System.out.println("Client connected!");
        	
        	
        	// Listen for incoming messages.
        	Message message;
        	
			while(true) {
				message = (Message) in.readObject();
				if(message != null) {
					System.out.println("Message received!");
					out.writeObject(ServerDecoder.decode(message));
					out.flush();
				}
			}
        
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }catch (Exception e) {}
        
    }

}
