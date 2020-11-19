// Menu with a list of options

package dialog;

import domain.User;

import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;

public class Menu extends VBox {

	private static Menu menu;
	
	private static final double BTN_WIDTH = 100;
	private static final double BTN_HEIGHT = 20;
	
	private static Button profileButton = new Button("Profile");
	private static Button myQuizButton = new Button("My Quiz");
	private static Button notifButton = new Button("Notifications");
	private static Button settingsButton = new Button("Settings");
	private static Button helpButton = new Button("Help");
	private static Button logoutButton = new Button("Log out");
	
	// constructor can only be accessed from within
	private Menu() {
		super(8);
		this.setPadding(new Insets(15, 12, 15, 12));
		profileButton.setPrefSize(BTN_WIDTH, BTN_HEIGHT);
		myQuizButton.setPrefSize(BTN_WIDTH, BTN_HEIGHT);
		notifButton.setPrefSize(BTN_WIDTH, BTN_HEIGHT);
		settingsButton.setPrefSize(BTN_WIDTH, BTN_HEIGHT);
		helpButton.setPrefSize(BTN_WIDTH, BTN_HEIGHT);
		logoutButton.setPrefSize(BTN_WIDTH, BTN_HEIGHT);
		
		this.getChildren().addAll(profileButton, myQuizButton, notifButton, settingsButton, helpButton,logoutButton);
		
		logoutButton.setOnAction(new EventHandler<ActionEvent>() {
			 
		    public void handle(ActionEvent e) {
		    	
		    	User.logout();
		    	PrimeScene.login();
		    }
		    
		});
		
	}
	
	// Gets the current instance -> Singleton
	public static Menu getMenu() {
		
		if (menu == null) menu = new Menu();
		return menu;
		
	}
	
}
