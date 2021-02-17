package gui;

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

/**
 * CreateQuizResultBox class display the final results after a Quiz has been completed
 * 1.a) A Congratulation message if the accumulated points passed the threshold
 * 1.b) A encouragement message if the Quiz was failed obtaining less correct answers that thresholds requires
 * 2.A message about accumulated points from total points available
 * 4.Review the answered questions by comparing with correct result     *
 */
public class CreateQuizResultBox extends VBox {

    private static CreateQuizResultBox quizFinalResultBox;
    private static int indexOfQuestion;
    private VBox passFailVBox;
    private GridPane gridPaneResult;
    private Label points;
    private Text question;
    private Text answerUser;
    private Text correctAnswer;
    private HBox pointsHbox;
    private HBox finishHBox;
    private Label questionNR;
    private Label checkAnswer;

    /**
     * Constructor for Quiz results class
     */
    private CreateQuizResultBox() {
        indexOfQuestion = 0;
        passedFailQuiz();
        initiateResultQuestionsAnswer();
        finishQuizButton();
        this.getChildren().addAll(passFailVBox, gridPaneResult, finishHBox);
        fillResultAnsweredQuestions(indexOfQuestion);
    }

    /**
     * The final scene is created only once after completing the results and the user can not triger twise the results
     *
     * @return results after taking the quiz
     */
    public static CreateQuizResultBox getQuizFinalResultBox() {
        if (quizFinalResultBox == null) {
            quizFinalResultBox = new CreateQuizResultBox();
        }
        return quizFinalResultBox;
    }

