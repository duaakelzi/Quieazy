//starts the application

package application;

import gui.PrimeScene;
import javafx.application.Application;
import javafx.stage.Stage;

public class Quieasy extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage myStage) {
		
		//connect to server
		ClientAgent clientAgent = ClientAgent.getClientAgent();

		// start listening for incoming messages
		(new Thread(clientAgent)).start();
		
		// Setup the stage
		myStage.setTitle("Quieasy");
		
		myStage.setScene(PrimeScene.getPrimeScene());
		
		// Show the stage and its scene.
		myStage.show();
		
	}
	
}
