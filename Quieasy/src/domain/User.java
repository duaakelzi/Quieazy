// class for user and account related operations such as registration, log in/out

package domain;

import application.ClientAgent;
import data.Message;
import data.RegisterData;
import data.LoginData;

public class User {
	
	private static User user;
	
	// user data
	private String firstName;
	private String lastName;
	private String email;
	
	// constructor can only be accessed from within
	private User(String firstName, String lastName, String email) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		
	}
	
	// Gets the current instance -> Singleton.
	public static User getUser(String firstName, String lastName, String email) {
		
		if (user == null) user = new User(firstName, lastName, email);
		
		return user;
		
	}
	
	// create a new account
	public static void register(String firstName, String lastName, String email, String password) {
		
		ClientAgent clientAgent = ClientAgent.getClientAgent();
		Message message = new Message();
		message.task = "REGISTER";
		message.registerData = new RegisterData(firstName, lastName, email, password);
		clientAgent.send(message);
		
	}
	
	// log user in
	public static void login(String email, String password) {
		
		ClientAgent clientAgent = ClientAgent.getClientAgent();
		Message message = new Message();
		message.task = "LOG_IN";
		message.loginData = new LoginData(email, password);
		clientAgent.send(message);
		
	}
	
	// log user out
	public static void logout() {
		user = null;
	}
	
}
