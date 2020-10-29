// GUI for log in.

package dialog;

import javafx.scene.text.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.event.*;

import javafx.scene.paint.Color;

import application.Account;

public class Login extends GridPane {
	
	private static Login login;
	
	public final double WIDTH = 300;
	public final double HEIGHT = 275;
	
	private Text msg = new Text("Enter your account.");
	
	private TextField userTextField = new TextField();
	private PasswordField pwBox = new PasswordField();
	
	private Login() {
		super();
		
		this.setAlignment(Pos.CENTER);
		this.setHgap(10);
		this.setVgap(10);
		this.setPadding(new Insets(25, 25, 25, 25));
		
		Text scenetitle = new Text("Welcome");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		this.add(scenetitle, 0, 0, 2, 1);

		Label userName = new Label("User Name:");
		this.add(userName, 0, 1);

		//TextField userTextField = new TextField();
		this.add(userTextField, 1, 1);

		Label pw = new Label("Password:");
		this.add(pw, 0, 2);

		//PasswordField pwBox = new PasswordField();
		this.add(pwBox, 1, 2);
		
		Button btn = new Button("Log in");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btn);
		this.add(hbBtn, 1, 4);
		
        this.add(msg, 1, 6);
		
		btn.setOnAction(new EventHandler<ActionEvent>() {
			 
		    public void handle(ActionEvent e) {
		        
		    	Account.logIn(userTextField.getText(), pwBox.getText());
		    	
		    }
		    
		});
		
	}
	
	// Gets the current instance -> Singleton.
	public static Login getLogin() {
		
		if (login == null) login = new Login();
		
		return login;
		
	}
	
	// Show "Login failed!" on unsuccessful log in attempt.
	public void fail() {
		
		userTextField.setText("");
		pwBox.setText("");
		msg.setFill(Color.FIREBRICK);
	    msg.setText("Failed! Please try again.");
	    
	}
	
}
