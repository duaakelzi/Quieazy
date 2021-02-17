package guib;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

import java.util.ArrayList;

/**
 * A ComboBox class to select quiz author.
 */
public class AuthorSelectionBox extends ComboBox {
	
	private static AuthorSelectionBox authorSelectionBox; // Singleton
	
	private static ObservableList<String> options = FXCollections.observableArrayList(); // A list of names of authors

	/**
	 * Private constructor.
	 */
	private AuthorSelectionBox() {
		
		super(options);
		
		AuthorSelectionBox.loadOptions(); // Load the list of names of authors
		
		this.setValue("Select author"); // Set the default selected value
		
	}

	/**
	 * Gets the single instance of this class.
	 * @return The single instance of this class.
	 */
	public static AuthorSelectionBox instance() {
		
		if (authorSelectionBox == null) authorSelectionBox = new AuthorSelectionBox();
		
		return authorSelectionBox;
		
	}

	/**
	 * Load a list of names of authors into the ComboBox.
	 */
	public static void loadOptions() {
		
		options.clear();
		
		ArrayList<String> authorList = BrowserData.authorList();
		
		for (int i = 0; i < authorList.size(); i++) {
			
			options.add(authorList.get(i));
		    
		}
		
	}

	/**
	 * Set the default selected value.
	 */
	public static void setDefault(){

		authorSelectionBox.setValue("Select author");

	}
	
}
