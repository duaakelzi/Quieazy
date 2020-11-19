// The workspace on the dash board

package dialog;

import javafx.scene.control.TabPane;

public class MainPane extends TabPane{

	private static MainPane mainPane;
	
	// constructor can only be accessed from within
	private MainPane() {
		
		super();
		
	}
	
	// Gets the current instance -> Singleton
	public static MainPane getMainPane() {
		
		if (mainPane == null) mainPane = new MainPane();
		
		return mainPane;
		
	}
	
}
