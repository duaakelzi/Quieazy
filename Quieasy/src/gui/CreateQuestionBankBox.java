package gui;

import data.FilterDataQuestionBank;
import data.QuestionData;
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
    public static ArrayList<QuestionData> allQuestionsData; //this holds all data from DB before beeing transformed in FilteredData Objects
    /**
     * CreateQuestionBank class represent a filter that gets all Questions from DB
     * and passing throw the filter matching the key words introduced by user and are ready to be added to the Quiz
     * Class holds a Text field holds the key words of user and a ListView of questions that have been selected
     */
    private static CreateQuestionBankBox createQuestionBankBox;
    private final ListView<FilterDataQuestionBank> listQuestions = new ListView<>();
    private final ObservableList<FilterDataQuestionBank> dataQuestionObservaleList; // contain data from all quizzes ready to be filtered
    private final ObservableList<FilterDataQuestionBank> filterDataQuestionBankObservableList; //contain filtered data only
    private List<String> inputSearchText;
    private List<String> questionsDB;
    private Label warrningNotFound;
    private TextField searchKeyWords;
    private HBox searchFieldHBox;
    private HBox warningNotFound;
    private StackPane listViewData;
    private HBox saveButton;

    /**
     * Constructor that initializes the Filter text Field, Button and the ListView where will be displayed the filtered data
     */
    private CreateQuestionBankBox() {
        initiateSearchField();
        initiateWarrningMessageNotFound();
        initiateListViewData();
        initiateSaveButton();
        this.getChildren().addAll(searchFieldHBox, warningNotFound, listViewData, saveButton);
        // contain data from all quizes ready to be filtered
        dataQuestionObservaleList = FXCollections.observableArrayList();
        //filtered data to be displayed to the user
        filterDataQuestionBankObservableList = FXCollections.observableArrayList();
        allQuestionsData = QuestionRequests.fetchAllQuestions();
        fillObservaleListWithData(allQuestionsData);
    }

    /**
     * Singleton object of Bank Question class that is created only once and the user can not trigger to open multiple windows
     * of Bank Question, in order to keep the data consistent.
     *
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
    private void initiateSearchField() {
        searchFieldHBox = new HBox(30);
        searchFieldHBox.setPadding(new Insets(30, 0, 0, 50));
        Label searchLabel = new Label("Search");
        searchLabel.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 24));
        searchKeyWords = new TextField();
        searchKeyWords.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 18));
        searchKeyWords.setMinWidth(450);
        searchKeyWords.setPromptText("Search");
        Button searchButton = new Button("➔");
        searchButton.setOnAction(actionEvent -> {
            if (listQuestions.getItems() != null) {
                listQuestions.getItems().clear();
            }
            // in case if new Quiz is added not to hold the data from previous selected quiz
            if (dataQuestionObservaleList.size() == 0) {
                fillObservaleListWithData(allQuestionsData);
            }
            if (filterDataQuestionBankObservableList.size() != 0) {
                filterDataQuestionBankObservableList.clear();
            }
            String filterSearch = searchKeyWords.getText();
            inputSearchText = Arrays.asList(filterSearch.toLowerCase().split(" "));
            if (filterSearch.length() == 0) {
                warrningNotFound.setText("Question Not Found");
                PauseTransition pause = new PauseTransition(Duration.seconds(1));
                pause.setOnFinished(actionEvent1 -> warrningNotFound.setText(null));
                pause.play();

            } else {
                for (FilterDataQuestionBank filterDataQuestionBank : dataQuestionObservaleList) {
                    questionsDB = Arrays.asList(filterDataQuestionBank.getQuestions().getQuestion().toLowerCase().split("[\\s.,:;]+"));
                    if (questionsDB.containsAll(inputSearchText)) {
                        filterDataQuestionBankObservableList.add(filterDataQuestionBank);
                    }
                }
                if (filterDataQuestionBankObservableList.size() == 0) {
                    warrningNotFound.setText("Question Not Found");
                    PauseTransition pause = new PauseTransition(Duration.seconds(1));
                    pause.setOnFinished(actionEvent1 -> warrningNotFound.setText(null));
                    pause.play();
                }
                listQuestions.setItems(filterDataQuestionBankObservableList);
            }

        });
        searchButton.setFont(Font.font(14));
        searchFieldHBox.getChildren().addAll(searchLabel, searchKeyWords, searchButton);
    }

    /**
     * In case if the key words doesn't match with any questions from DB or an empty field is left
     * an warning message is displayed to the user with containt="Question Not Found"
     */
    private void initiateWarrningMessageNotFound() {
        warningNotFound = new HBox(250);
        warrningNotFound = new Label();
        warrningNotFound.setPadding(new Insets(5, 0, 5, 175));
        warrningNotFound.setFont(Font.font("Courier New", FontWeight.SEMI_BOLD, 16));
        warrningNotFound.setTextFill(Color.FIREBRICK);
        warningNotFound.getChildren().add(warrningNotFound);
    }

    /**
     * Method to display the question in the ListView
     *
     * @return the view of question
     */
    private void initiateListViewData() {
        listViewData = new StackPane();
        listViewData.setMaxWidth(760);
        listViewData.setMaxHeight(380);
        listQuestions.setItems(filterDataQuestionBankObservableList);
        listQuestions.setCellFactory(questionDataListView -> new FilteredData());
        listViewData.getChildren().addAll(listQuestions);
    }

    /**
     * Save Button to store all selected questions to the Quiz
     */
    private void initiateSaveButton() {
        saveButton = new HBox();
        Button save = new Button("▼ SAVE QUESTION");
        save.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 16));
        save.setOnAction(actionEvent -> {

            CreateAddQuestionBox.getCreateAddQuestionBox().fillTableObservableListWithQuestion();
            dataQuestionObservaleList.clear();
            filterDataQuestionBankObservableList.clear();
            searchKeyWords.clear();
            CreateQuestionBankTab.getCreateQuestionBankTab().closeTab();

        });
        saveButton.setPadding(new Insets(20, 0, 0, 570));
        saveButton.getChildren().addAll(save);
    }

    /**
     * Fill the FXCollection observale list with Filtered Questions to be displayed
     *
     * @param allQuestionsData, represent all question from all Quizzes from DB
     */
    public void fillObservaleListWithData(ArrayList<QuestionData> allQuestionsData) {
        for (QuestionData allQuestionsDatum : allQuestionsData) {

            dataQuestionObservaleList.addAll(new FilterDataQuestionBank(allQuestionsDatum,
                    allQuestionsDatum.getUser().getFirstName(),
                    allQuestionsDatum.getUser().getLastName()));
        }
    }
}

