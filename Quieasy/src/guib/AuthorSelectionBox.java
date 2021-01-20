// author selection box

package guib;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

import java.util.ArrayList;

public class AuthorSelectionBox extends ComboBox {
	
	private static AuthorSelectionBox authorSelectionBox;
	
	private static ObservableList<String> options = FXCollections.observableArrayList();
	
	// constructor can only be accessed from within
	private AuthorSelectionBox() {
		
		super(options);
		
		AuthorSelectionBox.loadOptions();
		
		this.setValue("Select author");
		
	}
	
	// Gets the current instance -> Singleton
	public static AuthorSelectionBox instance() {
		
		if (authorSelectionBox == null) authorSelectionBox = new AuthorSelectionBox();
		
		return authorSelectionBox;
		
	}
	
	public static void loadOptions() {
		
		options.clear();
		
		ArrayList<String> authorList = BrowserData.authorList();
		
		for (int i = 0; i < authorList.size(); i++) {
			
			options.add(authorList.get(i));
		    
		}
		
	}

	public static void setDefault(){

		authorSelectionBox.setValue("Select author");

	}
	
}
