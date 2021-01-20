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
import requests.UserRequests;

import java.util.ArrayList;

public class CreateQuestionChoicesBox extends VBox {
    /**
     * CreateQuestionChoicesBox class create the field where user can introduce the question with choices,
     * The window of Question Choices is opened either added new Question or is triggered by edited new Question from ListTable
     * The class holds a Text field of Question, 4 Choices with 4RadioButtons to select the correct answer and the save Button
     */

    private static CreateQuestionChoicesBox createQuestionChoicesBox;
    private SpinnerValueFactory <Integer> valueSpinner;
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
    private final QuizData quiz;
    private ArrayList<ChoicesData> choicesData;
    private int indexEditQuestion;
    private VBox questionVbox;
    private VBox choices;
    private HBox buttonHBox;

    /**
     * Constructor that initiates the Questiontext Field, 4 answer with Radio Button and save Button
     */
    private CreateQuestionChoicesBox(){
        // initiate the Question area
        initiateQuestionPointsFields();
        // initiate the answer field with radiobutton
        initiateChoices();
        // initiate saveButton
        initiateSaveQuestionBtn();
        this.getChildren().addAll(questionVbox, choices, buttonHBox);
        quiz = CreateQuizBox.getCreateQuizBox().getQuiz();
    }

    /**
     * Singleton objects that holds that View of Question is created once
     * @return the Window of Question with Answers field to insert or edit Question
     */

    public static CreateQuestionChoicesBox getCreateQuestionChoicesBox(){
        if(createQuestionChoicesBox == null) createQuestionChoicesBox = new CreateQuestionChoicesBox();
        return createQuestionChoicesBox;
    }

    /**
     * Create the Question Text Field with a spinner that holds the points to specific question
     */
    private void initiateQuestionPointsFields(){
        questionVbox = new VBox(10);
        Label questionlabel = new Label("Question");
        questionlabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        textQuestion = new TextArea();
        textQuestion.setPromptText("Enter the Question");
        textQuestion.setPrefColumnCount(10);
        textQuestion.setWrapText(true);
        textQuestion.setPrefHeight(100);
        textQuestion.setPrefWidth(600);
        HBox questionPointsLabel = new HBox(470);
        questionVbox.setPadding(new Insets(20,30, 10, 30));
        Label pointsLabel = new Label("Points");
        pointsLabel.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 16));
        questionPointsLabel.getChildren().addAll(questionlabel, pointsLabel);
        HBox questionPointsdata = new HBox(10);
        Spinner<Integer> pointsSpinner = new Spinner<>();
        pointsSpinner.setEditable(true);
        valueSpinner = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10000, 0);
        valueSpinner.setWrapAround(true);
        pointsSpinner.setValueFactory(valueSpinner);
        questionPointsdata.getChildren().addAll(textQuestion, pointsSpinner);
        questionVbox.getChildren().addAll(questionPointsLabel, questionPointsdata);

    }

    /**
     * Create 4 text field with radio Button to insert the choices for Question and to select the correct Question
     * @return VBox that holds all the Nodes input the choices and correct answer
     */
    private void initiateChoices(){
        choices = new VBox(10);
        choices.setPadding(new Insets(10,30,0,30));
        HBox labelchoice = new HBox(470);
        Label choicelabel = new Label("Choices");
        choicelabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        Label correctlabel = new Label("Correct");
        correctlabel.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 16));
        labelchoice.getChildren().addAll(choicelabel, correctlabel);
        // first field of answer with radio bottom
        HBox fistanswer = new HBox(150);
        firstchoice = new TextField();
        firstchoice.setPromptText("Enter the 1 st answer");
        firstchoice.setMinWidth(400);
        firstradiobtn = new RadioButton();
        fistanswer.getChildren().addAll(firstchoice, firstradiobtn);

        //second field of answer with radio bottom
        HBox secondanswer = new HBox(150);
        secondchoice = new TextField();
        secondchoice.setPromptText("Enter the 2nd answer");
        secondchoice.setMinWidth(400);
        secondradiobtn = new RadioButton();
        secondanswer.getChildren().addAll(secondchoice, secondradiobtn);

        //third field of answer with radio bottom
        HBox thirdanswer = new HBox(150);
        thirdchoice = new TextField();
        thirdchoice.setPromptText("Enter the 3rd answer");
        thirdchoice.setMinWidth(400);
        thirdradiobtn = new RadioButton();
        thirdanswer.getChildren().addAll(thirdchoice, thirdradiobtn);

        // fourth field of answer with radio bottom
        HBox fourthanswer = new HBox(150);
        fourthchoice = new TextField();
        fourthchoice.setPromptText("Enter the 4th answer");
        fourthchoice.setMinWidth(400);
        fourthradiobtn = new RadioButton();
        fourthanswer.getChildren().addAll(fourthchoice, fourthradiobtn);

        choices.getChildren().addAll(labelchoice,fistanswer, secondanswer, thirdanswer, fourthanswer);


    }

    /**
     * Create Save Button
     * 1. Save the new Question if the Question doen't exist
     * 2 Save the changes of the edit Question if the answer is the list
     */
    private void initiateSaveQuestionBtn(){
        buttonHBox = new HBox();
        buttonHBox.setPadding(new Insets(50, 30, 0, 490));
        Button saveQ= new Button("ADD");
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
                   newQuestionAdd.setUser(UserRequests.getCurrentUser());
                   //add the newly-created question to the list of newQuestions (for further persistence)
                   CreateAddQuestionBox.getNewQuestions().add(newQuestionAdd);
                   System.out.println("Size of array of new questions after adding: " + CreateAddQuestionBox.getNewQuestions().size());
                   //add to allQuestions where imported questions are integrated
             //      quiz.getQuestions().add(newQuestionAdd);
                   CreateAddQuestionBox.getAllQuestions().add(newQuestionAdd);
                   int index = quiz.getQuestions().indexOf(newQuestionAdd);
                   CreateAddQuestionBox.getCreateAddQuestionBox().fillTableObservableListWithQuestion();
               }
                CreateQuestionChoicesTab.getCreateQuestionChoicesTab().closeTab();
                sanitizeInputs();

        });
        buttonHBox.getChildren().addAll(saveQ);
    }

    /**
     * Fill in the Text field and choices when the user edit the Question from the table of questions
     * @param index of the question in the list
     */
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

    /**
     * Sanitizing the inputs after editing or inserting a new Question when the tab is closed
     */
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

    /**
     * Create a new Question from inserted data from user
     * @return the new Question
     */
    private QuestionData createNewQuestion(){
        choicesData = new ArrayList<>();
        choicesData.add(new ChoicesData(firstchoice.getText(), firstradiobtn.isSelected()));
        choicesData.add(new ChoicesData(secondchoice.getText(), secondradiobtn.isSelected()));
        choicesData.add(new ChoicesData(thirdchoice.getText(), thirdradiobtn.isSelected()));
        choicesData.add(new ChoicesData(fourthchoice.getText(), fourthradiobtn.isSelected()));
        //create questionData with necessary attributes
        QuestionData newQuestion = new QuestionData(textQuestion.getText(), choicesData,valueSpinner.getValue());
        return newQuestion;
    }

    /**
     * Save the edited Question in the table List
     * @param index is the position of question in the list
     */
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
