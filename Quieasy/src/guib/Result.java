package guib;

import data.QuizData;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;

/**
 * Contains vertically listed snippets of quizzes that match the filter or search criteria in the Quiz Browser.
 */
public class Result extends VBox {
	
	private static Result result; // Singleton
	
	private static ArrayList<QuizData> resultList = new ArrayList<QuizData>(); // A list of quizzes that match the filter or search criteria.

	/**
	 * Private constructor.
	 */
	private Result() {
		
		super(8);
		
		this.setStyle("-fx-background-color: #5F9EA0;");
		this.setPadding(new Insets(10, 100, 10, 100));
		this.setAlignment(Pos.CENTER);
		
	}

	/**
	 * Gets the single instance of this class.
	 * @return The single instance of this class.
	 */
	public static Result instance() {
		
		if (result == null) result = new Result();
		
		return result;
		
	}

	/**
	 * Apply the specified filter on the quizzes and show results.
	 * @param filterBy Specify filter type: "Course", "Author" or "None".
	 * @param filterValue Course or author name.
	 */
	public static void filter(Object filterBy, Object filterValue) {

		if(filterBy.equals("Course")) { // Filter by course

			ArrayList<QuizData> list = new ArrayList<QuizData>();

			ArrayList<QuizData> quizList = BrowserData.quizList();

			for (int i = 0; i < quizList.size(); i++) {

				if(filterValue.equals(quizList.get(i).getCourse())) {

					list.add(quizList.get(i));

				}

			}

			resultList = list;
			Result.instance().draw();
		}

		if(filterBy.equals("Author")) { // Filter by author

			ArrayList<QuizData> list = new ArrayList<QuizData>();

			ArrayList<QuizData> quizList = BrowserData.quizList();

			for (int i = 0; i < quizList.size(); i++) {

				if(filterValue.equals(quizList.get(i).getUser().getLastName() + ", " + quizList.get(i).getUser().getFirstName())) {

					list.add(quizList.get(i));

				}

			}

			resultList = list;
			Result.instance().draw();

		}

		if(filterBy.equals("None")) { // Apply no filter

			resultList = BrowserData.quizList();
			Result.instance().draw();

		}

	}

	/**
	 * Draw a snippet for each quiz in the result.
	 */
	private void draw() {
		
		this.getChildren().clear(); // empty the result first
		
		for (int i = 0; i < resultList.size(); i++) { // create a snippet for each quiz in the result and show
			
			this.getChildren().add(new QuizSnippet(resultList.get(i)));
		    
		}
		
		if(resultList.size() == 0) { // if no result: show "No result found!"

			Text noResult = new Text("No result found!");
			noResult.setFont(new Font(20));
			this.getChildren().add(noResult);
			
		}
		
	}

	/**
	 * Search quiz by ID and show.
	 * @param quizID ID of the quiz.
	 */
	public void search(String quizID) {
		
		this.getChildren().clear(); // empty the result first
		
		ArrayList<QuizData> quizList = BrowserData.quizList();

		boolean noMatch = true; // true if no match found
		
		for (int i = 0; i < quizList.size(); i++) {
			
			if(quizID.equals(quizList.get(i).getName())) { // if match found: create a snippet for the quiz and show
				
				this.getChildren().add(new QuizSnippet(quizList.get(i)));
				noMatch = false;
				
			}
		    
		}

		if(noMatch) { // if no match found: show "No match found!"

			Text noResult = new Text("No match found!");
			noResult.setFont(new Font(20));
			this.getChildren().add(noResult);

		}
		
	}
	
}
