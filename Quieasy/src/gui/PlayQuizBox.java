package gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class PlayQuizBox extends VBox {

    private static PlayQuizBox playQuizBox;
    private Label textmark;
    private Label questionlabel;
    private CheckBox[] answersCheck;
    private Button submit;
    private Button skip;
    private Button cancel;
    //missing: Quiz with its questionData and user info

    //constructor*****
    public PlayQuizBox() {

        // questions track with Pagination
        HBox questionstrack = initiateQuestionTrack();

        //Question information mark and the number of question
        HBox markQuestion = initiateQuestionMarkandQuestion();

        // Answer layout
        VBox answers = initiateAnswers();

        //Buttons
        HBox buttons = initiateButtons();

        this.getChildren().addAll(questionstrack, markQuestion, answers, buttons);
        //fetch all questions here

    }

    public static PlayQuizBox getPlayQuizBox() {
        if(playQuizBox == null){
            playQuizBox = new PlayQuizBox();
        }
        return playQuizBox;
    }

    //i believe, this one is the track to navigate among questions. it probably needs to be linked to the actual array of questions
    // yes
    public HBox initiateQuestionTrack(){
        HBox questionstrack = new HBox(10);
        questionstrack.setAlignment(Pos.CENTER);
        //questionstrack.setPadding(new Insets(10));
        Pagination trackquestions = new Pagination(20);
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

    // i'm guessing, this method should display the questions and choices? if so, we should load/fetch questions before or as
    // the first step
    public HBox initiateQuestionMarkandQuestion(){
        HBox markQuestion = new HBox(10);
        //markQuestion.setPadding(new Insets(10));
        VBox mark = new VBox();
        mark.setBackground(new Background(new BackgroundFill(Color.LIGHTSTEELBLUE, new CornerRadii(0), Insets.EMPTY)));
        mark.setPrefWidth(120);
        mark.setPadding(new Insets(10));
        textmark = new Label();
        textmark.setWrapText(true);
        textmark.setFont(Font.font("Times New Roman", 17));
        //....setText() create methods to populate with questions and call textmark attr from that method. analogously with questionLabel
        mark.getChildren().addAll(textmark);
        mark.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        HBox question = new HBox();
        question.setBackground(new Background(new BackgroundFill(Color.LIGHTSKYBLUE, null, null)));
        question.setPrefWidth(535);
        question.setPrefHeight(100);
        questionlabel = new Label();
        questionlabel.setPadding(new Insets(10));
        questionlabel.setWrapText(true);
        questionlabel.setFont(Font.font("Times New Roman", 20));
        question.getChildren().addAll(questionlabel);
        question.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        markQuestion.getChildren().addAll(mark, question);
        return markQuestion;
    }

    // i suppose, this one saves user choice? if yes, we need to have a way to save that input (in an array, i guess)
    // user input from here would be checked with actual array with correct answers
    public VBox initiateAnswers(){
        VBox answers = new VBox(20);
        answers.setPrefWidth(650);
        answersCheck = new CheckBox[4];
        answers.setBackground(new Background(new BackgroundFill(Color.LIGHTCYAN, null, null)));
        answers.setPadding(new Insets(20, 10, 10, 140));
        for(int i= 0; i < answersCheck.length; i++){ //are choices displayed here??
            CheckBox answer = answersCheck[i] = initiateAnswer();
            answers.getChildren().addAll(answer);
            answer.selectedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observableValue, Boolean old_value, Boolean new_val) {

                }
            });
        }


        return answers;
    }
    //not sure what this does
    public CheckBox initiateAnswer(){
        CheckBox answer = new CheckBox();
        answer.setFont(Font.font("Times New Roman", 16));
        return answer;
    }


    public HBox initiateButtons(){
        HBox buttons = new HBox(120);
        buttons.setPadding(new Insets(40));
        submit = initiateSubmitButton();
        skip = initiateSkipButton();
        cancel = initiateCancelButton();
        buttons.setAlignment(Pos.CENTER);
        buttons.getChildren().addAll(submit, skip, cancel);
        return buttons;

    }

    // method to finish quiz, save user input, check for correctness, send results to the server and display to user
    // also gateway to go back or repeat quiz
    public Button initiateSubmitButton(){
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
