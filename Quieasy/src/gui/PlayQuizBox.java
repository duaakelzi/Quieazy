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
import javafx.scene.text.Text;
import requests.CheckCorrectAnswerC;

import java.util.ArrayList;

public class PlayQuizBox extends VBox {

    private static PlayQuizBox playQuizBox;
    private Label textMark;
    private Text questionText;
    private RadioButton[] answersCheck;
    private Button submit;
    private Button next;
    private Button cancel;
    //missing: Quiz with its questionData and user info
    private QuizData quiz;
    private ArrayList<QuestionData> quizQuestions;
    private String selectedAnswer[];
    private static int indexQuestion = 0;
    private ToggleGroup group;
    ProgressIndicator indicator;
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
        VBox answers = initiateAnswers();

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
        indicator = new ProgressIndicator(0.0);
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
        //textMark.setText("Question " + (indexQuestion+1) +  "\n Quiz points: " + quizQuestions.get(indexQuestion).getPoints()); // additional info: status (answered/not), points (out of total)
        mark.getChildren().addAll(textMark);
        mark.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        HBox question = new HBox();
        question.setBackground(new Background(new BackgroundFill(Color.LIGHTSKYBLUE, null, null)));
        question.setPrefWidth(535);
        question.setPrefHeight(100);
        questionText = new Text();
        questionText.setWrappingWidth(535);

        questionText.setFont(Font.font("Times New Roman", 20));
        //for first view only
        initiateDataPlayQuiz(indexQuestion);
        question.getChildren().addAll(questionText);
        question.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        markQuestion.getChildren().addAll(mark, question);


        return markQuestion;
    }

    // shows the choices for the question
    public VBox initiateAnswers(){
        VBox answers = new VBox(20);
        group = new ToggleGroup();
        answers.setPrefWidth(650);
        answersCheck = new RadioButton[4];
        selectedAnswer =new String[quiz.getQuestions().size()];
        answers.setBackground(new Background(new BackgroundFill(Color.LIGHTCYAN, null, null)));
        answers.setPadding(new Insets(20, 10, 10, 140));

        for(int i= 0; i < quizQuestions.get(indexQuestion).getAnswers().size(); i++){
            //for first view only
            RadioButton answer = showAnswer(quizQuestions.get(indexQuestion).getAnswers(), i);
            answersCheck[i]=answer;
            System.out.println(answer.getText());
            System.out.println(quizQuestions.get(indexQuestion).getAnswers().get(i).getChoiceDescription()); //test
            answersCheck[i].setToggleGroup(group);
            answers.getChildren().addAll(answersCheck[i]);
            answer.selectedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observableValue, Boolean old_value, Boolean new_val) {
                    boolean selected =answer.isSelected();


                    if (selected){System.out.println("selected :"+answer.getText());
                        selectedAnswer[indexQuestion]=answer.getText();;
                    }
                    else {{System.out.println("not selected :"+answer.getText());}}//what should happen here?
                }
            });
        }
        return answers;
    }

    //depict the specific choice for the question
    private RadioButton showAnswer(ArrayList<ChoicesData> questionChoices, int pos){
        RadioButton answer = new RadioButton();
        answer.setFont(Font.font("Times New Roman", 16));

        answer.setText(questionChoices.get(pos).getChoiceDescription());
        System.out.println("show"+questionChoices.get(pos).getChoiceDescription());
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

            try {

                indexQuestion++;
                double progres = (indexQuestion*1.0)/quizQuestions.size();
                indicator.setProgress(progres);
                if (indexQuestion==quizQuestions.size()){indexQuestion=0;}

                this.textMark.setText("Question " + (indexQuestion + 1) + "\n Point :" + quizQuestions.get(indexQuestion).getPoints());

                this.questionText.setText(quizQuestions.get(indexQuestion).getQuestion());
                for (int i = 0; i < 4; i++) {
                    answersCheck[i].setText(quizQuestions.get(indexQuestion).getAnswers().get(i).getChoiceDescription());
                    answersCheck[i].setSelected(false);
                }
                indicator.setProgress(Double.valueOf(indexQuestion*100.0/quizQuestions.size()));






            }catch (IndexOutOfBoundsException e){
                System.out.println(" I am out of boundary");

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

    public Button initiateCancelButton(){
        cancel = new Button();
        cancel.setBackground(new Background(new BackgroundFill(Color.LIGHTCORAL, null, null)));
        ImageView cancelimg = new ImageView(new Image("images/cancelicon.png"));
        cancel.setGraphic(cancelimg);
        cancel.setEffect(new DropShadow());
        return cancel;
    }

    private void initiateDataPlayQuiz(int pos){

        this.textMark.setText("Question " + (pos+1) + "\n Points: " + quizQuestions.get(pos).getPoints());
        this.questionText.setText(quizQuestions.get(pos).getQuestion());

    }


}
