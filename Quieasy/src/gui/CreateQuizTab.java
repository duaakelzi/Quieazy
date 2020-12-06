// create quiz tab

package gui;

import javafx.scene.control.Tab;


public class CreateQuizTab extends Tab {
	
	private static CreateQuizTab createQuizTab;
	private CreateQuizBox createQuizBox;
	private CreateAddQuestionBox createQuestionBox;
	private String name;

	
	// constructor can only be accessed from within
	private CreateQuizTab() {
		super("New Quiz", CreateQuizBox.getCreateQuizBox());

		
	}
	public CreateQuizTab(String name, CreateQuizTab tab){
		super(name, CreateAddQuestionBox.getCreateAddQuestionBox());
		tab.setDisable(true) ;
	}

	public CreateQuizTab(String name){
		super(name, CreateQuestionChoicesBox.getCreateQuestionChoicesBox());
	}




	// Gets the current instance -> Singleton
	public static CreateQuizTab getCreateQuizTab() {
		
		if (createQuizTab == null) createQuizTab = new CreateQuizTab();

		
		return createQuizTab;
		
	}

	
}
