package data;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

private String studyProgram;

private int indexQuiz;

private int indexQuestion;

    private final GridPane dataGrid;
    private final Button addButton;
    private final Button delButton;
    //index from ArrayList to add faster to add Question
    private final Label indexQuizHide;
    private final Label indexQuestionHide;
    private final Text question;
    private final Text authorText;
    private final Text studyProgramText;

    public FilterDataQuestionBank(QuestionData questions, String author, String studyProgram, int indexQuiz, int indexQuestion) {
        dataGrid = new GridPane();
        question = new Text();
        authorText = new Text();
        studyProgramText = new Text();
        indexQuizHide = new Label();
        indexQuestionHide = new Label();
        dataGrid.setHgap(10);
        dataGrid.setVgap(0);
        dataGrid.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        addButton = new Button("+");
        addButton.setMinWidth(30);
        delButton = new Button("-");
        delButton.setDisable(true);
        delButton.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, null, null)));
        delButton.setMinWidth(30);
        addButton.setBackground(new Background(new BackgroundFill(Color.LIGHTCYAN, null, null)));
        dataGrid.add(addButton, 0, 0);
        dataGrid.add(delButton, 1, 0);
//            indexHide.getId();
//            indexHide.setVisible(true);
//            dataGrid.add(indexHide, 0, 1);
        question.setWrappingWidth(500);
        dataGrid.add(question, 2,0);

        //author.setWrappingWidth(75);
        authorText.setTextAlignment(TextAlignment.CENTER);
        dataGrid.add(authorText, 3,0);

        // studyProgram.setWrappingWidth(75);
        studyProgramText.setTextAlignment(TextAlignment.CENTER);
        dataGrid.add(studyProgramText, 4, 0);

        dataGrid.add(indexQuizHide, 5, 0);
        dataGrid.add(indexQuestionHide, 6, 0);
        this.questions = questions;
        this.author = author;
        this.studyProgram = studyProgram;
        this.indexQuiz = indexQuiz;
        this.indexQuestion = indexQuestion;
        question.setText(questions.getQuestion());
        authorText.setText(author);
        studyProgramText.setText(studyProgram);
        indexQuizHide.setText(String.valueOf(indexQuiz));
        //indexQuizHide.setVisible(false);
        indexQuestionHide.setText(String.valueOf(indexQuestion));
        //indexQuestionHide.setVisible(false);
    }

    public QuestionData getQuestions() {
        return questions;
    }

    public String getAuthor() {
        return author;
    }

    public String getStudyProgram() {
        return studyProgram;
    }




    public int getIndexQuiz() {
        return indexQuiz;
    }

    public int getIndexQuestion() {
        return indexQuestion;
    }

    public Text getQuestion() {
        return question;
    }

    public Label getIndexQuizHide() {
        return indexQuizHide;
    }

    public Label getIndexQuestionHide() {
        return indexQuestionHide;
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
    //    addButton.setOnAction(actionEvent -> {
////                            //here save the chosen question to be added to Array Question ArrayList//
//        dataGrid.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
//        CreateQuestionBankBox.quiz.addSingleQuestion(CreateQuestionBankBox.allQuizesData
//                .get(Integer.parseInt(indexQuizHide.getText()))
//                .getQuestions().get(Integer.parseInt(indexQuestionHide.getText())));
//        addButton.setDisable(true);
//        delButton.setDisable(false);
//
//    });
//                delButton.setOnAction(actionEvent -> {
//        System.out.println(questionData.getQuestions().getQuestion());
//        dataGrid.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
//        for(int i = 0; i < CreateQuestionBankBox.quiz.getQuestions().size(); i++){
//            if(question.getText().equals(CreateQuestionBankBox.quiz.getQuestions().get(i).getQuestion())){
//                CreateQuestionBankBox.quiz.removeSingleQuestion(i);
//                addButton.setDisable(false);
//                delButton.setDisable(true);
//            }
//        }
//
//    });
//
//
//
//                question.setText(questionData.getQuestions().getQuestion());
//                author.setText(questionData.getAuthor());
//                studyProgram.setText(questionData.getStudyProgram());
//                indexQuizHide.setText(String.valueOf(questionData.getIndexQuiz()));
//                indexQuizHide.setVisible(false);
//                indexQuestionHide.setText(String.valueOf(questionData.getIndexQuestion()));
//                indexQuestionHide.setVisible(false);
//
//    setGraphic(dataGrid);

}
