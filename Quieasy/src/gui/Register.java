// Dialog for creating a new account

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

public class Register extends VBox{
	
	private static Register register;
	
	private static TextField firstName = new TextField();
	private static TextField lastName = new TextField();
	private static TextField email = new TextField();
	private static PasswordField password1 = new PasswordField();
	private static PasswordField password2 = new PasswordField();
	
	private static Text msg = new Text("");
	
	// constructor can only be accessed from within
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
	
	// Gets the current instance -> Singleton.
	public static Register getRegister() {
		
		if (register == null) register = new Register();
		
		return register;
		
	}
	
	// validate input and register
	private void validate(String firstName, String lastName, String email, String password1, String password2) {
		
		boolean valid = true;
		String errorMessage = "";
		
		/*
		 * VALIDATION: will be implemented latter.
		 */
		
		if(valid) UserRequests.register(firstName, lastName, email, password1);
		
	}
	
	// Gets the current instance -> Singleton.
	public static void emailInUse() {
		
		password1.setText("");
		password2.setText("");
		msg.setFill(Color.FIREBRICK);
		msg.setText("Registration failed.");
		
	}
	
	private static void switchToLogin() {
		clear();
		PrimeScene.login();
	}
	
	public static void clear() {
		firstName.setText("");
		lastName.setText("");
		email.setText("");
		password1.setText("");
		password2.setText("");
		msg.setText("");
	}
	
}
