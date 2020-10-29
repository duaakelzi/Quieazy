//PrimeScene is the scene where GUI items are shown.
//Only a single instance required. Hence, Singleton Pattern is used here.

package application;

import dialog.*;
import javafx.scene.*;

public class PrimeScene extends Scene {
	
	private static PrimeScene primeScene;
	
	// Constructor
	private PrimeScene(Parent root, double width, double height) {
		
		super(root, width, height);
		
	}
	
	// Gets the current instance. A new one is created and returned if it doesn't exist.
	public static PrimeScene getPrimeScene() {
		
		if (primeScene == null) {
			
			Login login = Login.getLogin();
			primeScene = new PrimeScene(login, login.WIDTH, login.HEIGHT);
			
		}
		
		return primeScene;
		
	}
	
	//Method to switch to 'Home Page" on successful login.
	public void home() {
		
		this.setRoot(Home.getHome());
		
	}
	
}
