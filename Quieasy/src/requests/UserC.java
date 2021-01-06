// class for user and account related operations such as registration, log in/out

package requests;

import application.ClientAgent;
import data.Message;
import data.RegisterData;
import data.LoginData;
import data.UserData;
import gui.Login;
import gui.PrimeScene;
import gui.Register;

public class UserC {
	
	private static UserC userC;
	private static Message request;
	private static Message response;
	
	// user data
	private String firstName;
	private String lastName;
	private String email;
	
	// constructor can only be accessed from within
	private UserC(String firstName, String lastName, String email) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		
	}
	
	// Gets the current instance -> Singleton.
	public static UserC getUser(String firstName, String lastName, String email) {
		
		if (userC == null) userC = new UserC(firstName, lastName, email);
		
		return userC;
		
	}
	
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
	
	// log user in
	public static void login(String email, String password) {

		ClientAgent clientAgent = ClientAgent.getClientAgent();
		request = new Message();
		request.task = "LOG_IN";
		request.loginData = new LoginData(email, password);

		response = new Message();
		response = clientAgent.sendAndWaitForResponse(request);
		if (response != null && response.status) {
			UserC.getUser(response.userData.getFirstName(), response.userData.getLastName(), response.userData.getEmail());

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
		userC = null;
	}

	//get the current user
	public static UserC getCurrentUser(){
		return userC;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}
}
