package gui;

import data.QuestionData;
import data.QuizData;
import guib.QuizBrowser;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
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
import requests.CheckCorrectAnswerC;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * PLay Quiz View display in the top of the page the progress and the pagination of Questions
 * Following by the QUestion Nr, points of that question and the count down timer of the Quiz
 * Question field that holds the Questions of the Quiz
 * Answers with radioButtons inviting the user to select a choice
 * Button to cancel the Quiz, Button next to the Question, Submit Button after the Quiz is completed to go to the results
 */
public class PlayQuizBox extends VBox {

    private static PlayQuizBox playQuizBox;
    private static int indexQuestion = 0;
    public QuizData quiz;
    public ArrayList<QuestionData> quizQuestions;
    public Map<String, String> selectedAnswerUser;
    public ArrayList<QuizData> allQuizzes = new ArrayList<>();
    private VBox answers;
    private Label textMark;
    private Text questionText;
    private Label minutes;
    private Label dot;
    private Label seconds;
    private Integer min;
    private Integer sec;
    private Button submit;
    private Button next;
    private Button cancel;
    private ProgressIndicator indicator;
    private Pagination trackquestions;
    private RadioButton choice_1;
    private RadioButton choice_2;
    private RadioButton choice_3;
    private RadioButton choice_4;
    private ToggleGroup groupOfAnswers;
    private Timeline time;
    private HBox questionsTrack;
    private HBox markQuestion;
    private HBox buttons;

    /**
     * Constructor of PlayQuiz to initiate all favaFx fields to display the Question, answers and progress of the Quiz
     */
    private PlayQuizBox() {
        quiz = QuizBrowser.getQuizToPlay();
        quizQuestions = quiz.getQuestions();
        // questions track with Pagination
        initiateQuestionTrack();
        //Question information: nr, points and Timer
        initiateQuestionMarkandQuestion();
        // Answer layout
        initiateAnswers();
        //Buttons
        initiateButtons();
        this.getChildren().addAll(questionsTrack, markQuestion, answers, buttons);
        selectedAnswerUser = new HashMap<>();
        fillQuestionChoicesWithdata(indexQuestion);
    }

    public static PlayQuizBox getPlayQuizBox() {
        if (playQuizBox == null) {
            playQuizBox = new PlayQuizBox();
        }
        return playQuizBox;
    }

    /**
     * resetting the Quiz play View
     */
    public static void reset() {
        playQuizBox = null;
        indexQuestion = 0;
    }

    /**
     * Track to navigate among questions. linked to the actual array of questions
     * pagination can be used to navigate backward and upwards to question with choices
     *
     * @return progress of answered questions and the current answered question
     */
    public void initiateQuestionTrack() {
        questionsTrack = new HBox(30);
        questionsTrack.setPadding(new Insets(20, 0, 20, 0));
        questionsTrack.setAlignment(Pos.CENTER);
        trackquestions = new Pagination();
        trackquestions.setPageCount(quiz.getQuestions().size());
        trackquestions.setMaxPageIndicatorCount(10);
        indicator = new ProgressIndicator(0.0);
        trackquestions.currentPageIndexProperty().addListener((observableValue, number, t1) -> {
            cleanChoicesSelection();
            fillQuestionChoicesWithdata(observableValue.getValue().intValue());
        });
        AnchorPane anchor = new AnchorPane();
        AnchorPane.setTopAnchor(trackquestions, 10.0);
        AnchorPane.setRightAnchor(trackquestions, 10.0);
        AnchorPane.setBottomAnchor(trackquestions, 10.0);
        AnchorPane.setLeftAnchor(trackquestions, 10.0);
        anchor.getChildren().addAll(trackquestions);
        questionsTrack.getChildren().addAll(indicator, anchor);
    }

    /**
     * Field where the nr, points of the questions along with timer of Quiz are displayed
     * Question Field where is holding the question from the Quiz
     */
    public void initiateQuestionMarkandQuestion() {
        markQuestion = new HBox(20);
        markQuestion.setPadding(new Insets(0, 0, 30, 0));
        VBox mark = new VBox();
        mark.setBackground(new Background(new BackgroundFill(Color.LIGHTSTEELBLUE, new CornerRadii(0), Insets.EMPTY)));
        mark.setPrefWidth(120);
        mark.setPadding(new Insets(10));
        textMark = new Label();
        textMark.setWrapText(true);
        textMark.setFont(Font.font("Times New Roman", 17));
        // timer settings
        TilePane tilePaneTImer = new TilePane();
        minutes = new Label();
        dot = new Label();
        seconds = new Label();
        minutes.setFont(Font.font("Constantia", FontWeight.SEMI_BOLD, 20));
        dot.setFont(Font.font("Constantia", FontWeight.SEMI_BOLD, 20));
        seconds.setFont(Font.font("Constantia", FontWeight.SEMI_BOLD, 20));
        minutes.setTextFill(Color.BLACK);
        dot.setTextFill(Color.BLACK);
        seconds.setTextFill(Color.BLACK);
        tilePaneTImer.getChildren().addAll(minutes, dot, seconds);
        countDownTimerQuiz();
        mark.getChildren().addAll(textMark, tilePaneTImer);
        mark.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        HBox question = new HBox();
        question.setBackground(new Background(new BackgroundFill(Color.LIGHTSKYBLUE, null, null)));
        question.setPrefWidth(625);
        question.setPrefHeight(100);
        questionText = new Text();
        questionText.setWrappingWidth(535);
        questionText.setFont(Font.font("Times New Roman", 20));
        question.getChildren().addAll(questionText);
        question.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        markQuestion.getChildren().addAll(mark, question);
    }

