//PrimeScene is a container for all UI elements.
//Only a single instance required. Hence, Singleton Pattern is used here.

package gui;

import javafx.scene.*;
import javafx.scene.paint.Color;

public class PrimeScene extends Scene {
	
	private static PrimeScene primeScene;
	
	private static final double WIDTH = 800;
	private static final double HEIGHT = 500;
	
	// constructor can only be accessed from within
	private PrimeScene(Parent root, Color color) {
		super(root,WIDTH, HEIGHT, color);
		
	}
	
	// Gets the current instance -> Singleton
	public static PrimeScene getPrimeScene() {
		
		if (primeScene == null) {
			
			//primeScene = new PrimeScene(Login.getLogin(), WIDTH, HEIGHT, Color.LAVENDER);
			primeScene = new PrimeScene(Home.getHome(),Color.LAVENDER); 
			
		}
		
		return primeScene;
		
	}
	
	// Method to open Home Page on successful login.
	public static void home() {
		
		getPrimeScene().setRoot(Home.getHome());
		
	}
	
	// show "login" dialog
	public static void login() {
		
		getPrimeScene().setRoot(Login.getLogin());
		
	}
	
	// show "register" dialog
	public static void register() {
		
		getPrimeScene().setRoot(Register.getRegister());
		
	}
	public  static void createQuestion(){
		getPrimeScene().setRoot(CreateAddQuestionBox.getCreateAddQuestionBox());
	}

	
}
