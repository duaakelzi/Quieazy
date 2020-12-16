package gui;

import data.Answer;
import data.Question;
import data.Quiz;
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
    private Question newQuestionAdd;
    private Quiz data = CreateQuizBox.getCreateQuizBox().getQuiz();
    private ArrayList<Answer> answers;
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
        firstchoice.setText(data.getQuestions().get(index).getAnswers().get(0).getAnswer());
        firstradiobtn.setSelected(data.getQuestions().get(index).getAnswers().get(0).isCorrectAnswer());

        secondchoice.setText(data.getQuestions().get(index).getAnswers().get(1).getAnswer());
        secondradiobtn.setSelected(data.getQuestions().get(index).getAnswers().get(1).isCorrectAnswer());

        thirdchoice.setText(data.getQuestions().get(index).getAnswers().get(2).getAnswer());
        thirdradiobtn.setSelected(data.getQuestions().get(index).getAnswers().get(2).isCorrectAnswer());

        fourthchoice.setText(data.getQuestions().get(index).getAnswers().get(3).getAnswer());
        fourthradiobtn.setSelected(data.getQuestions().get(index).getAnswers().get(3).isCorrectAnswer());

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

    private Question createnewQuestion(){
        answers = new ArrayList<>();
        answers.add(new Answer(firstchoice.getText(), firstradiobtn.isSelected()));
        answers.add(new Answer(secondchoice.getText(), secondradiobtn.isSelected()));
        answers.add(new Answer(thirdchoice.getText(), thirdradiobtn.isSelected()));
        answers.add(new Answer(fourthchoice.getText(), fourthradiobtn.isSelected()));

        Question newQuestion = new Question(textQuestion.getText(), answers);

        data.addQuestion(newQuestion);
        return newQuestion;
    }

    private void saveEditQuestion(int index){
        data.getQuestions().get(index).setQuestion(textQuestion.getText());
        answers.get(0).setAnswer(firstchoice.getText());
        answers.get(0).setCorrectAnswer(firstradiobtn.isSelected());
        answers.get(1).setAnswer(secondchoice.getText());
        answers.get(1).setCorrectAnswer(secondradiobtn.isSelected());
        answers.get(2).setAnswer(thirdchoice.getText());
        answers.get(2).setCorrectAnswer(thirdradiobtn.isSelected());
        answers.get(3).setAnswer(fourthchoice.getText());
        answers.get(3).setCorrectAnswer(fourthradiobtn.isSelected());
        //update amswers Edit Button pushed
        data.getQuestions().get(index).setAnswers(answers);

    }


}
