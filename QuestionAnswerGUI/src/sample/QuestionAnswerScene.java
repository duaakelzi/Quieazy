package sample;

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
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class QuestionAnswerScene extends SceneMaker{
    private Label textmark;
    private Label questionlabel;
    private CheckBox[] answersCheck;
    private Button submit;
    private Button skip;
    private Button cancel;

    public QuestionAnswerScene(Stage stage, Controller controller) throws FileNotFoundException {
        super(controller, 900, 650);
        Background background = new Background(new BackgroundFill(Color.LIGHTCYAN, null, null));
        //top menu bar
        BorderPane topMenuBar = initiateTopMenuBar();
        // questions track with Pagination
        HBox questionstrack = initiateQuestionTrack();
        //Question information mark and the number of question
        HBox markQuestion = initiateQuestionMarkandQuestion();
        // Answer layout
        VBox answers = initiateAnswers();
        //Buttons
        HBox buttons = initiateButtons();

        //answers.getChildren().addAll(answer1, answer2, answer3, answer4, buttons);
        VBox root = new VBox(0);
        root.getChildren().addAll(topMenuBar,questionstrack, markQuestion, answers, buttons);
        root.setBackground(background);
        setRoot(root);
    }

    public BorderPane initiateTopMenuBar(){
        MenuBar rightBar = new MenuBar();
        Menu account = new Menu("Chan Li");
        MenuItem item1right =new MenuItem("Settings");
        MenuItem item2right = new MenuItem("Help");
        MenuItem item3right = new MenuItem("Log Out");
        account.getItems().addAll(item1right,item2right, item3right);
        ImageView userimg = new ImageView(new Image("images/user.png"));
        account.setGraphic(userimg);
        rightBar.getMenus().addAll(account);

        MenuBar leftBar = new MenuBar();
        Menu menu = new Menu();
        MenuItem item1left = new MenuItem("Home");
        MenuItem item2left = new MenuItem("Dashboard");
        MenuItem item3left = new MenuItem("Quizzes");
        MenuItem item4left = new MenuItem("Catalog");
        menu.getItems().addAll(item1left,item2left, item3left, item4left);

        ImageView menuimg = new ImageView(new Image("images/menu.png"));
        menu.setGraphic(menuimg);
        leftBar.getMenus().addAll(menu);
        Region space = new Region();
        space.getStyleClass().add("menu-bar");
        HBox.setHgrow(space, Priority.SOMETIMES);
        HBox menuBar = new HBox(leftBar, space, rightBar);
        BorderPane topMenuBar = new BorderPane();
        topMenuBar.setTop(menuBar);
        return topMenuBar;
    }

    public HBox initiateQuestionTrack(){
        HBox questionstrack = new HBox(30);
        questionstrack.setAlignment(Pos.CENTER);
        questionstrack.setPadding(new Insets(10));
        Pagination trackquestions = new Pagination(20);
        trackquestions.getStyleClass().add(Pagination.STYLE_CLASS_BULLET);
        ProgressIndicator indicator = new ProgressIndicator(0.45);
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
        markQuestion.setPadding(new Insets(10));
        VBox mark = new VBox();
        mark.setBackground(new Background(new BackgroundFill(Color.LIGHTSTEELBLUE, new CornerRadii(0), Insets.EMPTY)));
        mark.setPrefWidth(900*0.2);
        mark.setPadding(new Insets(10));
        textmark = new Label();
        textmark.setWrapText(true);
        textmark.setFont(Font.font("Times New Roman", 17));
        mark.getChildren().addAll(textmark);
        mark.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        HBox question = new HBox();
        question.setBackground(new Background(new BackgroundFill(Color.LIGHTSKYBLUE, null, null)));
        question.setPrefWidth(900*0.8);
        question.setPrefHeight(150);
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
        VBox answers = new VBox(30);
        answers.setPrefWidth(900*0.8);
        answersCheck = new CheckBox[4];
        answers.setBackground(new Background(new BackgroundFill(Color.LIGHTCYAN, null, null)));
        answers.setPadding(new Insets(20, 10, 10, 900*0.25));
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
        answer.setFont(Font.font("Times New Roman", 20));
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
            controller.onClickSubmit();
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
            controller.onClickSubmit();
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


    public Label getTextmark() {
        return textmark;
    }

    public Label getQuestionlabel() {
        return questionlabel;
    }

    public CheckBox[] getAnswersCheck() {
        return answersCheck;
    }


}
