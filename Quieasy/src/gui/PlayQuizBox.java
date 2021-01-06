package gui;

import data.ChoicesData;
import data.QuestionData;
import data.QuizData;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import requests.CheckCorrectAnswerC;

import java.util.ArrayList;

public class PlayQuizBox extends VBox {

    private static PlayQuizBox playQuizBox;
    private Label textMark;
    private Label questionLabel;
    private CheckBox[] answersCheck;
    private Button submit;
    private Button skip;
    private Button next;
    private Button cancel;
    //missing: Quiz with its questionData and user info
    private QuizData quiz;
    private ArrayList<QuestionData> quizQuestions;
    private String selectedAnswer[];
  //  private ArrayList<ChoicesData> questionChoices;

    //constructor*****
    public PlayQuizBox(QuizData quizToPlay) { //this shouldn't be empty but receive the quizData from the MyQuiz
        quiz = quizToPlay;
        quizQuestions = quiz.getQuestions();
        //fetch all questions here
      //  fetchQuestions(quizToPlay);

        // questions track with Pagination
        HBox questionsTrack = initiateQuestionTrack();

        //Question information mark and the number of question
        HBox markQuestion = initiateQuestionMarkandQuestion();

        // Answer layout
        VBox answers = initiateAnswers(0);

        //Buttons
        HBox buttons = initiateButtons();

        this.getChildren().addAll(questionsTrack, markQuestion, answers, buttons);

    }

    //not needed because quiz already contains all data. Maybe needed when browser is available
//    private void fetchQuestions(QuizData quiz){
//        quizQuestions = QuestionC.fetchQuizQuestions(quiz);
//    }



    // the track to navigate among questions. linked to the actual array of questions
    public HBox initiateQuestionTrack(){
        //as soon as one of the arrows clicked (left or right), the text should change
        HBox questionstrack = new HBox(quiz.getQuestions().size());
        questionstrack.setAlignment(Pos.CENTER);
        //questionstrack.setPadding(new Insets(10));
        Pagination trackquestions = new Pagination(quiz.getQuestions().size());
        trackquestions.getStyleClass().add(Pagination.STYLE_CLASS_BULLET);
        ProgressIndicator indicator = new ProgressIndicator(0.8);
        AnchorPane anchor = new AnchorPane();
        AnchorPane.setTopAnchor(trackquestions, 10.0);
        AnchorPane.setRightAnchor(trackquestions, 10.0);
        AnchorPane.setBottomAnchor(trackquestions, 10.0);
        AnchorPane.setLeftAnchor(trackquestions, 10.0);
        anchor.getChildren().addAll(trackquestions);
        questionstrack.getChildren().addAll(indicator,anchor);
        return questionstrack;
    }

    // this method should display the question and quiz info.
    public HBox initiateQuestionMarkandQuestion(){
        HBox markQuestion = new HBox(10);
        //markQuestion.setPadding(new Insets(10));
        VBox mark = new VBox();
        mark.setBackground(new Background(new BackgroundFill(Color.LIGHTSTEELBLUE, new CornerRadii(0), Insets.EMPTY)));
        mark.setPrefWidth(120);
        mark.setPadding(new Insets(10));
        textMark = new Label();
        textMark.setWrapText(true);
        textMark.setFont(Font.font("Times New Roman", 17));
        //for first view only
        textMark.setText("Quiz: " + quiz.getName() + "\n" + "Question 1" + "\n" + "Quiz threshold: " + quiz.getThreshold()); // additional info: status (answered/not), points (out of total)
        mark.getChildren().addAll(textMark);
        mark.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        HBox question = new HBox();
        question.setBackground(new Background(new BackgroundFill(Color.LIGHTSKYBLUE, null, null)));
        question.setPrefWidth(535);
        question.setPrefHeight(100);
        questionLabel = new Label();
        questionLabel.setPadding(new Insets(10));
        questionLabel.setWrapText(true);
        questionLabel.setFont(Font.font("Times New Roman", 20));
        //for first view only
        questionLabel.setText(quizQuestions.get(0).getQuestion());
        question.getChildren().addAll(questionLabel);
        question.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        markQuestion.getChildren().addAll(mark, question);
        return markQuestion;
    }

