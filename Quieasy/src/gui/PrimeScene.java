package gui;

import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * The the container for all content of the application's GUI.
 */
public class PrimeScene extends Scene {
	
	private static PrimeScene primeScene; // Singleton
	
	private static final double WIDTH = 900; // The width of the scene
	private static final double HEIGHT = 600; // The height of the scene

	/**
	 * Private constructor. Creates a Scene for a specific root Node with a specific size.
	 * @param root The root node of the scene graph.
	 * @param width The width of the scene.
	 * @param height The height of the scene.
	 */
	private PrimeScene(Parent root, double width, double height) {

		super(root, width, height);
		
	}

	/**
	 * Gets the single instance of this class.
	 * @return The single instance of this class.
	 */
	public static PrimeScene getPrimeScene() {
		
		if (primeScene == null) {

			primeScene = new PrimeScene(Login.getLogin(), WIDTH, HEIGHT);

		}
		
		return primeScene;
		
	}

	/**
	 * Show home page on successful login.
	 */
	public static void home() {
		
		getPrimeScene().setRoot(Home.getHome());
		
	}

	/**
	 * Show log in view.
	 */
	public static void login() {
		
		getPrimeScene().setRoot(Login.getLogin());
		
	}

	/**
	 * Show create account view.
	 */
	public static void register() {
		
		getPrimeScene().setRoot(Register.getRegister());
		
	}

}
