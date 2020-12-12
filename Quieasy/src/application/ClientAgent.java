// Connects to the server, sends and receives message over network.
// Runs on a thread of its own.

package application;

import data.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientAgent implements Runnable{
	
	private static ClientAgent clientAgent;
	
	private Socket socket = null;
	private ObjectOutputStream out = null;
	private ObjectInputStream in = null;
	
	// constructor can only be accessed from within
	private ClientAgent() {
		
        String hostName = "localhost"; // server host
        int portNumber = 3000; // server port number
        
        try {
        	
            socket = new Socket(hostName, portNumber);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            System.out.println("Connected!");
            
        }catch (UnknownHostException e) {
        	
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
            
        } catch (IOException e) {
        	
            System.err.println("Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
            
        }
		
	}
	
	// Continuously listens for incoming messages.
	public void run() {
		
		Message message;
		
		try {
			
			while(true) {
				
				message = (Message) in.readObject();
				
				if(message != null) { // if message received
					
					// decode and execute received message
					ClientDecoder.decode(message);
					
				}
				
			}
			
		}catch(Exception e) {}
		
	}
	
	// Gets the current instance -> Singleton.
	public static ClientAgent getClientAgent() {
		
		if (clientAgent == null) clientAgent = new ClientAgent();
		
		return clientAgent;
		
	}
	
	// Send messages
	public void send(Message message) {
		
		try {
			System.out.println(message.task);
			
			out.writeObject(message);
			out.flush();
			
		}catch(Exception e) {
			
			e.printStackTrace();
			System.exit(1);
			
		}
		
	}
	
}