    /**
     * Layout of 4 questions with 4 RadioButtons grouped to be selected only one
     */
    public void initiateAnswers() {
        answers = new VBox(20);
        groupOfAnswers = new ToggleGroup();
        answers.setPrefWidth(650);
        answers.setBackground(new Background(new BackgroundFill(Color.LIGHTCYAN, null, null)));
        answers.setPadding(new Insets(20, 10, 10, 150));
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

    }

    /**
     * Create Cancel Button, Next Button and the Submit Quiz button
     */
    private void initiateButtons() {
        buttons = new HBox(160);
        buttons.setPadding(new Insets(40));
        submit = initiateSubmitButton();
        next = initiateNextButton();
        cancel = initiateCancelButton();
        buttons.setAlignment(Pos.CENTER);
        buttons.getChildren().addAll(cancel, next, submit);
    }

    /**
     * Next Button is created with in specific style that holds an image of the next >>
     *
     * @return next Button with the settings
     */
    private Button initiateNextButton() {
        next = new Button();
        next.setBackground(new Background(new BackgroundFill(Color.YELLOW, null, null)));
        ImageView skipimg = new ImageView(new Image("images/next.png"));
        next.setGraphic(skipimg);
        next.setEffect(new DropShadow());
        next.setOnAction(actionEvent -> {
            if (checkChoicesIfSelected()) {
                indexQuestion++;
                if (indexQuestion != quizQuestions.size()) {
                    cleanChoicesSelection();
                }
                fillQuestionChoicesWithdata(indexQuestion);
            } else {
                answers.setBorder(new Border(new BorderStroke(Color.FIREBRICK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                PauseTransition pause = new PauseTransition(Duration.seconds(3));
                pause.setOnFinished(actionEvent1 -> answers.setBorder(null));
                pause.play();
            }
        });
        return next;
    }


    /**
     * finish quiz, save user input, check for correctness, send results to the server and go to the Results of the Quiz
     * Disable play Quiz and Stop the Timer at certain point when user have been done
     *
     * @return submit Button settings
     */
    private Button initiateSubmitButton() {
        submit = new Button();
        submit.setDisable(true);
        submit.setEffect(new DropShadow());
        submit.setBackground(new Background(new BackgroundFill(Color.GREY, new CornerRadii(0), Insets.EMPTY)));
        ImageView submitImg = new ImageView(new Image("images/submit.png"));
        submit.setGraphic(submitImg);
        submit.setOnAction(e -> {
            time.stop();
            next.setDisable(true);
            cancel.setDisable(true);
            next.setBackground(new Background(new BackgroundFill(Color.GREY, new CornerRadii(0), Insets.EMPTY)));
            cancel.setBackground(new Background(new BackgroundFill(Color.GREY, new CornerRadii(0), Insets.EMPTY)));
            CheckCorrectAnswerC ch = new CheckCorrectAnswerC();
            ch.checkAnswers(quiz);
            MainPane.getMainPane().getTabs().add(CreateQuizResultTab.getQuizFinalResultTab());
        });
        return submit;
    }

    /**
     * Cancel Button implementation to quit the Quiz and to remove the play Quiz Tab from window
     *
     * @return Cancel Button with necessary functionality
     */
    public Button initiateCancelButton() {
        cancel = new Button();
        cancel.setBackground(new Background(new BackgroundFill(Color.LIGHTCORAL, null, null)));
        ImageView cancelimg = new ImageView(new Image("images/cancelicon.png"));
        cancel.setGraphic(cancelimg);
        cancel.setEffect(new DropShadow());
        cancel.setOnAction(actionEvent -> PlayQuizTab.reset());
        return cancel;
    }

    /**
     * On next Button or on the pagination handle The questions and answers are changed according to the order in the container fetch from DB
     *
     * @param indexofQuestion position of the question is passed to fill the question and answers field with text
     */
    private void fillQuestionChoicesWithdata(int indexofQuestion) {
        try {

            double progres = (indexQuestion * 1.0) / quizQuestions.size();
            indicator.setProgress(progres);
            if (indexQuestion == quizQuestions.size()) {
                indexQuestion = 0;
                submit.setDisable(false);
                submit.setBackground(new Background(new BackgroundFill(Color.YELLOWGREEN, new CornerRadii(0), Insets.EMPTY)));
            } else {

                this.textMark.setText("Question " + (indexofQuestion + 1) + "\nPoint : " + quizQuestions.get(indexofQuestion).getPoints());
                trackquestions.setCurrentPageIndex(indexofQuestion);
                this.questionText.setText(quizQuestions.get(indexofQuestion).getQuestion());
                try {
                    choice_1.setText(quizQuestions.get(indexofQuestion).getAnswers().get(0).getChoiceDescription());

                    choice_2.setText(quizQuestions.get(indexofQuestion).getAnswers().get(1).getChoiceDescription());

                    choice_3.setText(quizQuestions.get(indexofQuestion).getAnswers().get(2).getChoiceDescription());

                    choice_4.setText(quizQuestions.get(indexofQuestion).getAnswers().get(3).getChoiceDescription());

                    if (indexofQuestion < selectedAnswerUser.size()) {
                        if (choice_1.getText().equals(selectedAnswerUser.get(quizQuestions.get(indexofQuestion).getQuestion()))) {
                            System.out.println("Dataselectedby user " + selectedAnswerUser.get(quizQuestions.get(indexofQuestion).getQuestion()));
                            choice_1.setSelected(true);
                        }
                        if (choice_2.getText().equals(selectedAnswerUser.get(quizQuestions.get(indexofQuestion).getQuestion()))) {
                            choice_2.setSelected(true);
                        }
                        if (choice_3.getText().equals(selectedAnswerUser.get(quizQuestions.get(indexofQuestion).getQuestion()))) {
                            choice_3.setSelected(true);
                        }
                        if (choice_4.getText().equals(selectedAnswerUser.get(quizQuestions.get(indexofQuestion).getQuestion()))) {
                            choice_4.setSelected(true);
                        }
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Index out of boundary");
                }


                groupOfAnswers.selectedToggleProperty().addListener((observableValue, toggle, t1) -> {

                    if (checkChoicesIfSelected()) {

                        selectedAnswerUser.put(questionText.getText(), ((RadioButton) observableValue.getValue()).getText());

                    }

                });
            }

        } catch (IndexOutOfBoundsException e) {
            System.out.println(" I am out of boundary");

        }


    }

    /**
     * Sanitize the choice from the previous selected Question
     */
    private void cleanChoicesSelection() {
        choice_1.setSelected(false);
        choice_2.setSelected(false);
        choice_3.setSelected(false);
        choice_4.setSelected(false);
    }

    /**
     * Check if user have selected a choice from the Quiz
     *
     * @return false if any choices have not been selected
     */
    private boolean checkChoicesIfSelected() {
        return groupOfAnswers.getSelectedToggle() != null;
    }

    /**
     * Calculates how many questions have been answered correcttly by the user and holds the accumulated points
     * User accumulated points determined the view of the result window
     *
     * @return total accumulated points on the correct answers
     */
    public double calculationUserPoints() {
        double countPoints = 0.0;
        for (QuestionData quizQuestion : quizQuestions) {
            if (quizQuestion.getCorrectAnswer().equals(selectedAnswerUser.get(quizQuestion.getQuestion()))) {
                countPoints += quizQuestion.getPoints();
            }
        }
        return countPoints;
    }

    public double returnThreshold() {
        return quiz.getThreshold();
    }

    /**
     * Calculates the total amount of points of the quiz from the specific Question
     * Determine in percentage with user points if the Quiz was passed or failed
     *
     * @return total points of the Quiz
     */
    public double calculationTotalQuizPoints() {
        double countTotal = 0.0;
        for (QuestionData quizQuestion : quizQuestions) {
            countTotal += quizQuestion.getPoints();
        }
        return countTotal;
    }

    /**
     * Setting up the timer using the GUI Threat by using the Timeline
     * When the Time is Up the clock is stoped and an informative message that the time is out si displayed
     */
    private void countDownTimerQuiz() {

        time = new Timeline();
        time.setCycleCount(Timeline.INDEFINITE);
        min = quiz.getTimer() - 1;
        sec = 59;
        KeyFrame frame = new KeyFrame(Duration.seconds(1), actionEvent -> {
            if (sec != 0) {
                sec--;
                minutes.setText(String.format("%02d", min));
                dot.setText(":");
                seconds.setText(String.format("%02d", sec));
            } else {
                if (min <= 3) {
                    minutes.setTextFill(Color.FIREBRICK);
                    dot.setTextFill(Color.FIREBRICK);
                    seconds.setTextFill(Color.FIREBRICK);
                }
                min--;
                sec = 59;
                minutes.setText(String.format("%02d", min));
                seconds.setText(String.format("%02d", sec));
            }
            if (sec <= 0 && min <= 0) {
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

