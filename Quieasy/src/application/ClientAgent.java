// Connects to the server, sends and receives message over network.
// Runs on a thread of its own.

package application;

import java.io.*;
import java.net.*;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import data.Message;

public class ClientAgent implements Runnable {

	private static ClientAgent clientAgent;

	private Socket socket = null;
	private ObjectOutputStream out = null;
	private ObjectInputStream in = null;
	private Queue<Message> messageQueue = new LinkedList<>(); //maybe another DT would be more suitable; thread-safe and what happens with irrelevant messages (they might be waiting for another task to be picked up)?
	private final int MAX_RETRIES = 20;

	// constructor can only be accessed from within
	private ClientAgent() {

		String hostName = "localhost"; // server host
		int portNumber = 3000; // server port number

		try {

			socket = new Socket(hostName, portNumber);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			System.out.println("Connected!");

		} catch (UnknownHostException e) {

			System.err.println("Don't know about host " + hostName);
			System.exit(1);

		} catch (IOException e) {

			System.err.println("Couldn't get I/O for the connection to " + hostName);
			System.exit(1);

		}

	}

	// Continuously listens for incoming messages. Redesigned for the following reasons:
	// (-) Forces user to work in async way.
	// (-) Interferes with the JavaFX threads, that will lead to more complicated approaches.
	// (-) Response + request are separated
	// (-) Server responses unexpected by client --> may lead to incorrect behavior
	// (-) Server responses arrived unexpectedly
	// (-) Less and more complicated controls over data transmission between client-server.
	// (-) Tight coupling of Client-Decoder with the UI.
	public void run() {

		Message message;

		try {

			while (true) {
				System.out.println("Run method entered.."); //for testing
				message = (Message) in.readObject();
				System.out.println("ClientAgent received a message"); //for testing
				if (message != null) { // if message received
					System.out.println("Non-null message received.. "); //for testing
					// decode and execute received message
					messageQueue.add(message);
					// call the method that would read the message task and see if it's relevant
					System.out.println("Message task: " + message.task);// for testing only
				}

			}

		} catch (Exception e) {
			System.out.printf("Error occured in ClientAgent run. Exiting..");
			System.out.println(e.getMessage());
		}

	}

	// Gets the current instance -> Singleton.
	public static ClientAgent getClientAgent() {

		if (clientAgent == null) clientAgent = new ClientAgent();

		return clientAgent;

	}

	//user request sent here and processed further in private methods until relevant response returned
	public Message sendAndWaitForResponse(Message message) {
		send(message);
		return waitForResponse(message); //either relevant message or null
	}

	// Send messages
	private void send(Message message) {

		try {
			System.out.println("Message sent: " + message.task);

			out.writeObject(message);
			out.flush();
		} catch (Exception e) {

			e.printStackTrace();
			System.exit(1);

		}
	}
	//call receiveResponse() until the relevant response is there or the max_retries is reached
	private Message waitForResponse(Message request){

		Message result = null;
		for(int i = 0; i <= MAX_RETRIES && result == null; i++) {
			System.out.println("Retries loop entered " + (i+1) + " time(s)");
			result = receiveResponse(request);
			if(result == null) {
				try{
					Thread.sleep(400);
				}catch(InterruptedException ie) {}
			}
		}

		return result;
	}

	//checks the queue and see if message is relevant
	private Message receiveResponse(Message request) {
		//check if relevant messages there
		Message result = null;
		for (Message msg : messageQueue) {
			//if relevant positive or negative response, return the message to the sendAndWaitForResponse
			if (msg.task.equals(request.task)) {
				result = msg; // to check later: what happens if the message isn't successful?
 				//should remove the specific message from the queue since it's relevant
			}
		}
		if(result != null) {
			messageQueue.remove(result);
		}
		return result;
	}


}
