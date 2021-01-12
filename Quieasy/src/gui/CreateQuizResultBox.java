package gui;

import data.Message;
import data.QuizData;
import data.ResultData;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.Label;

import java.awt.*;

public class CreateQuizResultBox extends VBox {

    private Label resultText;
    private static CreateQuizResultBox createQuizResultBox;


    private ResultData result;


    public CreateQuizResultBox(Message resultDataResponse) {

        result=resultDataResponse.resultData;

        HBox Result = initiateResultText();
        this.getChildren().addAll(Result);

    }

    private HBox initiateResultText() {
        HBox markQuestion = new HBox(10);
        //markQuestion.setPadding(new Insets(10));
        VBox mark = new VBox();
        mark.setBackground(new Background(new BackgroundFill(Color.LIGHTSTEELBLUE, new CornerRadii(0), Insets.EMPTY)));
        mark.setPrefWidth(120);
        mark.setPadding(new Insets(10));
        resultText= new Label();
        resultText.setWrapText(true);
        resultText.setFont(Font.font("Times New Roman", 17));

        if (result.isPassed()){
        resultText.setText("Congratulations you passed your quiz your fainal result is: "+result.getStatistics()+"\n");}
        else {
                resultText.setText("sorry you did not pass your quiz your fainal result is:"+result.getStatistics()+"\n");
            }
        for(int i=0;i<result.getCorrectAnswers().length;i++)
        {
            if(result.getCorrectAnswers()[i]){

            System.out.println("your "+(i+1)+" answer is true");}
            else{System.out.println("your "+(i+1)+" answer is false");

            }
        }
        mark.getChildren().addAll(resultText);
        mark.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        markQuestion.getChildren().addAll(mark);
        return markQuestion;
    }

//    public static CreateQuizResultBox getCreateQuizResultBox(){
//        if(createQuizResultBox == null){
//            createQuizResultBox = new CreateQuizResultBox();
//        }
//        return createQuizResultBox;
//    }
}
