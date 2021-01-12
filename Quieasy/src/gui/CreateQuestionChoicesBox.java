package gui;

import data.ChoicesData;
import data.QuestionData;
import data.QuizData;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;


public class CreateQuestionChoicesBox extends VBox {

    private static CreateQuestionChoicesBox createQuestionChoicesBox;
    private TextField firstchoice;
    private RadioButton firstradiobtn;
    private TextField secondchoice;
    private RadioButton secondradiobtn;
    private TextField thirdchoice;
    private RadioButton thirdradiobtn;
    private TextField fourthchoice;
    private RadioButton fourthradiobtn;
    private TextArea textQuestion;
    private QuestionData newQuestionAdd;
    private QuizData quiz = CreateQuizBox.getCreateQuizBox().getQuiz();
    private ArrayList<ChoicesData> choicesData;
    private int indexEditQuestion;
    //c'tor
    private CreateQuestionChoicesBox(){
        // initiate the Question area
        VBox question = initiateQuestion();
        // initiate the answer field with radiobutton
        VBox answer = initiateChoices();
        // initiate saveButton
        HBox button = initiateSaveQuestionBtn();
        this.getChildren().addAll(question, answer, button);
    }

    public static CreateQuestionChoicesBox getCreateQuestionChoicesBox(){
        if(createQuestionChoicesBox == null) createQuestionChoicesBox = new CreateQuestionChoicesBox();
        return createQuestionChoicesBox;
    }

    //getter and setter
    public QuestionData getNewQuestionAdd() {
        return newQuestionAdd;
    }

