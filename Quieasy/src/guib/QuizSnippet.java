package guib;

import data.QuizData;
import gui.MainPane;
import gui.PlayQuizTab;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Snippet to display some information about a quiz. To be shown in the Quiz Browser's results box.
 */
public class QuizSnippet extends VBox {
	
	private QuizData quiz; // The quiz to show.

	/**
	 * Snippet constructor.
	 * @param quiz Quiz to show.
	 */
	public QuizSnippet(QuizData quiz) {
		
		super(6);
		
		this.quiz = quiz;
		
		this.setStyle("-fx-background-color: #FFE4B5;");
		this.setPadding(new Insets(12, 12, 12, 12));
		
		HBox hbox = new HBox(100);
		
		Text quizID = new Text(quiz.getName()); // Quiz ID
		quizID.setFont(new Font(14));
		quizID.setStyle("-fx-color: #336699;");
		
		Text quizAuth = new Text(quiz.getUser().getLastName() + ", " + quiz.getUser().getFirstName()); // Quiz author
		quizAuth.setFont(new Font(14));
		quizAuth.setStyle("-fx-color: #336699;");
		
		hbox.getChildren().addAll(quizID, quizAuth);
		
		Text courseName = new Text(quiz.getCourse()); // Quiz course
		courseName.setFont(new Font(14));
		courseName.setStyle("-fx-color: #336699;");
		
		HBox hbox2 = new HBox(10);
		hbox2.setAlignment(Pos.CENTER);
		
		Button playButton = new Button("Play"); // Play button
		playButton.setStyle("-fx-color: #336699;");
		
		hbox2.getChildren().add(playButton);
		
		this.getChildren().addAll(hbox, courseName, hbox2);
		
		playButton.setOnAction(new EventHandler<ActionEvent>() {
			 
		    public void handle(ActionEvent e) {
		        
		    	// Launch QuizPlayer for this quiz

				if(QuizBrowser.getQuizToPlay() != null){ // If QuizPlayer is already open

					if(!quiz.equals(QuizBrowser.getQuizToPlay())){ // If playing a different quiz

						// Quit the current quiz first
						MainPane.getMainPane().getTabs().remove(PlayQuizTab.getPlayQuizTab());
						PlayQuizTab.reset();

						// Play this quiz

						QuizBrowser.setQuizToPlay(quiz); // set this quiz to play
						MainPane.getMainPane().getTabs().add(PlayQuizTab.getPlayQuizTab()); // start QuizPlayer
						MainPane.getMainPane().getSelectionModel().select(PlayQuizTab.getPlayQuizTab()); // Show QuizPlayer tab

					}else{ // If playing the same quiz

						// Just show the QuizPlayer
						MainPane.getMainPane().getSelectionModel().select(PlayQuizTab.getPlayQuizTab());

					}

				}else{ // If QuizPlayer is not already open

					QuizBrowser.setQuizToPlay(quiz); // set this quiz to play
					MainPane.getMainPane().getTabs().add(PlayQuizTab.getPlayQuizTab()); // start QuizPlayer
					MainPane.getMainPane().getSelectionModel().select(PlayQuizTab.getPlayQuizTab()); // Show QuizPlayer tab

				}
		    	
		    }
		    
		});
		
	}

}
