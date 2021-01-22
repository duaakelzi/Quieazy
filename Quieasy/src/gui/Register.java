package gui;

import requests.UserRequests;

import javafx.scene.text.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * GUI for creating a new account.
 */
public class Register extends VBox{
	
	private static Register register; // singleton

	// input fields for user details
	private static TextField firstName = new TextField();
	private static TextField lastName = new TextField();
	private static TextField email = new TextField();
	private static PasswordField password1 = new PasswordField();
	private static PasswordField password2 = new PasswordField();
	
	private static Text msg = new Text(""); // text message to the user by the system

	/**
	 * Private constructor.
	 */
	private Register() {
		
		super();
		
		this.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(25, 25, 25, 25));
		this.setSpacing(10);
		
		Text title = new Text("Create a New Account");
		title.setFont(new Font(20));
		VBox.setMargin(title, new Insets(0, 0, 5, 0));
		
		firstName.setPromptText("First name");
		firstName.setMaxWidth(200);
		
		lastName.setPromptText("Last name");
		lastName.setMaxWidth(200);
		
		email.setPromptText("Email address");
		email.setMaxWidth(200);
		
		password1.setPromptText("Create password");
		password1.setMaxWidth(200);
		
		password2.setPromptText("Repeat password");
		password2.setMaxWidth(200);
		
		Button registerButton = new Button("Create my account");
		
		HBox hbox = new HBox();
		hbox.setSpacing(10);
		hbox.setAlignment(Pos.CENTER);
		
		Text question = new Text("Already have an account?");
		
		Button loginButton = new Button("Log in");
		
		hbox.getChildren().addAll(question, loginButton);
		
		this.getChildren().addAll(title, firstName, lastName, email, password1, password2, registerButton, msg, hbox);
		
		registerButton.setOnAction(new EventHandler<ActionEvent>() {
			 
		    public void handle(ActionEvent e) {
		    	
		    	validate(firstName.getText(), lastName.getText(), email.getText(), password1.getText(), password2.getText());
		    	
		    }
		    
		});
		
		loginButton.setOnAction(new EventHandler<ActionEvent>() {
			 
		    public void handle(ActionEvent e) {
		    	
		    	switchToLogin();
		    	
		    }
		    
		});
		
	}

	/**
	 * Gets the single instance of this class.
	 * @return The single instance of this class.
	 */
	public static Register getRegister() {
		
		if (register == null) register = new Register();
		
		return register;
		
	}

	/**
	 * Validate user input and create account.
	 * @param firstName User first name.
	 * @param lastName User last name.
	 * @param email User email.
	 * @param password1 User password.
	 * @param password2 User password repeated.
	 */
	private void validate(String firstName, String lastName, String email, String password1, String password2) {

		boolean valid = true;
		String errorMessage = "";

		String regex = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);

		if(firstName.equals("")){

			errorMessage = "First name required!";
			valid = false;

		}else if(lastName.equals("")){

			errorMessage = "Last name required!";
			valid = false;

		}else if(email.equals("")){

			errorMessage = "Email required!";
			valid = false;

		}else if(!matcher.matches()){

			errorMessage = "Invalid email!";
			valid = false;

		}else if(password1.equals("")){

			errorMessage = "Password required!";
			valid = false;

		}else if(password2.equals("")){

			errorMessage = "Repeat password!";
			valid = false;

		}else if(!password1.equals(password2)){

			errorMessage = "Passwords don't match!";
			valid = false;

		}

		if(valid){

			UserRequests.register(firstName, lastName, email, password1);

		}else{

			msg.setFill(Color.FIREBRICK);
			msg.setText(errorMessage);

		}
		
	}

	/**
	 * Show "Email already exists!" if already registered email is used to create a new account.
	 */
	public static void emailInUse() {
		
		password1.setText("");
		password2.setText("");
		msg.setFill(Color.FIREBRICK);
		msg.setText("Registration failed! Email already exists.");
		
	}

	/**
	 * Switch to account log in view.
	 */
	private static void switchToLogin() {
		clear();
		PrimeScene.login();
	}

	/**
	 * Clear all user input and system message.
	 */
	public static void clear() {
		firstName.setText("");
		lastName.setText("");
		email.setText("");
		password1.setText("");
		password2.setText("");
		msg.setText("");
	}
	
}
