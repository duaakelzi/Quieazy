package guib;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Filter bar - contains fields and buttons to filter quizzes by course or author and search by ID.
 */
public class FilterBar extends VBox {
	
	private static FilterBar filterBar; // Singleton

	private Button resetButton = new Button ("Reset"); // Reset button to restore filter values to default
	
	private static HBox hbox3 = new HBox(30);
	private final static ToggleGroup toggleGroup = new ToggleGroup(); // Toggle filter type: course or author.
	RadioButton rbCourse = new RadioButton("Course"); // filter by course
	private RadioButton rbAuthor = new RadioButton("Author"); // filter by author
	private RadioButton rbNone = new RadioButton("None"); // no filter
	
	// third line of controls
	private Button applyButton = new Button ("Apply Filter"); // Apply filter
	
	// fourth line of controls
	private TextField quizIDField = new TextField(); // Input field for quiz ID
	private Button searchButton = new Button ("Search"); // Search button to find quiz by ID

	/**
	 * Private constructor.
	 */
	private FilterBar() {
		
		super(15);
		this.setStyle("-fx-background-color: #FFFFFF;");
		this.setPadding(new Insets(25, 25, 25, 25));
		
		addNodes();
		addListeners();
		
	}

	/**
	 * Gets the single instance of this class.
	 * @return The single instance of this class.
	 */
	public static FilterBar instance() {
		
		if (filterBar == null) filterBar = new FilterBar();
		
		return filterBar;
		
	}

	/**
	 * Add nodes to the filter bar and adjust layout.
	 */
	private void addNodes() {
		
		// first line of controls
		HBox hbox1 = new HBox(10);
		hbox1.setAlignment(Pos.CENTER_RIGHT);
		
		resetButton = new Button ("Reset");
		
		hbox1.getChildren().add(resetButton);
		
		// second line of controls
		HBox hbox2 = new HBox(30);
		
		Label filterBy = new Label("Filter by:");
		
		rbCourse.setUserData("Course");
		rbCourse.setToggleGroup(toggleGroup);
		
		rbAuthor.setUserData("Author");
		rbAuthor.setToggleGroup(toggleGroup);
		
		rbNone.setUserData("None");
		rbNone.setToggleGroup(toggleGroup);
		rbNone.setSelected(true);
		
		hbox2.getChildren().addAll(filterBy, rbCourse, rbAuthor, rbNone);
		
		// third line of controls
		hbox3.getChildren().addAll(CourseSelectionBox.instance(), applyButton);
		hbox3.setDisable(true);
		
		// fourth line of controls
		HBox hbox4 = new HBox(30);
		
		quizIDField.setPromptText("Enter quiz ID");
		
		hbox4.getChildren().addAll(quizIDField, searchButton);
		
		// add all nodes
		this.getChildren().addAll(hbox1, hbox2, hbox3, hbox4);
		
	}

	/**
	 * Add event handlers to the controls in the filter bar.
	 */
	private void addListeners() {
		
		resetButton.setOnAction(new EventHandler<ActionEvent>() {
			 
		    public void handle(ActionEvent e) {
		        
		    	reset();
		    	
		    }
		    
		});
		
		toggleGroup.selectedToggleProperty().addListener(
			    (ObservableValue<? extends Toggle> ov, Toggle old_toggle, 
			    Toggle new_toggle) -> {
			    	
			    	if(rbCourse.isSelected()) {
		        		
		        		hbox3.getChildren().set(0, CourseSelectionBox.instance());
		        		hbox3.setDisable(false);
		        		
		        	}else if(rbAuthor.isSelected()) {
		        		
		        		hbox3.getChildren().set(0, AuthorSelectionBox.instance());
		        		hbox3.setDisable(false);
		        		
		        	}else if(rbNone.isSelected()) {
		        		
		        		hbox3.setDisable(true);
		        		
		        	}
			    	
			});
		
		applyButton.setOnAction(new EventHandler<ActionEvent>() {
			 
		    public void handle(ActionEvent e) {
		        
		    	filter();
		    	
		    }
		    
		});
		
		searchButton.setOnAction(new EventHandler<ActionEvent>() {
			 
		    public void handle(ActionEvent e) {
		        
		    	Result.instance().search(quizIDField.getText());
		    	
		    }
		    
		});
		
	}

	/**
	 * Reset the filter to default.
	 */
	public void reset() {

		rbNone.setSelected(true);
		quizIDField.clear();
		filter();
		
	}

	/**
	 * Run filter on the quizzes and show results.
	 */
	public static void filter() {
		
		Result.filter(toggleGroup.getSelectedToggle().getUserData(), ((ComboBox)hbox3.getChildren().get(0)).getValue());
		
	}
	
}