    /**
     * Display according to the accumulated score either the congrats or a motivation message
     * Display the score out of total of total points of the quiz
     */
    private void passedFailQuiz() {
        double totalPoints = PlayQuizBox.getPlayQuizBox().calculationTotalQuizPoints();
        double userPoints = PlayQuizBox.getPlayQuizBox().calculationUserPoints();
        passFailVBox = new VBox();
        passFailVBox.setPadding(new Insets(20, 0, 10, 210));
        ImageView congradulation = new ImageView(new Image("images/congradulations.png"));
        passFailVBox.setPrefWidth(580);
        ImageView failer = new ImageView(new Image("images/fail.png"));
        Label yourScoreLabel = new Label("YOUR SCORE:  " + userPoints + " / " + totalPoints + " points" + "\n\tReview Your Choices");

        yourScoreLabel.setPadding(new Insets(10, 0, 10, 30));
        yourScoreLabel.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 26));

        if ((userPoints / totalPoints) * 100 >= PlayQuizBox.getPlayQuizBox().returnThreshold()) {
            passFailVBox.getChildren().addAll(congradulation, yourScoreLabel);
            yourScoreLabel.setTextFill(Color.GREEN);
        } else {
            passFailVBox.getChildren().addAll(failer, yourScoreLabel);
            yourScoreLabel.setTextFill(Color.FIREBRICK);
        }

    }

    /**
     * View where the Question, correct answer and user choice is displayed
     * Using next and back buttons user can go upward or backward to review his choices
     */
    private void initiateResultQuestionsAnswer() {
        gridPaneResult = new GridPane();
        gridPaneResult.setHgap(5);
        gridPaneResult.setVgap(20);
        pointsHbox = new HBox(250);
        points = new Label();
        points.setFont(Font.font("Currier", FontWeight.EXTRA_BOLD, 16));
        pointsHbox.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, null, null)));
        questionNR = new Label();
        questionNR.setFont(Font.font("Currier", FontWeight.EXTRA_BOLD, 16));
        pointsHbox.getChildren().addAll(points, questionNR);
        Button back = new Button("▲");
        back.setRotate(-90);
        back.setOnAction(actionEvent -> {
            try {
                --indexOfQuestion;
                if (indexOfQuestion >= 0) {
                    fillResultAnsweredQuestions(indexOfQuestion);
                } else {
                    indexOfQuestion = 0;
                }
            } catch (Exception exception) {
                exception.getCause();

            }
        });

        Button next = new Button("▼");
        next.setRotate(-90);
        next.setOnAction(actionEvent -> {
            try {

                ++indexOfQuestion;
                if (indexOfQuestion < PlayQuizBox.getPlayQuizBox().quizQuestions.size()) {
                    fillResultAnsweredQuestions(indexOfQuestion);
                } else {
                    indexOfQuestion = PlayQuizBox.getPlayQuizBox().quizQuestions.size() - 1;
                }
            } catch (Exception exception) {
                exception.getCause();
            }
        });
        HBox questionHbox = new HBox();
        questionHbox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        questionHbox.setBackground(new Background(new BackgroundFill(Color.LIGHTSKYBLUE, null, null)));
        question = new Text();
        question.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 18));
        question.setWrappingWidth(650);
        questionHbox.getChildren().add(question);
        questionHbox.setPrefSize(700, 80);
        Label correctAnswerQuiz = new Label("✔");
        answerUser = new Text();
        answerUser.setWrappingWidth(650);
        correctAnswer = new Text();
        correctAnswer.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, 18));
        correctAnswer.setFill(Color.GREEN);
        correctAnswer.setWrappingWidth(650);
        answerUser.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, 18));
        gridPaneResult.add(pointsHbox, 1, 0);
        gridPaneResult.add(back, 0, 1);
        gridPaneResult.add(questionHbox, 1, 1);
        gridPaneResult.add(next, 2, 1);
        gridPaneResult.add(correctAnswer, 1, 2);
        gridPaneResult.add(correctAnswerQuiz, 0, 2);
        gridPaneResult.add(answerUser, 1, 3);
        checkAnswer = new Label();
        gridPaneResult.add(checkAnswer, 0, 3);
    }

    /**
     * Method that fill text fields with question, correct answer and choice of the played Quiz
     *
     * @param index the position of the Question in the Quiz
     */
    private void fillResultAnsweredQuestions(int index) {
        String correctAnswerOfQuestion = PlayQuizBox.getPlayQuizBox().quizQuestions.get(index).getCorrectAnswer();
        String userSelectionAnswer = PlayQuizBox.getPlayQuizBox().selectedAnswerUser.get(PlayQuizBox.getPlayQuizBox().quizQuestions.get(index).getQuestion());
        questionNR.setText("<< Question " + (index + 1) + "/" + PlayQuizBox.getPlayQuizBox().quizQuestions.size() + " >>");
        if (correctAnswerOfQuestion.equals(userSelectionAnswer)) {
            points.setText("+ " + PlayQuizBox.getPlayQuizBox().quizQuestions.get(index).getPoints());
            pointsHbox.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, null, null)));
            checkAnswer.setText("✔");
            checkAnswer.setTextFill(Color.GREEN);
            checkAnswer.setFont(Font.font("Currier", FontWeight.SEMI_BOLD, 16));
        } else {
            points.setText("+ 0");
            pointsHbox.setBackground(new Background(new BackgroundFill(Color.LIGHTCORAL, null, null)));
            checkAnswer.setText("✗");
            checkAnswer.setTextFill(Color.FIREBRICK);
            correctAnswer.setFont(Font.font("Currier", FontWeight.SEMI_BOLD, 16));
        }
        question.setText(PlayQuizBox.getPlayQuizBox().quizQuestions.get(index).getQuestion());
        correctAnswer.setText(correctAnswerOfQuestion);
        answerUser.setText(userSelectionAnswer);
    }

    /**
     * Finish Button of the Quiz the close the tab of played Quiz
     */
    private void finishQuizButton() {
        finishHBox = new HBox();
        finishHBox.setPadding(new Insets(10, 0, 0, 650));
        Button finishQuizButton = new Button("Finish");
        finishQuizButton.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 18));
        finishQuizButton.setOnAction(actionEvent -> {
            PlayQuizTab.getPlayQuizTab();
            CreateQuizResultTab.getQuizFinalResultTab().closeTab();
        });
        finishHBox.getChildren().addAll(finishQuizButton);

    }


}
