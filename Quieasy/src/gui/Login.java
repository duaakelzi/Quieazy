package gui;

import javafx.scene.text.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import requests.UserRequests;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;

/**
 * GUI for logging the user in.
 */
public class Login extends VBox{
	
	private static Login login; // singleton
	
	private static TextField email = new TextField(); // email input field
	private static PasswordField password = new PasswordField(); // password input field
	private static Text msg = new Text(""); // text message to the user by the system

	/**
	 * Private constructor.
	 */
	private Login() {
		
		super();
		
		this.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(25, 25, 25, 25));
		this.setSpacing(10);
		
		Text title = new Text("Log in");
		title.setFont(new Font(20));
		VBox.setMargin(title, new Insets(0, 0, 5, 0));
		
		email.setPromptText("Email address");
		email.setMaxWidth(200);
		
		password.setPromptText("Enter password");
		password.setMaxWidth(200);
		
		Button loginButton = new Button("Log in");
		
		HBox hbox = new HBox();
		hbox.setSpacing(10);
		hbox.setAlignment(Pos.CENTER);
		
		Text question = new Text("Not registered yet?");
		
		Button registerButton = new Button("Create account");
		
		hbox.getChildren().addAll(question, registerButton);
		
		this.getChildren().addAll(title, email, password, loginButton, msg, hbox);
		
		loginButton.setOnAction(new EventHandler<ActionEvent>() {
			 
		    public void handle(ActionEvent e) {

		    	UserRequests.login(email.getText(), password.getText());

		    }
		    
		});
		
		registerButton.setOnAction(new EventHandler<ActionEvent>() {
			 
		    public void handle(ActionEvent e) {
		        
		    	switchToRegister();
		    	
		    }
		    
		});
		
	}

	/**
	 * Gets the single instance of this class.
	 * @return The single instance of this class.
	 */
	public static Login getLogin() {
		
		if (login == null) login = new Login();
		
		return login;
		
	}

	/**
	 * Prompt user to try again on unsuccessful login attempt.
	 */
	public static void fail() {
		
		clear();
		msg.setFill(Color.FIREBRICK);
	    msg.setText("Incorrect email or password! Please try again.");
	    
	}

	/**
	 * Switch to create account view.
	 */
	private static void switchToRegister() {
		clear();
		PrimeScene.register();
	}

	/**
	 * Clear all user input and system message.
	 */
	public static void clear() {
		email.setText("");
		password.setText("");
		msg.setText("");
	}
	
}