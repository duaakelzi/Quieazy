package gui;

import javafx.geometry.Insets;
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
        VBox passFailVBox = new VBox();
        passFailVBox.setPadding(new Insets(0,0,0, 130));
        ImageView congradulation = new ImageView(new Image("images/congradulations.png"));
        passFailVBox.setPrefWidth(600);
        ImageView failer = new ImageView(new Image("images/fail.png"));
        Label yourScoreLabel = new Label("YOUR SCORE:  " + "99" +" points");
        yourScoreLabel.setPadding(new Insets(0,0,0,20));
        yourScoreLabel.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 26));
        yourScoreLabel.setTextFill(Color.FIREBRICK);
        if(72 >= CreateQuizBox.getCreateQuizBox().getQuiz().getThreshold()) {
            passFailVBox.getChildren().addAll(congradulation, yourScoreLabel);
        }else{
            passFailVBox.getChildren().addAll(failer, yourScoreLabel);
        }

       return passFailVBox;
    }
//    ImageView submitImg = new ImageView(new Image("images/submit.png"));
//        submit.setGraphic(submitImg);

}
