// Result box

package guib;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ResultBox extends VBox {
	
	private static ResultBox resultBox;
	
	// constructor can only be accessed from within
	private ResultBox() {
		
		super(8);
		
		addNodes();
		
	}
	
	// Gets the current instance -> Singleton
	public static ResultBox instance() {
		
		if (resultBox == null) resultBox = new ResultBox();
		
		return resultBox;
		
	}
	
	private void addNodes() {
		
		HBox hbox = new HBox(10);
		
		hbox.setPadding(new Insets(0, 20, 0, 0));
		hbox.setAlignment(Pos.CENTER_RIGHT);
		
		Button refreshButton = new Button("Refresh");
		
		hbox.getChildren().add(refreshButton);
		
		// ScrollPane
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		scrollPane.setContent(Result.instance());
		scrollPane.setFitToWidth(true);
		
		this.getChildren().addAll(hbox, scrollPane);

		refreshButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent e) {

				BrowserData.load();
				CourseSelectionBox.loadOptions();
				CourseSelectionBox.setDefault();
				AuthorSelectionBox.loadOptions();
				AuthorSelectionBox.setDefault();
				FilterBar.instance().reset();

			}

		});
		
	}
	
}
