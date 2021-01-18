// Quiz Browser tab

package guib;

import javafx.scene.control.Tab;

public class QuizBrowserTab extends Tab {
	
	private static QuizBrowserTab quizBrowserTab;
	
	// constructor can only be accessed from within
	private QuizBrowserTab() {
		
		super("Quiz Browser", QuizBrowser.instance());
		this.setClosable(false);
		
	}
	
	// Gets the current instance -> Singleton
	public static QuizBrowserTab instance() {
		
		if (quizBrowserTab == null) quizBrowserTab = new QuizBrowserTab();
		
		return quizBrowserTab;
		
	}
	
}