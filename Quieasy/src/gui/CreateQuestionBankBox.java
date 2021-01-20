package gui;

import data.FilterDataQuestionBank;
import data.QuestionData;
import data.QuizData;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;
import requests.QuestionRequests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateQuestionBankBox extends VBox {
    /**
     * CreateQuestionBank class represent a filter that gets all Questions from DB
     * and passing throw the filter matching the key words introduced by user and are ready to be added to the Quiz
     * Class holds a Text field holds the key words of user and a ListView of questions that have been selected
     */
    private static CreateQuestionBankBox createQuestionBankBox;
    private ListView<FilterDataQuestionBank> listQuestions = new ListView<>();
    private ObservableList<FilterDataQuestionBank> dataQuestionObservaleList; // contain data from all quizzes ready to be filtered
    private ObservableList<FilterDataQuestionBank> filterDataQuestionBankObservableList; //contain filtered data only
    private static QuizData quiz;
    private List<String> inputSearchText;
    private List<String> questionsDB;
    private Label warrningNotFound;
    private TextField searchKeyWords;
    private HBox searchFieldHBox;
    private HBox warningNotFound;
    private StackPane listViewData;
    private HBox saveButton;

    //***************************** fetch data from DB here********************************** return questionData with question owner
    public static ArrayList<QuestionData> allQuestionsData; //this holds all data from DB before beeing transformed in FilteredData Objects
    //***************************************************************************************
    // test data this should be from database

    ArrayList<QuestionData> questionDataArrayList = new ArrayList<>();


    /**
     * Constructor that initializes the Filter text Field, Button and the ListView where will be displayed the filtered data
     */
    private CreateQuestionBankBox() {

        initiateSearchField();
        initiateWarrningMessageNotFound();

        initiateListViewData();

        initiateSaveButton();

        this.getChildren().addAll(searchFieldHBox, warningNotFound, listViewData, saveButton);

        dataQuestionObservaleList = FXCollections.observableArrayList(); // contain data from all quizes ready to be filtered
        filterDataQuestionBankObservableList = FXCollections.observableArrayList(); //filtered data to be displayed to the user
        quiz = CreateQuizBox.getCreateQuizBox().getQuiz();// new quiz created by user to be added the questions
//****************************************************************************

        allQuestionsData = QuestionRequests.fetchAllQuestions();
        System.out.println(allQuestionsData.get(0).getQuestion());

        fillObservaleListWithData(allQuestionsData);
    }

    /**
     * Singleton object of Bank Question class that is created only once and the user can not trigger to open multiple windows
     * of Bank Question, in order to keep the data consistent.
     * @return the window where the user can filter the questions to be added to the Quiz
     */
    public static CreateQuestionBankBox getCreateQuestionBankBox() {
        if (createQuestionBankBox == null) {
            createQuestionBankBox = new CreateQuestionBankBox();
        }
        return createQuestionBankBox;

    }

    /**
     * Create a text Field with Button
     * The user in inserting the keywords of Question context what he/she is looking for and push the button search
     * The filter is splitting the key words from user and using Regex is searching in the questions where are 100% match
     * An FXCollection List is filled in ready to be displyed in the listView of found Questions
     */
    private void initiateSearchField(){
        searchFieldHBox = new HBox(30);
        searchFieldHBox.setPadding(new Insets(30, 0, 0, 50));
        Label searchLabel = new Label("Search");
        searchLabel.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 24));
        searchKeyWords = new TextField();
        searchKeyWords.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 18));
        searchKeyWords.setMinWidth(400);
        searchKeyWords.setPromptText("Search");
        Button searchButton = new Button("➔");
        searchButton.setOnAction(actionEvent -> {
            if(listQuestions.getItems() != null){listQuestions.getItems().clear();}
            // in case if new Quiz is added not to hold the data from previous selected quiz
            if(dataQuestionObservaleList.size() == 0) {fillObservaleListWithData(allQuestionsData);}
            if(filterDataQuestionBankObservableList.size() != 0){filterDataQuestionBankObservableList.clear();}
            String filterSearch = searchKeyWords.getText();
            inputSearchText = Arrays.asList(filterSearch.toLowerCase().split(" "));
            if(filterSearch.length() == 0){
                warrningNotFound.setText("Question Not Found");
                PauseTransition pause = new PauseTransition(Duration.seconds(1));
                pause.setOnFinished(actionEvent1 -> warrningNotFound.setText(null));
                pause.play();

            }else{
                for (FilterDataQuestionBank filterDataQuestionBank : dataQuestionObservaleList) {
                    questionsDB = Arrays.asList(filterDataQuestionBank.getQuestions().getQuestion().toLowerCase().split("[\\s.,:;]+"));
                    if (questionsDB.containsAll(inputSearchText)){
                        filterDataQuestionBankObservableList.add(filterDataQuestionBank);
                    }

                }
                if(filterDataQuestionBankObservableList.size() == 0){
                    warrningNotFound.setText("Question Not Found");
                    PauseTransition pause = new PauseTransition(Duration.seconds(1));
                    pause.setOnFinished(actionEvent1 -> warrningNotFound.setText(null));
                    pause.play();
                }
                listQuestions.setItems(filterDataQuestionBankObservableList);

                System.out.println("The size of my list is : " + filterDataQuestionBankObservableList.size());
            }

        });


        searchButton.setFont(Font.font(14));
        searchFieldHBox.getChildren().addAll(searchLabel, searchKeyWords, searchButton);

    }

    /**
     * In case if the key words doesn't match with any questions from DB or an empty field is left
     * an warning message is displayed to the user with containt="Question Not Found"
     */
    private void initiateWarrningMessageNotFound(){
        warningNotFound = new HBox(200);
        warrningNotFound = new Label();
        warrningNotFound.setPadding(new Insets(5, 0, 5, 175));
        warrningNotFound.setFont(Font.font("Courier New",FontWeight.SEMI_BOLD, 16));
        warrningNotFound.setTextFill(Color.FIREBRICK);
        warningNotFound.getChildren().add(warrningNotFound);

    }

    /**
     * Method to display the question in the ListView
     * @return the view of question
     */
    private void initiateListViewData(){
        listViewData = new StackPane();
        listViewData.setMaxWidth(665);
        listViewData.setMaxHeight(310);
        listQuestions.setItems(filterDataQuestionBankObservableList);
        listQuestions.setCellFactory(questionDataListView -> new FilteredData());
        listViewData.getChildren().addAll(listQuestions);
    }

    /**
     * Save Button to store all selected questions to the Quiz
     */
    private void initiateSaveButton(){
        saveButton = new HBox();
        Button save = new Button("▼ SAVE QUESTION");
        save.setOnAction(actionEvent -> {

            CreateAddQuestionBox.getCreateAddQuestionBox().fillTableObservableListWithQuestion();
            dataQuestionObservaleList.clear();
            filterDataQuestionBankObservableList.clear();
            searchKeyWords.clear();
            CreateQuestionBankTab.getCreateQuestionBankTab().closeTab();

        });
        saveButton.setPadding(new Insets(10, 0, 0, 535));
        saveButton.getChildren().addAll(save);
    }

    /**
     * Fill the FXCollection observale list with Filtered Questions to be displayed
     * @param allQuestionsData
     */
    public void fillObservaleListWithData(ArrayList<QuestionData> allQuestionsData) {
        for (int i = 0; i < allQuestionsData.size(); i++) {

//                dataQuestionObservaleList.addAll(new FilterDataQuestionBank(allQuestionsData.get(i).getQuestion(),
//                        allQuestionsData.get(i).getOwner()
////                        i));// instead of study program put course
////
//
//        }
        }
    }

    /**
     * Inner Class that fill List View with filtered questions
     */
    public static class FilteredData extends ListCell<FilterDataQuestionBank> {
        //FilteredData filteredData;

        private GridPane dataGrid;

        //constructor
        private FilteredData(){
            dataGrid = new GridPane();
        }


        @Override
        protected void updateItem(FilterDataQuestionBank questionData, boolean empty) {
            super.updateItem(questionData, empty);
            if (empty || questionData == null ) {
                setGraphic(null);
            }else {
                questionData.getAddButton().setOnAction(actionEvent -> {
//                            //here save the chosen question to be added to Array Question ArrayList//
                    questionData.getDataGrid().setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
                    // add imported question to oldQuestions method in the CreateAddQuestionBox
                    CreateAddQuestionBox.getOldQuestions().add(questionData.getQuestions());
//                    CreateQuestionBankBox.quiz.addSingleQuestion(CreateQuestionBankBox.allQuizesData
//                            .get(Integer.parseInt(questionData.getIndexQuizHide().getText()))
//                            .getQuestions().get(Integer.parseInt(questionData.getIndexQuestionHide().getText())));
                    //add to the almighty question array which will be displayed to user
                    CreateAddQuestionBox.getAllQuestions().add(questionData.getQuestions());
                    questionData.getAddButton().setDisable(true);
                    questionData.getDelButton().setDisable(false);

                });
                questionData.getDelButton().setOnAction(actionEvent -> {
                    System.out.println(questionData.getQuestions().getQuestion());
                    dataGrid.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                    // delete fro the temp array (indexOf(questionData.getId))
                    CreateAddQuestionBox.getOldQuestions().remove(questionData.getQuestions());
                    //remove from the almighty array of questions (not sure this works: should remove the question of this id)
                    CreateAddQuestionBox.getAllQuestions().remove(questionData.getQuestions());// (allQuestionsData.get(indexOf(questionData.getQuestion()))));

//                    for(int i = 0; i < CreateQuestionBankBox.quiz.getQuestions().size(); i++){
//                        if(questionData.getQuestion().getText().equals(CreateQuestionBankBox.quiz.getQuestions().get(i).getQuestion())){
//                            CreateQuestionBankBox.quiz.removeSingleQuestion(i);
//                            questionData.getAddButton().setDisable(false);
//                            questionData.getDelButton().setDisable(true);
//                        }
//                    }

                });


                dataGrid = questionData.getDataGrid();
                setGraphic(dataGrid);

            }



        }




    }




}
