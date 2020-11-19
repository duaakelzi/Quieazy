// GUI for home page of the app when logged in.

package dialog;

import javafx.scene.layout.*;

public class Home extends BorderPane {
	
	private static Home home;
	
	// constructor can only be accessed from within
	private Home() {
		super();
		this.setLeft(Menu.getMenu());
		this.setCenter(MainPane.getMainPane());
	}
	
	// Gets the current instance -> Singleton.
	public static Home getHome() {
		
		if (home == null) home = new Home();
		
		return home;
		
	}
	
}
