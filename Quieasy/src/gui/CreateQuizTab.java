// create quiz tab

package gui;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Tab;


public class CreateQuizTab extends Tab {
	
	private static CreateQuizTab createQuizTab;


	// constructor can only be accessed from within
	private CreateQuizTab() {
		super("New Quiz", CreateQuizBox.getCreateQuizBox());

		
	}

	// Gets the current instance -> Singleton
	public static CreateQuizTab getCreateQuizTab() {
		if (createQuizTab == null) createQuizTab = new CreateQuizTab();
		return createQuizTab;
	}

	public void closeTab(){
		CreateQuizTab tab = getCreateQuizTab();
		EventHandler<Event> handler = tab.getOnClosed();
		if(handler != null){
			handler.handle(null);
		}else{

			tab.getTabPane().getTabs().remove(tab);



		}

	}



	
}
