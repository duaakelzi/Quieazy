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

public class QuizSnippet extends VBox {
	
	private QuizData quiz;
	
	public QuizSnippet(QuizData quiz) {
		
		super(6);
		
		this.quiz = quiz;
		
		this.setStyle("-fx-background-color: #FFE4B5;");
		this.setPadding(new Insets(12, 12, 12, 12));
		
		HBox hbox = new HBox(100);
		
		Text quizID = new Text(quiz.getName());
		quizID.setFont(new Font(14));
		quizID.setStyle("-fx-color: #336699;");
		
		Text quizAuth = new Text(quiz.getUser().getLastName() + ", " + quiz.getUser().getFirstName());
		quizAuth.setFont(new Font(14));
		quizAuth.setStyle("-fx-color: #336699;");
		
		hbox.getChildren().addAll(quizID, quizAuth);
		
		Text courseName = new Text(quiz.getCourse());
		courseName.setFont(new Font(14));
		courseName.setStyle("-fx-color: #336699;");
		
		HBox hbox2 = new HBox(10);
		hbox2.setAlignment(Pos.CENTER);
		
		Button playButton = new Button("Play");
		playButton.setStyle("-fx-color: #336699;");
		
		hbox2.getChildren().add(playButton);
		
		this.getChildren().addAll(hbox, courseName, hbox2);
		
		playButton.setOnAction(new EventHandler<ActionEvent>() {
			 
		    public void handle(ActionEvent e) {
		        
		    	// launch "Play Quiz" from here.
				if(QuizBrowser.getQuizToPlay() != null){

					if(!quiz.equals(QuizBrowser.getQuizToPlay())){

						// close the current tab first
						MainPane.getMainPane().getTabs().remove(PlayQuizTab.getPlayQuizTab());
						PlayQuizTab.reset();
						//

						QuizBrowser.setQuizToPlay(quiz);
						MainPane.getMainPane().getTabs().add(PlayQuizTab.getPlayQuizTab());
						MainPane.getMainPane().getSelectionModel().select(PlayQuizTab.getPlayQuizTab());

					}else{

						MainPane.getMainPane().getSelectionModel().select(PlayQuizTab.getPlayQuizTab());

					}

				}else{

					QuizBrowser.setQuizToPlay(quiz);
					MainPane.getMainPane().getTabs().add(PlayQuizTab.getPlayQuizTab());
					MainPane.getMainPane().getSelectionModel().select(PlayQuizTab.getPlayQuizTab());

				}
		    	
		    }
		    
		});
		
	}

}