   // shows the choices for the question
    public VBox initiateAnswers(int questionPos){
        VBox answers = new VBox(20);
        answers.setPrefWidth(650);
        answersCheck = new CheckBox[4];
        selectedAnswer =new String[quiz.getQuestions().size()];
        answers.setBackground(new Background(new BackgroundFill(Color.LIGHTCYAN, null, null)));
        answers.setPadding(new Insets(20, 10, 10, 140));
        setAnswers(answersCheck.length, questionPos);
        answers.getChildren().addAll(answersCheck);
        return answers;
    }

    private void setAnswers(int size, int questionPos){
        for(int i= 0; i < answersCheck.length; i++){
            //for first view only
            answersCheck[i] = new CheckBox();
            answersCheck[i].setText(quizQuestions.get(questionPos).getAnswers().get(i).getChoiceDescription());
        }
    }

    private HBox initiateButtons(){
        HBox buttons = new HBox(120);
        buttons.setPadding(new Insets(40));
        submit = initiateSubmitButton();
        skip = initiateSkipButton();
        next = initiateNextButton();
        cancel = initiateCancelButton();
        buttons.setAlignment(Pos.CENTER);
        buttons.getChildren().addAll(cancel, skip, next, submit);
        return buttons;

    }
    //should change the fields with the next question
    private Button initiateNextButton() {
        next = new Button();
        next.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(0), Insets.EMPTY)));
        next.setText("Next Question");
        //same behavior expected of the questionsTrack
        next.setOnAction(actionEvent -> {
            for(int i = 1; i< quiz.getQuestions().size(); i++) {
                textMark.setText("Quiz: " + quiz.getName() + "\n" + "Question " + i + "\n" + "Quiz threshold: " + quiz.getThreshold()); // additional info: status (answered/not), points (out of total)
                questionLabel.setText(quizQuestions.get(i).getQuestion());
                setAnswers(quizQuestions.get(i).getAnswers().size(), i);
            }
        });
        return next;
    }
    // method to finish quiz, save user input, check for correctness, send results to the server and display to user
    // also gateway to go back or repeat quiz
    private Button initiateSubmitButton(){
        submit = new Button();
        submit.setEffect(new DropShadow());
        submit.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, new CornerRadii(0), Insets.EMPTY)));
        ImageView submitImg = new ImageView(new Image("images/submit.png"));
        submit.setGraphic(submitImg);
        submit.setOnAction(e->{
            //on button submit action
            // here local checking of correctly answered questions can take place, since client already has the data
            // the number of correctly answered questions will be sent to server, along with info on quiz&user
            // concurrently, user can be informed about the results of the played quiz
            // options to repeat or go back to QuizBrowser should be offered
            // !! at least one question should be answered!!
            CheckCorrectAnswerC ch=new CheckCorrectAnswerC();
            System.out.println(selectedAnswer[0]);
            ch.checkAnswers(quiz,selectedAnswer);
        });
        return submit;
    }
    public Button initiateSkipButton(){
        skip = new Button();
        skip.setBackground(new Background(new BackgroundFill(Color.YELLOW,null, null)));
        ImageView skipimg = new ImageView(new Image("images/skip.png"));
        skip.setGraphic(skipimg);
        skip.setEffect(new DropShadow());
        skip.setOnAction(e->{
            //on button skip action
            // all questions skipped will have no user choice saved. hence, they will be marked as "wrong" by the system, when submit button is clicked
            // !! it should be possible to change choice before user clicked submit!!
        });
        return skip;
    }
    public Button initiateCancelButton(){
        cancel = new Button();
        cancel.setBackground(new Background(new BackgroundFill(Color.LIGHTCORAL, null, null)));
        ImageView cancelimg = new ImageView(new Image("images/cancelicon.png"));
        cancel.setGraphic(cancelimg);
        cancel.setEffect(new DropShadow());
        return cancel;
    }


}
