package guib;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Displays a list of quizzes that match the filter criteria or search ID in the Quiz Browser.
 */
public class ResultBox extends VBox {
	
	private static ResultBox resultBox; // Singleton

	/**
	 * Private constructor.
	 */
	private ResultBox() {
		
		super(8);
		
		addNodes();
		
	}

	/**
	 * Gets the single instance of this class.
	 * @return The single instance of this class.
	 */
	public static ResultBox instance() {
		
		if (resultBox == null) resultBox = new ResultBox();
		
		return resultBox;
		
	}

	/**
	 * Add nodes to this ResultBox and adjust layout.
	 */
	private void addNodes() {
		
		HBox hbox = new HBox(10);
		
		hbox.setPadding(new Insets(0, 20, 0, 0));
		hbox.setAlignment(Pos.CENTER_RIGHT);
		
		Button refreshButton = new Button("Refresh"); // Refresh button: reloads quizzes from server and resets filter.
		
		hbox.getChildren().add(refreshButton);
		
		// ScrollPane
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		scrollPane.setContent(Result.instance()); // Show filter or search result
		scrollPane.setFitToWidth(true);
		
		this.getChildren().addAll(hbox, scrollPane);

		refreshButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent e) {

				// Reload quizzes from server and reset filter.

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
