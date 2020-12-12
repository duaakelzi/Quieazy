// Server thread handles individual connections.

package application;

import data.Message;

import java.net.*;
import java.io.*;

public class QuieasyServerThread extends Thread {
	
	private Socket socket = null;

    public QuieasyServerThread(Socket socket) {
        super("QuieasyServerThread");
        this.socket = socket;
    }
    
    public void run() {

        try (
        		ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        		ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        		
        ) {
        	
        	// Listen for incoming messages.
        	Message message = null;

			while(true) {
				
				message = (Message) in.readObject();


				
				if(message != null) {
					System.out.println("server "+message.task);
					System.out.println("Message received!");

					out.writeObject(ServerDecoder.decode(message));
					out.flush();
					System.out.println("Response sent!");
					
				}
				else{System.out.println("server message null");}
			}
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
        	
        }
        
    }

}
