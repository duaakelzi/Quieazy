// class for user and account related operations such as registration, log in/out

package requests;

import application.ClientAgent;
import data.Message;
import data.RegisterData;
import data.LoginData;
import data.UserData;
import gui.Login;
import gui.PlayQuizBox;
import gui.PrimeScene;
import gui.Register;
import javafx.scene.paint.Color;

public class UserRequests {
	
	private static UserData user;
	private static Message request;
	private static Message response;
	
	// Gets the current instance -> Singleton.
	public static UserData getUser(String firstName, String lastName, String email) {
		
		if (user == null) user = new UserData(firstName, lastName, email);
		
		return user;
	}
	/**
	 * this method to make a request to register a new user in the server
	 * then get the response from the server that telling us if the user is registered successfully or not
	 */
	// create a new account
	public static void register(String firstName, String lastName, String email, String password) {
		
		ClientAgent clientAgent = ClientAgent.getClientAgent();
		request = new Message();
		request.task = "REGISTER";
		request.registerData = new RegisterData(firstName, lastName, email, password);

		response = new Message();
		response = clientAgent.sendAndWaitForResponse(request);
		if (response != null && response.status) {
			//this leads to direct login, whereas user should first be informed that registration
			// was successful and be redirected to the login page again??
			Register.clear();

			// show dash board
			PrimeScene.home();
		}else if (response != null && (!response.status)){
			Register.emailInUse();
		}
		
	}
	/**
	 * this method to make a request to log in the user
	 * then get the response from the server to know if the user has an account to let him
	 * log into the app
	 */
	// log user in
	public static void login(String email, String password) {

		ClientAgent clientAgent = ClientAgent.getClientAgent();
		request = new Message();
		request.task = "LOG_IN";
		request.loginData = new LoginData(email, password);

		response = new Message();
		response = clientAgent.sendAndWaitForResponse(request);
		if (response != null && response.status) {
			UserRequests.getUser(response.userData.getFirstName(), response.userData.getLastName(), response.userData.getEmail());

			// clear user inputs
			Login.clear();

			// show dash board
			PrimeScene.home();


		} else if (response != null && (!response.status)) { // user entered incorrect email or password
			Login.fail();
		}
	}

	// log user out
	public static void logout() {
		user = null;
	}

	//get the current user
	public static UserData getCurrentUser(){
		return user;
	}

	public String getFirstName() {
		return user.getFirstName();
	}

	public String getLastName() {
		return user.getLastName();
	}

	public String getEmail() {
		return user.getEmail();
	}
}
