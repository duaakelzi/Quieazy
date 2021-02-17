package guib;

import javafx.scene.control.Tab;

/**
 * Tab for Quiz Browser.
 */
public class QuizBrowserTab extends Tab {
	
	private static QuizBrowserTab quizBrowserTab; // Singleton

	/**
	 * Private constructor.
	 */
	private QuizBrowserTab() {
		
		super("Quiz Browser", QuizBrowser.instance());
		this.setClosable(false);
		
	}

	/**
	 * Gets the single instance of this class.
	 * @return The single instance of this class.
	 */
	public static QuizBrowserTab instance() {
		
		if (quizBrowserTab == null) quizBrowserTab = new QuizBrowserTab();
		
		return quizBrowserTab;
		
	}
	
}