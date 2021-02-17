package gui;

import javafx.scene.layout.*;

/**
 * GUI for home page of the app when the user is logged in.
 */
public class Home extends BorderPane {
	
	private static Home home; // singleton

	/**
	 * Private constructor.
	 */
	private Home() {
		super();
		this.setLeft(Menu.getMenu());
		this.setCenter(MainPane.getMainPane());
	}

	/**
	 * Gets the single instance of this class.
	 * @return The single instance of this class.
	 */
	public static Home getHome() {
		
		if (home == null) home = new Home();
		
		return home;
		
	}
	
}
