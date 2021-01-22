
package application;

import gui.PrimeScene;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Class starts Quieasy Client as a JavaFX application.
 */
public class Quieasy extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * The start method called for the application to begin running.
	 * @param myStage The primary stage for this application, onto which the application scene can be set.
	 */
	public void start(Stage myStage) {
		
		//connect to server
		ClientAgent clientAgent = ClientAgent.getClientAgent();

		// start listening for incoming messages
		(new Thread(clientAgent)).start();
		
		// Setup the stage
		myStage.setTitle("Quieasy");

		// set scene
		myStage.setScene(PrimeScene.getPrimeScene());
		
		// Show the stage and its scene.
		myStage.show();
		
	}
	
}
