// Handles tasks related to user account such as log in and log out.

package application;

public class Account {
	
	private Account() {}
	
	// Log in
	public static void logIn(String userName, String passWord) {
		
		ClientAgent clientAgent = ClientAgent.getClientAgent();
		clientAgent.send(new Message("LOG_IN", userName, passWord));
		
	}
	
	// Log out
	public static void logOut() {}
	
}
