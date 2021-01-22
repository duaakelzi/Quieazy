package gui;

import data.QuestionData;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * Filtered data to be displayed in the ListView after the filter selected the matching key words given by user
 */
public class FilterDataQuestionBank {

    private final QuestionData questions;
    private final GridPane dataGrid;
    private final Button addButton;
    private final Button delButton;
    private final Text question;
    private String author;

    /**
     * Constructor that create this objects to fill the listView with questions, ready to be selected by user in his own Quiz
     * +Button is created ready to check the selected Question to be added in the Quiz
     * -Button is created ready to delete the question from the newest Quiz
     *
     * @param questions  containt that is displayed to the user
     * @param firstName, the owner of the question is created by taking the firstName and
     * @param lastName,  to show to whom belongs the questions filtered ready to be selected in another Quiz
     */
    public FilterDataQuestionBank(QuestionData questions, String firstName, String lastName) {
        this.author = firstName + " " + lastName;
        dataGrid = new GridPane();
        question = new Text();
        Text questionAuthor = new Text();
        dataGrid.setHgap(10);
        dataGrid.setVgap(0);
        dataGrid.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        addButton = new Button("+");
        addButton.setMinWidth(30);
        delButton = new Button("-");
        delButton.setDisable(true);
        delButton.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, null, null)));
        delButton.setMinWidth(30);
        addButton.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
        dataGrid.add(addButton, 0, 0);
        dataGrid.add(delButton, 1, 0);
        question.setWrappingWidth(550);
        dataGrid.add(question, 2, 0);
        questionAuthor.setTextAlignment(TextAlignment.CENTER);
        dataGrid.add(questionAuthor, 3, 0);
        this.questions = questions;
        this.author = firstName + " " + lastName;
        question.setText(questions.getQuestion());
        questionAuthor.setText(author);
    }

    /**
     * Getter and Setter to retrieve data from the listView
     *
     * @return question, author
     */
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