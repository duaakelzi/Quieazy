package gui;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class QuizFinalResultBox extends VBox {
    private static QuizFinalResultBox quizFinalResultBox;

    private QuizFinalResultBox(){

        VBox congradulation = passedQuiz();
        this.getChildren().addAll(congradulation);

      //  this.getChildren().add();

    }

    public static QuizFinalResultBox getQuizFinalResultBox() {
        if(quizFinalResultBox == null){
            quizFinalResultBox = new QuizFinalResultBox();
        }
        return quizFinalResultBox;
    }


    private VBox passedQuiz(){
        VBox congradulations = new VBox();
        ImageView congradulation = new ImageView(new Image("images/congradulations.png"));
        Label yourScore = new Label("YOUR SCORE: ");
        yourScore.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 26));
        yourScore.setTextFill(Color.FIREBRICK);
        congradulations.getChildren().addAll(congradulation, yourScore);

       return congradulations;
    }
//    ImageView submitImg = new ImageView(new Image("images/submit.png"));
//        submit.setGraphic(submitImg);

}
