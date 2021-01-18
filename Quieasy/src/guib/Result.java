// results

package guib;

import data.QuizData;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class Result extends VBox {
	
	private static Result result;
	
	private static ArrayList<QuizData> resultList = new ArrayList<QuizData>();
	
	// constructor can only be accessed from within
	private Result() {
		
		super(8);
		
		this.setStyle("-fx-background-color: #5F9EA0;");
		this.setPadding(new Insets(10, 100, 10, 100));
		this.setAlignment(Pos.CENTER);
		
	}
	
	// Gets the current instance -> Singleton
	public static Result instance() {
		
		if (result == null) result = new Result();
		
		return result;
		
	}
	
	public static void filter(Object filterBy, Object filterValue) {

		if(filterBy.equals("Course")) {

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

		if(filterBy.equals("Author")) {

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

		if(filterBy.equals("None")) {

			resultList = BrowserData.quizList();
			Result.instance().draw();

		}

	}
	
	private void draw() {
		
		this.getChildren().clear();
		
		for (int i = 0; i < resultList.size(); i++) {
			
			this.getChildren().add(new QuizSnippet(resultList.get(i)));
		    
		}
		
		if(resultList.size() == 0) {
			
			// when no result found
			Text noResult = new Text("No result found!");
			noResult.setFont(new Font(20));
			this.getChildren().add(noResult);
			
		}
		
	}
	
	public void search(String quizID) {
		
		this.getChildren().clear();
		
		ArrayList<QuizData> quizList = BrowserData.quizList();

		boolean noMatch = true; // no match found
		
		for (int i = 0; i < quizList.size(); i++) {
			
			if(quizID.equals(quizList.get(i).getName())) {
				
				this.getChildren().add(new QuizSnippet(quizList.get(i)));
				noMatch = false;
				
			}
		    
		}

		if(noMatch) {

			// when no match found
			Text noResult = new Text("No match found!");
			noResult.setFont(new Font(20));
			this.getChildren().add(noResult);

		}
		
	}
	
}
