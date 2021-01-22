package guib;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

import java.util.ArrayList;

/**
 * A ComboBox class to select course.
 */
public class CourseSelectionBox extends ComboBox {
	
	private static CourseSelectionBox courseSelectionBox; // Singleton
	
	private static ObservableList<String> options = FXCollections.observableArrayList(); // A list of course names

	/**
	 * Private constructor.
	 */
	private CourseSelectionBox() {
		
		super(options);
		
		CourseSelectionBox.loadOptions(); // Load the list of course names
		
		this.setValue("Select course"); // Set the default selected value
		
	}

	/**
	 * Gets the single instance of this class.
	 * @return The single instance of this class.
	 */
	public static CourseSelectionBox instance() {
		
		if (courseSelectionBox == null) {
			
			courseSelectionBox = new CourseSelectionBox();
			
		}
		
		return courseSelectionBox;
		
	}

	/**
	 * Load a list of course names into the ComboBox.
	 */
	public static void loadOptions() {
		
		options.clear();
		
		ArrayList<String> courseList = BrowserData.courseList();
		
		for (int i = 0; i < courseList.size(); i++) {
			
			options.add(courseList.get(i));
		    
		}
		
	}

	/**
	 * Set the default selected value.
	 */
	public static void setDefault(){

		courseSelectionBox.setValue("Select course");

	}
	
}