    //buttons
    private VBox initiateQuestion(){
        VBox questionVbox = new VBox(10);
        HBox questionPointsLabel = new HBox(470);
        questionVbox.setPadding(new Insets(20,30, 10, 30));
        Label questionlabel = new Label("Question");
        Label pointsLabel = new Label("Points");
        pointsLabel.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 16));
        questionPointsLabel.getChildren().addAll(questionlabel, pointsLabel);
        HBox questionPointsdata = new HBox(10);
        questionlabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        textQuestion = new TextArea();
        textQuestion.setPromptText("Enter the Question");
        textQuestion.setPrefColumnCount(10);
        textQuestion.setWrapText(true);
        textQuestion.setPrefHeight(100);
        textQuestion.setPrefWidth(600);
        Spinner<Integer> pointsSpinner = new Spinner<>();
        pointsSpinner.setEditable(true);

        SpinnerValueFactory <Integer> valueSpinner = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10000, 0);
        valueSpinner.setWrapAround(true);

        pointsSpinner.setValueFactory(valueSpinner);
        questionPointsdata.getChildren().addAll(textQuestion, pointsSpinner);

        questionVbox.getChildren().addAll(questionPointsLabel, questionPointsdata);

        return questionVbox;

    }

    private VBox initiateChoices(){
        VBox choices = new VBox(10);
        choices.setPadding(new Insets(10,30,0,30));
        HBox labelchoice = new HBox(470);
        Label choicelabel = new Label("Choices");
        choicelabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        Label correctlabel = new Label("Correct");
        correctlabel.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 16));
        labelchoice.getChildren().addAll(choicelabel, correctlabel);
        // first field of answer with radio buttom
        HBox fistanswer = new HBox(150);
        firstchoice = new TextField();
        firstchoice.setPromptText("Enter the 1 st answer");
        firstchoice.setMinWidth(400);
        firstradiobtn = new RadioButton();
        fistanswer.getChildren().addAll(firstchoice, firstradiobtn);

        //second field of answer with radio buttom
        HBox secondanswer = new HBox(150);
        secondchoice = new TextField();
        secondchoice.setPromptText("Enter the 2nd answer");
        secondchoice.setMinWidth(400);
        secondradiobtn = new RadioButton();
        secondanswer.getChildren().addAll(secondchoice, secondradiobtn);

        //third field of answer with radio buttom
        HBox thirdanswer = new HBox(150);
        thirdchoice = new TextField();
        thirdchoice.setPromptText("Enter the 3rd answer");
        thirdchoice.setMinWidth(400);
        thirdradiobtn = new RadioButton();
        thirdanswer.getChildren().addAll(thirdchoice, thirdradiobtn);

        // fourth field of answer with radio buttom
        HBox fourthanswer = new HBox(150);
        fourthchoice = new TextField();
        fourthchoice.setPromptText("Enter the 4th answer");
        fourthchoice.setMinWidth(400);
        fourthradiobtn = new RadioButton();
        fourthanswer.getChildren().addAll(fourthchoice, fourthradiobtn);

        choices.getChildren().addAll(labelchoice,fistanswer, secondanswer, thirdanswer, fourthanswer);

        return choices;
    }

    private HBox initiateSaveQuestionBtn(){
        HBox buttonHbox = new HBox();
        buttonHbox.setPadding(new Insets(50, 30, 0, 520));
        Button saveQ= new Button(" + ADD ");
        saveQ.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 16));
        saveQ.setOnAction(actionEvent -> {
                indexEditQuestion = CreateAddQuestionBox.getCreateAddQuestionBox().indexSelecteditem();
               if(indexEditQuestion >=0 && indexEditQuestion < quiz.getQuestions().size()){
                   //update question with new data
                    saveEditedQuestion(indexEditQuestion);
                    CreateAddQuestionBox.getCreateAddQuestionBox().fillTableObservableListWithQuestion();
               }else {
                   //create new question and add to the tableView
                   newQuestionAdd = createNewQuestion();
                   //add the newly-created question to the list of newQuestions
                   //not sure this works:
                   CreateAddQuestionBox.getNewQuestions().add(newQuestionAdd);
                   System.out.println("Size of array of new questions after adding: " + CreateAddQuestionBox.getNewQuestions().size());
                   //add to allQuestions
                   quiz.getQuestions().add(newQuestionAdd);
                   int index = quiz.getQuestions().indexOf(newQuestionAdd);
                   CreateAddQuestionBox.getCreateAddQuestionBox().fillTableObservableListWithQuestion();
               }
                CreateQuestionChoicesTab.getCreateQuestionChoicesTab().closeTab();
                //CreateAddQuestionBox.getCreateAddQuestionBox().getTableViewListQuestions().getSelectionModel().clearSelection(); // clear all the selection
                sanitizeInputs();

        });
        buttonHbox.getChildren().addAll(saveQ);

        return buttonHbox;
    }


    public void editQuestion(int index){
        textQuestion.setText(quiz.getQuestions().get(index).getQuestion());
        firstchoice.setText(quiz.getQuestions().get(index).getAnswers().get(0).getChoiceDescription());
        firstradiobtn.setSelected(quiz.getQuestions().get(index).getAnswers().get(0).isCorrect());

        secondchoice.setText(quiz.getQuestions().get(index).getAnswers().get(1).getChoiceDescription());
        secondradiobtn.setSelected(quiz.getQuestions().get(index).getAnswers().get(1).isCorrect());

        thirdchoice.setText(quiz.getQuestions().get(index).getAnswers().get(2).getChoiceDescription());
        thirdradiobtn.setSelected(quiz.getQuestions().get(index).getAnswers().get(2).isCorrect());

        fourthchoice.setText(quiz.getQuestions().get(index).getAnswers().get(3).getChoiceDescription());
        fourthradiobtn.setSelected(quiz.getQuestions().get(index).getAnswers().get(3).isCorrect());

    }

    private void sanitizeInputs(){
        textQuestion.clear();
        firstchoice.clear();
        secondchoice.clear();
        thirdchoice.clear();
        fourthchoice.clear();
        firstradiobtn.setSelected(false);
        secondradiobtn.setSelected(false);
        thirdradiobtn.setSelected(false);
        fourthradiobtn.setSelected(false);
    }

    //this one serves only the purpose of creating a question,
    // not yet making it persistent
    private QuestionData createNewQuestion(){
        choicesData = new ArrayList<>();
        choicesData.add(new ChoicesData(firstchoice.getText(), firstradiobtn.isSelected()));
        choicesData.add(new ChoicesData(secondchoice.getText(), secondradiobtn.isSelected()));
        choicesData.add(new ChoicesData(thirdchoice.getText(), thirdradiobtn.isSelected()));
        choicesData.add(new ChoicesData(fourthchoice.getText(), fourthradiobtn.isSelected()));
        //create questionData with necessary attributes

        return new QuestionData(textQuestion.getText(), choicesData);
    }

    private void saveEditedQuestion(int index){
        quiz.getQuestions().get(index).setQuestion(textQuestion.getText());
        choicesData.get(0).setChoiceDescription(firstchoice.getText());
        choicesData.get(0).setCorrect(firstradiobtn.isSelected());
        choicesData.get(1).setChoiceDescription(secondchoice.getText());
        choicesData.get(1).setCorrect(secondradiobtn.isSelected());
        choicesData.get(2).setChoiceDescription(thirdchoice.getText());
        choicesData.get(2).setCorrect(thirdradiobtn.isSelected());
        choicesData.get(3).setChoiceDescription(fourthchoice.getText());
        choicesData.get(3).setCorrect(fourthradiobtn.isSelected());
        //update answers Edit Button pushed
        quiz.getQuestions().get(index).setAnswers(choicesData);
        CreateAddQuestionBox.getUpdatedQuestions().add(quiz.getQuestions().get(index)); //for easier processing on the server side
    }


}
