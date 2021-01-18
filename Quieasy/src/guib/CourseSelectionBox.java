// course selection box

package guib;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

import java.util.ArrayList;

public class CourseSelectionBox extends ComboBox {
	
	private static CourseSelectionBox courseSelectionBox;
	
	private static ObservableList<String> options = FXCollections.observableArrayList();
	
	// constructor can only be accessed from within
	private CourseSelectionBox() {
		
		super(options);
		
		CourseSelectionBox.loadOptions();
		
		this.setValue("Select course");
		
	}
	
	// Gets the current instance -> Singleton
	public static CourseSelectionBox instance() {
		
		if (courseSelectionBox == null) {
			
			courseSelectionBox = new CourseSelectionBox();
			
		}
		
		return courseSelectionBox;
		
	}
	
	public static void loadOptions() {
		
		options.clear();
		
		ArrayList<String> courseList = BrowserData.courseList();
		
		for (int i = 0; i < courseList.size(); i++) {
			
			options.add(courseList.get(i));
		    
		}
		
	}

	public static void setDefault(){

		courseSelectionBox.setValue("Select course");

	}
	
}
