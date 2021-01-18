// Quiz Browser

package guib;

import javafx.geometry.Insets;
import javafx.scene.layout.VBox;

public class QuizBrowser extends VBox {
	
	private static QuizBrowser quizBrowser;
	
	// constructor can only be accessed from within
	private QuizBrowser() {
		
		super(8); // spacing between children
		this.setStyle("-fx-background-color: #ADD8E6;");
		this.setPadding(new Insets(5, 5, 5, 5));
		
		BrowserData.load();

		this.getChildren().addAll(FilterBar.instance(), ResultBox.instance());
		
		FilterBar.filter();
		
	}
	
	// Gets the current instance -> Singleton
	public static QuizBrowser instance() {
		
		if (quizBrowser == null) quizBrowser = new QuizBrowser();
		
		return quizBrowser;
		
	}

	/*
	public static void load() {
		
		QuizBrowser.instance().getChildren().addAll(FilterBar.instance(), ResultBox.instance());
		
		FilterBar.filter();
		
	}
	*/
}