/**
 * Inner Class that fill List View with filtered questions objects using the listener of Filtereddata from Observable List
 */
class FilteredData extends ListCell<FilterDataQuestionBank> {

    private GridPane dataGrid;

    /**
     * Constructor
     */
    FilteredData() {
        dataGrid = new GridPane();
    }

    /**
     * @param questionData type of FilteredDataQuestionBank is  a wrapper of question filtered to be displayed to the user in a listView
     * @param empty        is the row is empty to set up a empty row without object
     *                     add Button get the question and added to the Quiz
     *                     remove button remove the question from listView and Quiz
     * @override updateItem is a listener of listview row, filling each row of list with FilteredData
     */
    @Override
    protected void updateItem(FilterDataQuestionBank questionData, boolean empty) {
        super.updateItem(questionData, empty);
        if (empty || questionData == null) {
            setGraphic(null);
        } else {
            questionData.getAddButton().setOnAction(actionEvent -> {
                //save the chosen question to be added to Array Question ArrayList
                questionData.getDataGrid().setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
                // add imported question to oldQuestions method in the CreateAddQuestionBox
                CreateAddQuestionBox.getOldQuestions().add(questionData.getQuestions());
                //add to the almighty question array which will be displayed to user
                CreateAddQuestionBox.getAllQuestions().add(questionData.getQuestions());
                questionData.getAddButton().setDisable(true);
                questionData.getDelButton().setDisable(false);

            });
            questionData.getDelButton().setOnAction(actionEvent -> {
                dataGrid.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                CreateAddQuestionBox.getOldQuestions().remove(questionData.getQuestions());
                CreateAddQuestionBox.getAllQuestions().remove(questionData.getQuestions());
                questionData.getDelButton().setDisable(true);//
            });
            dataGrid = questionData.getDataGrid();
            setGraphic(dataGrid);
        }
    }
}





