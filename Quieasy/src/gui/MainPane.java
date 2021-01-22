package gui;

import guib.QuizBrowserTab;
import javafx.scene.control.TabPane;

/**
 * Class for the workspace on the dash board. It is a TabPane.
 */
public class MainPane extends TabPane{

	private static MainPane mainPane; // singleton

	/**
	 * Private constructor.
	 */
	private MainPane() {
		
		super();
		this.getTabs().add(QuizBrowserTab.instance());
		this.getSelectionModel().select(QuizBrowserTab.instance());

	}

	/**
	 * Gets the single instance of this class.
	 * @return The single instance of this class.
	 */
	public static MainPane getMainPane() {
		
		if (mainPane == null) mainPane = new MainPane();
		
		return mainPane;
		
	}

}
