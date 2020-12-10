// Starts the server and listens for incoming connections and messages.
// Receives and forwards message to the appropriate class.
// Sends back responses.

package application;

import java.io.*;
import java.net.*;

public class QuieasyServer {

    public static void main(String[] args) throws IOException {
        
        if (args.length != 1) {
            System.err.println("Usage: java QuieasyServer <port number>");
            System.exit(1);
        }
        
        int portNumber = Integer.parseInt(args[0]);
        
        try (ServerSocket serverSocket = new ServerSocket(portNumber)){
        	
        	System.out.println("Quieasy server running on port " + portNumber);
        	
        	int count = 1; // count clients connected
        	
        	while(true) {
        		
        		new QuieasyServerThread(serverSocket.accept()).start();
        		System.out.println("Client " + count + " connected!");
        		count++;
        	}
        	
        } catch (IOException e) {
        	
            System.err.println("Could not listen on port " + portNumber);
            System.out.println(e.getMessage());
            
        }
        
    }

}
