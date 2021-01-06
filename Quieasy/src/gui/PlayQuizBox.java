package gui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

public class PlayQuizBox extends VBox {

    private static PlayQuizBox playQuizBox;
    private Label textmark;
    private Label questionlabel;
    private CheckBox[] answersCheck;
    private Button submit;
    private Button skip;
    private Button cancel;

    //private Integer seconds;
    private Label timer;
    private TilePane tilePaneTImer;
    Integer min = 0; //here get the ttime from DB -1 because the second is starting with 59
    private Integer sec = 59;
    Label minutes;
    Label dot;
    Label seconds;
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



    }

    public static PlayQuizBox getPlayQuizBox() {
        if(playQuizBox == null){
            playQuizBox = new PlayQuizBox();
        }
        return playQuizBox;
    }

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

    public HBox initiateQuestionMarkandQuestion(){
        HBox markQuestion = new HBox(10);
        //markQuestion.setPadding(new Insets(10));
        VBox mark = new VBox(5);
        mark.setBackground(new Background(new BackgroundFill(Color.LIGHTSTEELBLUE, new CornerRadii(0), Insets.EMPTY)));
        mark.setPrefWidth(120);
        mark.setPadding(new Insets(10));
        textmark = new Label("Text");
        textmark.setWrapText(true);
        textmark.setFont(Font.font("Times New Roman", 17));
       // mark.getChildren().addAll(textmark);
        mark.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
         //timer
        tilePaneTImer = new TilePane();
        minutes = new Label();
        dot = new Label();
        seconds = new Label();
        minutes.setFont(Font.font("Constantia", FontWeight.SEMI_BOLD, 20));
        dot.setFont(Font.font("Constantia",FontWeight.SEMI_BOLD, 20));
        seconds.setFont(Font.font("Constantia",FontWeight.SEMI_BOLD, 20));
        minutes.setTextFill(Color.BLACK);
        dot.setTextFill(Color.BLACK);
        seconds.setTextFill(Color.BLACK);

        tilePaneTImer.getChildren().addAll(minutes,dot, seconds);
        countDownTimerQuiz();



        mark.getChildren().addAll(textmark,tilePaneTImer);
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

    public VBox initiateAnswers(){
        VBox answers = new VBox(20);
        answers.setPrefWidth(650);
        answersCheck = new CheckBox[4];
        answers.setBackground(new Background(new BackgroundFill(Color.LIGHTCYAN, null, null)));
        answers.setPadding(new Insets(20, 10, 10, 140));
        for(int i= 0; i < answersCheck.length; i++){
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

    public Button initiateSubmitButton(){
        submit = new Button();
        submit.setEffect(new DropShadow());
        submit.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, new CornerRadii(0), Insets.EMPTY)));
        ImageView submitimg = new ImageView(new Image("images/submit.png"));
        submit.setGraphic(submitimg);
        submit.setOnAction(e->{
            //on button submit action
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

   private void countDownTimerQuiz(){

       Timeline time = new Timeline();
       time.setCycleCount(Timeline.INDEFINITE);
     //  time.stop();

       KeyFrame frame = new KeyFrame(Duration.seconds(1), actionEvent -> {
           if(sec != 0){
               sec--;
               minutes.setText(String.format("%02d", min));
               dot.setText(":");
               seconds.setText(String.format("%02d", sec));
           }else{
               if(min <= 3){
                   minutes.setTextFill(Color.FIREBRICK);
                   dot.setTextFill(Color.FIREBRICK);
                   seconds.setTextFill(Color.FIREBRICK);
               }
               min--;
               sec = 59;
               minutes.setText(String.format("%02d", min));
               seconds.setText(String.format("%02d", sec));
           }
           if(sec <=0 && min <=0){
               time.stop();
               Alert alert = new Alert(Alert.AlertType.WARNING);
               alert.setHeaderText("TIME's UP");
               alert.show();
               minutes.setText("TIME'");
               dot.setText("s");
               seconds.setText(" UP");
           }



       });
       time.getKeyFrames().add(frame);
       time.playFromStart();
   }





}
