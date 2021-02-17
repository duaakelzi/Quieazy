package guib;

import data.QuizData;

import javafx.geometry.Insets;
import javafx.scene.layout.VBox;

/**
 * Quiz browser - Displays all available quizzes and offers options to filter them by course or author and search by ID.
 */
public class QuizBrowser extends VBox {
	
	private static QuizBrowser quizBrowser; // Singleton

	private static QuizData quizToPlay = null; // Quiz selected by the user for playing

	/**
	 * Private constructor.
	 */
	private QuizBrowser() {
		
		super(8); // spacing between children
		this.setStyle("-fx-background-color: #ADD8E6;");
		this.setPadding(new Insets(5, 5, 5, 5));
		
		BrowserData.load(); // Retrieve quizzes from server.

		this.getChildren().addAll(FilterBar.instance(), ResultBox.instance());
		
		FilterBar.filter(); // Apply default filter
		
	}

	/**
	 * Gets the single instance of this class.
	 * @return The single instance of this class.
	 */
	public static QuizBrowser instance() {
		
		if (quizBrowser == null) quizBrowser = new QuizBrowser();
		
		return quizBrowser;
		
	}

	/**
	 * Set quiz to play.
	 * @param quiz Quiz to play.
	 */
	public static void setQuizToPlay(QuizData quiz){

		quizToPlay = quiz;

	}

	/**
	 * Get quiz to play.
	 * @return Quiz to play.
	 */
	public static QuizData getQuizToPlay(){

		return quizToPlay;

	}

}