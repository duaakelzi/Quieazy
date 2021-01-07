package gui;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class CreateQuizResultBox extends VBox {

    private Text resultText;
    private static CreateQuizResultBox createQuizResultBox;
    boolean Result1=false;


    public CreateQuizResultBox(boolean result) {
        this.Result1=result;
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
        resultText=new Text();
        resultText.setWrappingWidth(535);
        resultText.setFont(Font.font("Times New Roman", 17));
        if(Result1){
        resultText.setText("Congratulations you passed your quiz");}
        else {
                resultText.setText("sorry you did not pass your quiz");
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
