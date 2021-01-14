package gui;

import data.ChoicesData;
import data.QuestionData;
import data.QuizData;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
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
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PlayQuizBox extends VBox {

    private static PlayQuizBox playQuizBox;
    private Label textMark;
    private Text questionText;
    private Label minutes;
    private Label dot;
    private Label seconds;
    private Integer min; //here get the ttime from DB -1 because the second is starting with 59
    private Integer sec;
    private RadioButton[] answersCheck;
    private Button submit;
    private Button next;
    private Button cancel;
    //missing: Quiz with its questionData and user info
    private QuizData quiz;
    private ArrayList<QuestionData> quizQuestions;
    private String[] selectedAnswer;
    private static int indexQuestion = 0;
    ProgressIndicator indicator;
    Pagination trackquestions;
    private final int NUMBEROFCHOICES = 4;
    RadioButton choice_1;
    RadioButton choice_2;
    RadioButton choice_3;
    RadioButton choice_4;
    ToggleGroup groupOfAnswers;
    VBox answers;
    Map<String, String> selectedAnswerUser = new HashMap<>();
  //  private ArrayList<ChoicesData> questionChoices;

    //constructor*****
    private PlayQuizBox() { //this shouldn't be empty but receive the quizData from the MyQuiz QuizData
        // quizToPlay
        quiz = CreateQuizBox.getCreateQuizBox().getQuiz();
        //quiz = quizToPlay;
        quizQuestions = quiz.getQuestions();
        //fetch all questions here
      //  fetchQuestions(quizToPlay);

        // questions track with Pagination
        HBox questionsTrack = initiateQuestionTrack();

        //Question information mark and the number of question
        HBox markQuestion = initiateQuestionMarkandQuestion();

        // Answer layout
        answers = initiateAnswers();

        //Buttons
        HBox buttons = initiateButtons();

        this.getChildren().addAll(questionsTrack, markQuestion, answers, buttons);
        fillQuestionChoicesWithdata(indexQuestion);

    }

    public static PlayQuizBox getPlayQuizBox() {
        if(playQuizBox == null){ playQuizBox = new PlayQuizBox();}
        return playQuizBox;
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
        trackquestions = new Pagination();
        trackquestions.setPageCount(quiz.getQuestions().size());
        trackquestions.setMaxPageIndicatorCount(10);
       // trackquestions.getStyleClass().add(Pagination.STYLE_CLASS_BULLET);
         indicator = new ProgressIndicator(0.0);
         trackquestions.currentPageIndexProperty().addListener(new ChangeListener<Number>() {
             @Override
             public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                 cleanChoicesSelection();
                 fillQuestionChoicesWithdata(observableValue.getValue().intValue());
//                 if(checkChoicesIfSelected()){
////                     String selectionUser = selectedAnswerUser.get(quizQuestions.get(observableValue.getValue().intValue()));
////                     System.out.println("What user have been selected" +selectionUser);
//
//                 }
                 System.out.println("Observale " + observableValue.getValue().intValue() + " number: " + number + " t1 :" + t1);
             }
         });
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
        //timer
        TilePane tilePaneTImer = new TilePane();
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
        //for first view only
        //textMark.setText("Question " + (indexQuestion+1) +  "\n Quiz points: " + quizQuestions.get(indexQuestion).getPoints()); // additional info: status (answered/not), points (out of total)
        mark.getChildren().addAll(textMark, tilePaneTImer);
        mark.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        HBox question = new HBox();
        question.setBackground(new Background(new BackgroundFill(Color.LIGHTSKYBLUE, null, null)));
        question.setPrefWidth(535);
        question.setPrefHeight(100);
        questionText = new Text();
        questionText.setWrappingWidth(535);
        questionText.setFont(Font.font("Times New Roman", 20));
        //for first view only
        //initiateDataPlayQuiz(indexQuestion);
        question.getChildren().addAll(questionText);
        question.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        markQuestion.getChildren().addAll(mark, question);
        return markQuestion;
    }

   // shows the choices for the question
    public VBox initiateAnswers(){
        VBox answers = new VBox(20);
        groupOfAnswers = new ToggleGroup();
        answers.setPrefWidth(650);
        //answersCheck = new RadioButton[NUMBEROFCHOICES];
       // selectedAnswer =new String[quiz.getQuestions().size()];
        answers.setBackground(new Background(new BackgroundFill(Color.LIGHTCYAN, null, null)));
        answers.setPadding(new Insets(20, 10, 10, 140));
       // for(int i= 0; i < NUMBEROFCHOICES; i++){
            //for first view only
           // RadioButton answer = new RadioButton();
            //answersCheck[i]=answer;
           // answer.setFont(Font.font("Times New Roman", 16));
//            System.out.println(answer.getText());
//            System.out.println(quizQuestions.get(indexQuestion).getAnswers().get(i).getChoiceDescription()); //test
            choice_1 = new RadioButton();
            choice_2 = new RadioButton();
            choice_3 = new RadioButton();
            choice_4 = new RadioButton();
            choice_1.setToggleGroup(groupOfAnswers);
            choice_2.setToggleGroup(groupOfAnswers);
            choice_3.setToggleGroup(groupOfAnswers);
            choice_4.setToggleGroup(groupOfAnswers);
            choice_1.setFont(Font.font("Times New Roman", 16));
            choice_2.setFont(Font.font("Times New Roman", 16));
            choice_3.setFont(Font.font("Times New Roman", 16));
            choice_4.setFont(Font.font("Times New Roman", 16));
            answers.getChildren().addAll(choice_1, choice_2, choice_3, choice_4);
//            answer.selectedProperty().addListener((observableValue, old_value, new_val) -> {
//               boolean selected =answer.isSelected();
//               if (selected){System.out.println("selected :"+answer.getText());
//               selectedAnswer[indexQuestion]=answer.getText();;
//               }
//               else {{System.out.println("not selected :"+answer.getText());}}//what should happen here?
//            });
      //  }
        return answers;
    }

    //depict the specific choice for the question
    private RadioButton showAnswer(ArrayList<ChoicesData> questionChoices, int pos){
        RadioButton answer = new RadioButton();
        answer.setFont(Font.font("Times New Roman", 16));
        answer.setText(questionChoices.get(pos).getChoiceDescription());
        //System.out.println("show"+questionChoices.get(pos).getChoiceDescription());
        return answer;
    }


    private HBox initiateButtons(){
        HBox buttons = new HBox(120);
        buttons.setPadding(new Insets(40));
        submit = initiateSubmitButton();
        next = initiateNextButton();
        cancel = initiateCancelButton();
        buttons.setAlignment(Pos.CENTER);
        buttons.getChildren().addAll(cancel, next, submit);
        return buttons;

    }
    //should change the fields with the next question
    private Button initiateNextButton() {
        next = new Button();
        next.setBackground(new Background(new BackgroundFill(Color.YELLOW,null, null)));
        ImageView skipimg = new ImageView(new Image("images/skip.png"));
        next.setGraphic(skipimg);
        next.setEffect(new DropShadow());
        //so far doesn't work. same behavior expected of the questionsTrack
        next.setOnAction(actionEvent -> {
                    if(checkChoicesIfSelected()) {
                        indexQuestion++;
                        if(indexQuestion != quizQuestions.size()){cleanChoicesSelection();}
                        fillQuestionChoicesWithdata(indexQuestion);


                    }else{

                        answers.setBorder(new Border(new BorderStroke(Color.FIREBRICK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                        PauseTransition pause = new PauseTransition(Duration.seconds(3));
                        pause.setOnFinished(actionEvent1 -> answers.setBorder(null));
                        pause.play();
                    }



//            for(int i = 1; i< quiz.getQuestions().size(); i++) {
//                textMark.setText("Quiz: " + quiz.getName() + "\n" + "Question " + i + "\n" + "Quiz threshold: " + quiz.getThreshold()); // additional info: status (answered/not), points (out of total)
//                questionText.setText(quizQuestions.get(i).getQuestion());
//                initiateAnswers(i);
//            }
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
            selectedAnswerUser.forEach((key, value) -> System.out.println(key + " the answer from " + value));
            //on button submit action
            // here local checking of correctly answered questions can take place, since client already has the data
            // the number of correctly answered questions will be sent to server, along with info on quiz&user
            // concurrently, user can be informed about the results of the played quiz
            // options to repeat or go back to QuizBrowser should be offered
            // !! at least one question should be answered!!
//            CheckCorrectAnswerC ch=new CheckCorrectAnswerC();
//            //System.out.println(selectedAnswer[0]);
//            Message i=ch.checkAnswers(quiz,selectedAnswer);
           // System.out.println("play Result"+i);
            MainPane.getMainPane().getTabs().add(QuizFinalResultTab.getQuizFinalResultTab());
           // MainPane.getMainPane().getTabs().add(new Tab("result",new CreateQuizResultBox(i)));
            CreateAddQuestionTab.getCreateAddQuestionTab().closeTab();

        });
        return submit;
    }

    public Button initiateCancelButton(){
        cancel = new Button();
        cancel.setBackground(new Background(new BackgroundFill(Color.LIGHTCORAL, null, null)));
        ImageView cancelimg = new ImageView(new Image("images/cancelicon.png"));
        cancel.setGraphic(cancelimg);
        cancel.setEffect(new DropShadow());
        return cancel;
    }

    private void fillQuestionChoicesWithdata(int indexofQuestion){
            try {

                double progres = (indexQuestion * 1.0) / quizQuestions.size();
                indicator.setProgress(progres);
                if (indexQuestion == quizQuestions.size()) {
                    indexQuestion = 0;
                } else {

                    this.textMark.setText("Question " + (indexofQuestion + 1) + "\nPoint : " + quizQuestions.get(indexofQuestion).getPoints());
                    trackquestions.setCurrentPageIndex(indexofQuestion);
                    this.questionText.setText(quizQuestions.get(indexofQuestion).getQuestion());
                   try {
                       choice_1.setText(quizQuestions.get(indexofQuestion).getAnswers().get(0).getChoiceDescription());

                       choice_2.setText(quizQuestions.get(indexofQuestion).getAnswers().get(1).getChoiceDescription());

                       choice_3.setText(quizQuestions.get(indexofQuestion).getAnswers().get(2).getChoiceDescription());

                       choice_4.setText(quizQuestions.get(indexofQuestion).getAnswers().get(3).getChoiceDescription());

                       if(indexofQuestion < selectedAnswerUser.size()) {
                           if (choice_1.getText().equals(selectedAnswerUser.get(quizQuestions.get(indexofQuestion).getQuestion()))) {
                               System.out.println("Dataselectedby user " + selectedAnswerUser.get(quizQuestions.get(indexofQuestion).getQuestion()));
                               choice_1.setSelected(true);
                           }
                           if (choice_2.getText().equals(selectedAnswerUser.get(quizQuestions.get(indexofQuestion).getQuestion()))){
                               choice_2.setSelected(true);
                           }
                           if (choice_3.getText().equals(selectedAnswerUser.get(quizQuestions.get(indexofQuestion).getQuestion()))){
                               choice_3.setSelected(true);
                           }
                           if (choice_4.getText().equals(selectedAnswerUser.get(quizQuestions.get(indexofQuestion).getQuestion()))){
                               choice_4.setSelected(true);
                           }
                       }
                   }catch (IndexOutOfBoundsException e){
                       System.out.println("Index out of boundary");
                   }


                    groupOfAnswers.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
                        @Override
                        public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
                            //  selectedAnswerUser.put(quizQuestions.get(trackquestions.getCurrentPageIndex()), t1.getUserData().toString() );
                            if (checkChoicesIfSelected()) {

                                selectedAnswerUser.put(questionText.getText(),((RadioButton)observableValue.getValue()).getText());

                            }

                        }
                    });

//                for (int i = 0; i < NUMBEROFCHOICES; i++) {
//                    answersCheck[i].setText(quizQuestions.get(indexofQuestion).getAnswers().get(i).getChoiceDescription());
//                    answersCheck[i].setSelected(false);
//                    RadioButton choicesOfQuestionsRadioButtons=answersCheck[i];
//                    choicesOfQuestionsRadioButtons.selectedProperty().addListener(new ChangeListener<Boolean>() {
//                        @Override
//                        public void changed(ObservableValue<? extends Boolean> observableValue, Boolean old_value, Boolean new_val) {
//                            boolean selected =choicesOfQuestionsRadioButtons.isSelected();
//                            if (selected){System.out.println("selected :"+choicesOfQuestionsRadioButtons.getText());
//                                selectedAnswer[indexQuestion]=choicesOfQuestionsRadioButtons.getText();;
//                            }
//                            else {{System.out.println("not selected :"+choicesOfQuestionsRadioButtons.getText());}}//what should happen here?
//                        }
//                    });
//                }

                }

            } catch (IndexOutOfBoundsException e) {
                System.out.println(" I am out of boundary");

            }



    }

    private void cleanChoicesSelection(){
        choice_1.setSelected(false);
        choice_2.setSelected(false);
        choice_3.setSelected(false);
        choice_4.setSelected(false);
    }

    private boolean checkChoicesIfSelected() {
        return groupOfAnswers.getSelectedToggle() != null;
    }


    private void countDownTimerQuiz(){

        Timeline time = new Timeline();
        time.setCycleCount(Timeline.INDEFINITE);
        min = quiz.getTimer()-1;
        sec = 59;
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
