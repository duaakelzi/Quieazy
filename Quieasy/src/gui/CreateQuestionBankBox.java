package gui;

import data.ChoicesData;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateQuestionBankBox extends VBox {
    private static CreateQuestionBankBox createQuestionBankBox;
    public ListView<FilterDataQuestionBank> listQuestions = new ListView<>();
    public ObservableList<FilterDataQuestionBank> dataQuestionObservaleList; // contain data from all quizes ready to be filtered
    public ObservableList<FilterDataQuestionBank> filterDataQuestionBankObservableList; //contain filtered data only
    public static QuizData quiz;
    public List<String> inputSearchText;
    public List<String> questionsDB;
    private Label warrningNotFound;
    private TextField searchKeyWords;

//***************************** fetch data from DB here********************************** return questionData with question owner
    public static ArrayList<QuestionData> allQuestionsData; //this holds all data from DB before beeing transformed in FilteredData Objects
//***************************************************************************************
    // test data this should be from database
    ArrayList<ChoicesData> q1answer = new ArrayList<>();
    QuestionData q1 = new QuestionData("indirect question I need the Left hand list view too scroll all the way down. ... goal org.openjfx:javafx-maven-plugin:0.0.4:run (default-cli) on project Kochbuch: .", q1answer);
    ArrayList<ChoicesData> q2answer = new ArrayList<>();
    QuestionData q2 = new QuestionData("Question 2 n above FXML we have created two listview with style class naming mylistview. ... .mylistview .scroll-bar:horizontal:pressed .track , ... you want your scrollbar little bit wider and bigger than it's default size (like of below image", q2answer);
    ArrayList<ChoicesData> q3answer = new ArrayList<>();
    QuestionData q3 = new QuestionData("Anlor indirecter question gives the substance of the question,of the sentence in which it is quoted. ", q3answer);
    ArrayList<ChoicesData> q4answer = new ArrayList<>();
    QuestionData q4 = new QuestionData("Question 4 Create an custom ListCell for the ListView in JavaFX. ... ObservableList, so when changes in that list occur, they get updated to the ListView. ... code in here, because if you scroll fast in your listview it could produce some lags", q4answer);

    ArrayList<QuestionData> questionDataArrayList = new ArrayList<>();




    //constructor
    private CreateQuestionBankBox() {

        HBox searchfield = initiateSearchField();
        HBox warningNotFound = initiateWarrningMessageNotFound();

        StackPane listViewData = initiateListViewData();

        HBox save = initiateSaveButton();

        this.getChildren().addAll(searchfield, warningNotFound, listViewData, save);

        dataQuestionObservaleList = FXCollections.observableArrayList(); // contain data from all quizes ready to be filtered
        filterDataQuestionBankObservableList = FXCollections.observableArrayList(); //filtered data to be displayed to the user
        quiz = CreateQuizBox.getCreateQuizBox().getQuiz();// new quiz created by user to be added the questions
//****************************************************************************

        allQuestionsData = new ArrayList<>();
        //data for testing replace with DB data
        q1answer.add(new ChoicesData("1",true));
        q1answer.add(new ChoicesData("2", false));
        questionDataArrayList.add(q1);
        questionDataArrayList.add(q2);
        questionDataArrayList.add(q3);
        questionDataArrayList.add(q4);
//        allQuizesData.add(new QuizData("Program si CTS", "Course is Calculus","My name is Janea", 20, 60, questionDataArrayList));
//        allQuizesData.add(new QuizData("ITF", "Programming 3", "Bourdau", 50, 60, questionDataArrayList));
        fillObservaleListWithData(allQuestionsData);
    }

    //get the current instance ->Singleton
    public static CreateQuestionBankBox getCreateQuestionBankBox() {
        if (createQuestionBankBox == null) {
            createQuestionBankBox = new CreateQuestionBankBox();
        }
        return createQuestionBankBox;

    }

    //search field
    private HBox initiateSearchField(){
        HBox searchFieldHBox = new HBox(30);
        searchFieldHBox.setPadding(new Insets(30, 0, 0, 50));
        Label searchLabel = new Label("Search");
        searchLabel.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 24));
        searchKeyWords = new TextField();
        searchKeyWords.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 18));
        searchKeyWords.setMinWidth(400);
        searchKeyWords.setPromptText("Filter");
        Button searchButton = new Button("➔");
        //    FilteredList<FilterDataQuestionBank> filteredData = new FilteredList<>(dataQuestionObservaleList, s ->true);
            searchButton.setOnAction(actionEvent -> {

                if(listQuestions.getItems() != null){listQuestions.getItems().clear();}
                if(dataQuestionObservaleList.size() == 0) {fillObservaleListWithData(allQuestionsData);}// in case if new Quiz is added not to hold the data from previous selected quiz
                if(filterDataQuestionBankObservableList.size() != 0){filterDataQuestionBankObservableList.clear();}

                    String filterSearch = searchKeyWords.getText();
                    inputSearchText = Arrays.asList(filterSearch.toLowerCase().split(" "));
                    if(filterSearch.length() == 0){
                        warrningNotFound.setText("Question Not Found");
                        PauseTransition pause = new PauseTransition(Duration.seconds(1));
                        pause.setOnFinished(actionEvent1 -> warrningNotFound.setText(null));
                        pause.play();
                       // filterDataQuestionBankObservableList.add(new FilterDataQuestionBank())
                        //listQuestions.getItems().clear();

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
        return searchFieldHBox;

    }
    private HBox initiateWarrningMessageNotFound(){
        HBox warrningMessage = new HBox(200);
        warrningNotFound = new Label();
        //warrningNotFound.setVisible(false);
        warrningNotFound.setPadding(new Insets(5, 0, 5, 175));
        warrningNotFound.setFont(Font.font("Courier New",FontWeight.SEMI_BOLD, 16));
        warrningNotFound.setTextFill(Color.FIREBRICK);
        warrningMessage.getChildren().add(warrningNotFound);
        return warrningMessage;

    }


    // here is the method that call the class to fill data in Listview
    private StackPane initiateListViewData(){
        //HBox listViewHBox = new HBox();
        StackPane pane = new StackPane();
        pane.setMaxWidth(665);
        pane.setMaxHeight(310);
        listQuestions.setItems(filterDataQuestionBankObservableList);
        listQuestions.setCellFactory(questionDataListView -> new FilteredData());
        pane.getChildren().addAll(listQuestions);

        return  pane;
    }
//save button to store the data********************************
    HBox initiateSaveButton(){
        HBox saveHbox = new HBox();
        Button save = new Button("▼ SAVE QUESTION");
        save.setOnAction(actionEvent -> {

            CreateAddQuestionBox.getCreateAddQuestionBox().fillTableObservableListWithQuestion();
            dataQuestionObservaleList.clear();
            filterDataQuestionBankObservableList.clear();
            searchKeyWords.clear();
            CreateQuestionBankTab.getCreateQuestionBankTab().closeTab();

        });
        saveHbox.setPadding(new Insets(10, 0, 0, 535));
        saveHbox.getChildren().addAll(save);
        return saveHbox;
    }


    public void fillObservaleListWithData(ArrayList<QuestionData> allQuestionsData) {
//        System.out.println("allQuizesDatafrom fillObservablewithData "+ allQuestionsData.size());
//        System.out.println("allQuizesDataQuestions fillObservablewithData "+ allQuestionsData.get(0).getQuestions().size() + " and " +allQuestionsData.get(1).getQuestions().size());
        for (int i = 0; i < allQuestionsData.size(); i++) {

//                dataQuestionObservaleList.addAll(new FilterDataQuestionBank(allQuestionsData.get(i).getQuestion(),
//                       // allQuestionsData.get(i).getOwner(),
////                        i));// instead of study program put course
////
//
//        }
        }
    }

        //inner-class that fills ListView with data
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



