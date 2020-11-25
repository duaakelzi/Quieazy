// create quiz wizard

package gui;

import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;

public class CreateQuizBox extends VBox {
	
	private static CreateQuizBox createQuizBox;
	
	// constructor can only be accessed from within
	private CreateQuizBox() {
		
		super();
		
		Button button = new Button("Add Question"); // just for demo
		
		this.getChildren().addAll(button);
		
	}
	
	// Gets the current instance -> Singleton
	public static CreateQuizBox getCreateQuizBox() {
		
		if (createQuizBox == null) createQuizBox = new CreateQuizBox();
		
		return createQuizBox;
		
	}

}
