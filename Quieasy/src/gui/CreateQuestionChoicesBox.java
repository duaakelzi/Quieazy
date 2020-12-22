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
import requests.QuestionC;

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
    private QuizData data = CreateQuizBox.getCreateQuizBox().getQuiz();
    private ArrayList<ChoicesData> choicesData;
    private int indexEditQuestion;
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


    private VBox initiateQuestion(){
        VBox questionVbox = new VBox(10);
        questionVbox.setPadding(new Insets(20,30, 10, 30));
        Label questionlabel = new Label("Question");
        questionlabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        textQuestion = new TextArea();
        textQuestion.setPromptText("Enter the Question");
        textQuestion.setPrefColumnCount(10);
        textQuestion.setPrefHeight(100);
        textQuestion.setPrefWidth(600);
        questionVbox.getChildren().addAll(questionlabel, textQuestion);

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
        buttonHbox.setPadding(new Insets(50, 30, 0, 490));
        Button saveQ= new Button("SAVE QUESTION");
        saveQ.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 16));
        saveQ.setOnAction(actionEvent -> {
                indexEditQuestion = CreateAddQuestionBox.getCreateAddQuestionBox().indexSelecteditem();
               if(indexEditQuestion >=0 && indexEditQuestion < data.getQuestions().size()){
                   //update question with new data
                    saveEditQuestion(indexEditQuestion);

                    CreateAddQuestionBox.getCreateAddQuestionBox().fillTableObservableListWithQuestion();


               }else {
                   //create new question and add to the tableView
                   newQuestionAdd = createnewQuestion();
                   int index = data.getQuestions().indexOf(newQuestionAdd);
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
        textQuestion.setText(data.getQuestions().get(index).getQuestion());
        firstchoice.setText(data.getQuestions().get(index).getAnswers().get(0).getChoiceDescription());
        firstradiobtn.setSelected(data.getQuestions().get(index).getAnswers().get(0).isCorrect());

        secondchoice.setText(data.getQuestions().get(index).getAnswers().get(1).getChoiceDescription());
        secondradiobtn.setSelected(data.getQuestions().get(index).getAnswers().get(1).isCorrect());

        thirdchoice.setText(data.getQuestions().get(index).getAnswers().get(2).getChoiceDescription());
        thirdradiobtn.setSelected(data.getQuestions().get(index).getAnswers().get(2).isCorrect());

        fourthchoice.setText(data.getQuestions().get(index).getAnswers().get(3).getChoiceDescription());
        fourthradiobtn.setSelected(data.getQuestions().get(index).getAnswers().get(3).isCorrect());

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

//    private QuestionData createnewQuestion(){
//        answers = new ArrayList<>();
//        answers.add(new Answer(firstchoice.getText(), firstradiobtn.isSelected()));
//        answers.add(new Answer(secondchoice.getText(), secondradiobtn.isSelected()));
//        answers.add(new Answer(thirdchoice.getText(), thirdradiobtn.isSelected()));
//        answers.add(new Answer(fourthchoice.getText(), fourthradiobtn.isSelected()));
//
//        QuestionData newQuestion = new QuestionData(textQuestion.getText(), answers);
//
//        data.addQuestion(newQuestion);
//        return newQuestion;
//    }
    //experiment -> questions aren't there?
    private QuestionData createnewQuestion(){
        choicesData = new ArrayList<>();
        choicesData.add(new ChoicesData(firstchoice.getText(), firstradiobtn.isSelected()));
        choicesData.add(new ChoicesData(secondchoice.getText(), secondradiobtn.isSelected()));
        choicesData.add(new ChoicesData(thirdchoice.getText(), thirdradiobtn.isSelected()));
        choicesData.add(new ChoicesData(fourthchoice.getText(), fourthradiobtn.isSelected()));

        QuestionData newQuestion = new QuestionData(textQuestion.getText(), choicesData);
        //make question persistent
        QuestionC.createnewQuestions(data, newQuestion);
        //ask to update Quiz
        data.addQuestion(newQuestion);
        return newQuestion;
    }

    private void saveEditQuestion(int index){
        data.getQuestions().get(index).setQuestion(textQuestion.getText());
        choicesData.get(0).setChoiceDescription(firstchoice.getText());
        choicesData.get(0).setCorrect(firstradiobtn.isSelected());
        choicesData.get(1).setChoiceDescription(secondchoice.getText());
        choicesData.get(1).setCorrect(secondradiobtn.isSelected());
        choicesData.get(2).setChoiceDescription(thirdchoice.getText());
        choicesData.get(2).setCorrect(thirdradiobtn.isSelected());
        choicesData.get(3).setChoiceDescription(fourthchoice.getText());
        choicesData.get(3).setCorrect(fourthradiobtn.isSelected());
        //update amswers Edit Button pushed
        data.getQuestions().get(index).setAnswers(choicesData);

    }


}
