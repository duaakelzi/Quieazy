package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class QuizFinalResultBox extends VBox {
    private static QuizFinalResultBox quizFinalResultBox;
    private VBox passFailVBox;
    private GridPane gridPaneResult;
    private Label points;
    private Text question;
    private Text answerUser;
    private Text correctAnswer;
    private Button next;
    private Button back;
    private HBox pointsHbox;
    private Button cancelResults;
    private Button finishQuizButton;
    private HBox finishHBox;
    private static int indexOfQuestion = 0;
    private Label questionNR;
    private Label checkAnswer;

    private QuizFinalResultBox(){

        passedFailQuiz();
        initiateResultQuestionsAnswer();
        finishQuizButton();
        this.getChildren().addAll(passFailVBox, gridPaneResult, finishHBox);
        fillResultAnsweredQuestions(indexOfQuestion);
    }

    public static QuizFinalResultBox getQuizFinalResultBox() {
        if(quizFinalResultBox == null){
            quizFinalResultBox = new QuizFinalResultBox();
        }
        return quizFinalResultBox;
    }


    private void passedFailQuiz(){
        double totalPoints = PlayQuizBox.getPlayQuizBox().calculationTotalQuizPoints();
        double userPoints = PlayQuizBox.getPlayQuizBox().calculationUserPoints();
        passFailVBox = new VBox();
        passFailVBox.setPadding(new Insets(0,0,10, 130));
        ImageView congradulation = new ImageView(new Image("images/congradulations.png"));
        passFailVBox.setPrefWidth(600);
        ImageView failer = new ImageView(new Image("images/fail.png"));
        Label yourScoreLabel = new Label("YOUR SCORE:  " + userPoints + " / " + totalPoints +" points" + "\n\tReview Your Choices");

        yourScoreLabel.setPadding(new Insets(0,0,0,20));
        yourScoreLabel.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 26));

        if((userPoints/totalPoints)*100>= CreateQuizBox.getCreateQuizBox().getQuiz().getThreshold()) {
            passFailVBox.getChildren().addAll(congradulation, yourScoreLabel);
            yourScoreLabel.setTextFill(Color.GREEN);
        }else{
            passFailVBox.getChildren().addAll(failer, yourScoreLabel);
            yourScoreLabel.setTextFill(Color.FIREBRICK);
        }

    }

    private void initiateResultQuestionsAnswer(){
        gridPaneResult = new GridPane();
        gridPaneResult.setHgap(5);
        gridPaneResult.setVgap(10);
        pointsHbox = new HBox(200);
        points = new Label();
        points.setFont(Font.font("Currier", FontWeight.EXTRA_BOLD, 16));
        pointsHbox.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, null, null)));
        questionNR = new Label();
        questionNR.setFont(Font.font("Currier", FontWeight.EXTRA_BOLD, 16));
        pointsHbox.getChildren().addAll(points, questionNR);
        back = new Button("▲");
        // back.setMinWidth(70);
        back.setRotate(-90);
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent actionEvent) {
                try {
                   // next.setDisable(false);
                    --indexOfQuestion;

                    if(indexOfQuestion >=0) {

                        fillResultAnsweredQuestions(indexOfQuestion);
                    }else {
                       indexOfQuestion = 0;

                    }
                } catch (Exception exception){
                    System.out.println("Quiz from the begining");

                }
            }

        });

        next = new Button("▼");
        next.setRotate(-90);
        next.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {

                    ++indexOfQuestion;
                    if(indexOfQuestion < PlayQuizBox.getPlayQuizBox().quizQuestions.size()) {
                        fillResultAnsweredQuestions(indexOfQuestion);
                    }else{
                        indexOfQuestion = PlayQuizBox.getPlayQuizBox().quizQuestions.size()-1;
                    }
                } catch (Exception exception){
                    System.out.println("At the end of Quiz");
                }

            }
        });
        // next.setMinWidth(70);
        HBox questionHbox = new HBox();
        questionHbox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        questionHbox.setBackground(new Background(new BackgroundFill(Color.LIGHTSKYBLUE, null, null)));
        question = new Text();
        question.setWrappingWidth(550);
        questionHbox.getChildren().add(question);
        questionHbox.setPrefSize(600, 70);
        Label correctAnswerQuiz = new Label("✔");
        answerUser = new Text();
        answerUser.setWrappingWidth(550);
        correctAnswer = new Text();
        correctAnswer.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, 16));
        correctAnswer.setFill(Color.GREEN);
        correctAnswer.setWrappingWidth(550);
        answerUser.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, 16));
        gridPaneResult.add(pointsHbox, 1, 0);
        gridPaneResult.add(back, 0, 1);
        gridPaneResult.add(questionHbox, 1, 1);
        gridPaneResult.add(next, 2, 1);
        gridPaneResult.add(correctAnswer, 1, 2);
        gridPaneResult.add(correctAnswerQuiz,0, 2 );
        gridPaneResult.add(answerUser, 1, 3);
        checkAnswer = new Label();
        gridPaneResult.add(checkAnswer, 0, 3);
    }

    private void fillResultAnsweredQuestions(int index) {
        String correctAnswerOfQuestion = PlayQuizBox.getPlayQuizBox().quizQuestions.get(index).getCorrectAnswer();
        String userSelectionAnswer = PlayQuizBox.getPlayQuizBox().selectedAnswerUser.get(PlayQuizBox.getPlayQuizBox().quizQuestions.get(index).getQuestion());
        questionNR.setText("<< Question " + (index + 1) + "/" + PlayQuizBox.getPlayQuizBox().quizQuestions.size() + " >>");
        if (correctAnswerOfQuestion.equals(userSelectionAnswer)) {
            points.setText("+ " + PlayQuizBox.getPlayQuizBox().quizQuestions.get(index).getPoints());
            pointsHbox.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, null, null)));
            checkAnswer.setText("✔");
            checkAnswer.setTextFill(Color.GREEN);

        } else {
            points.setText("+ 0");
            pointsHbox.setBackground(new Background(new BackgroundFill(Color.LIGHTCORAL, null, null)));
            checkAnswer.setText("✗");
            checkAnswer.setTextFill(Color.FIREBRICK);

            //back.setDisable(true);

        }

        question.setText(PlayQuizBox.getPlayQuizBox().quizQuestions.get(index).getQuestion());
        correctAnswer.setText(correctAnswerOfQuestion);
        answerUser.setText(userSelectionAnswer);
    }

    private void finishQuizButton(){
        finishHBox = new HBox();
        finishHBox.setPadding(new Insets(10,0,0,500));
        finishQuizButton = new Button("Finish");
        finishQuizButton.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 18));
        finishQuizButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                PlayQuizTab.getPlayQuizTab().closeTab();
                QuizFinalResultTab.getQuizFinalResultTab().closeTab();
            }
        });
        finishHBox.getChildren().addAll(finishQuizButton);

    }




}
