package data;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class FilterDataQuestionBank {

    private QuestionData questions;

    private String author;

    private final GridPane dataGrid;
    private final Button addButton;
    private final Button delButton;
    //index from ArrayList to add faster to add Question

    private final Text question;
    private final Text questionAuthor;



    public FilterDataQuestionBank(QuestionData questions, String author, int indexQuestion, int questionID) {
        dataGrid = new GridPane();
        question = new Text();
        questionAuthor = new Text();
        dataGrid.setHgap(10);
        dataGrid.setVgap(0);
        dataGrid.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        addButton = new Button("+");
        addButton.setMinWidth(30);
        delButton = new Button("-");
        delButton.setDisable(true);
        delButton.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
        delButton.setMinWidth(30);
        addButton.setBackground(new Background(new BackgroundFill(Color.GREY, null, null)));
        dataGrid.add(addButton, 0, 0);
        dataGrid.add(delButton, 1, 0);
//            indexHide.getId();
//            indexHide.setVisible(true);
//            dataGrid.add(indexHide, 0, 1);
        question.setWrappingWidth(550);
        dataGrid.add(question, 2,0);

        //author.setWrappingWidth(75);
        questionAuthor.setTextAlignment(TextAlignment.CENTER);
        dataGrid.add(questionAuthor, 3,0);

        // studyProgram.setWrappingWidth(75);

        this.questions = questions;
        this.author = author;


        question.setText(questions.getQuestion());
        questionAuthor.setText(author);
    }

    public QuestionData getQuestions() {
        return questions;
    }

    public String getAuthor() {
        return author;
    }



    public Text getQuestion() {
        return question;
    }



    public Button getAddButton() {
        return addButton;
    }

    public Button getDelButton() {
        return delButton;
    }

    public GridPane getDataGrid() {
        return dataGrid;
    }


}