//starts the application

package application;

import javafx.application.*;
import javafx.stage.*;

public class Quieasy extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage myStage) {

		// Setup the stage
		myStage.setTitle("Quieasy");
		
		myStage.setScene(PrimeScene.getPrimeScene());
		
		// Show the stage and its scene.
		myStage.show();
		
		//start the client agent thread that sends and receives message to and from the server
		ClientAgent clientAgent = ClientAgent.getClientAgent();
		
		(new Thread(clientAgent)).start();
		
	}
	
}
