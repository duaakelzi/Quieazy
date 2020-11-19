//starts the application

package application;

import javafx.application.*;
import javafx.stage.*;
import dialog.PrimeScene;

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
