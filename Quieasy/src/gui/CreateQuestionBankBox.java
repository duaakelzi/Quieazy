package gui;

import data.ChoicesData;
import data.FilterDataQuestionBank;
import data.QuestionData;
import data.QuizData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateQuestionBankBox extends VBox {
    private static CreateQuestionBankBox createQuestionBankBox;
    public ListView<FilterDataQuestionBank> listQuestions = new ListView<>();
    public ObservableList<FilterDataQuestionBank> dataQuestionObservaleList = FXCollections.observableArrayList();

    public  ObservableList<FilterDataQuestionBank> filterDataQuestionBankObservableList = FXCollections.observableArrayList();

    List<String> inputSearchText;
    List<String> questionsDB;




    // test data this should be from database
    ArrayList<ChoicesData> q1answer = new ArrayList<>();

    QuestionData q1 = new QuestionData("An indirect question gives the substance of the question, adapted to the form of the sentence in which it is quoted. It depends on a verb or other expression of asking, doubting, knowing, or the like.", q1answer);
    ArrayList<ChoicesData> q2answer = new ArrayList<>();
    QuestionData q2 = new QuestionData("Question 2", q2answer);
    ArrayList<ChoicesData> q3answer = new ArrayList<>();
    QuestionData q3 = new QuestionData("An indirect question gives the substance of the question, adapted to the form of the sentence in which it is quoted. It depends on a verb or other expression of asking, doubting, knowing, or the like.", q3answer);
    ArrayList<ChoicesData> q4answer = new ArrayList<>();
    QuestionData q4 = new QuestionData("Question 4", q4answer);

    ArrayList<QuestionData> questionDataArrayList = new ArrayList<>();

    ArrayList<QuizData> quizData = new ArrayList<>();


    //constructor
    private CreateQuestionBankBox() {

        HBox searchfield = initiateSearchField();

        StackPane listViewData = initiateListViewData();

        HBox save = initiateSaveButton();

        this.getChildren().addAll(searchfield, listViewData, save);
//****************************************************************************


        //data for testing replace with DB data
        q1answer.add(new ChoicesData("1",true));
        q1answer.add(new ChoicesData("2", false));
        questionDataArrayList.add(q1);
        questionDataArrayList.add(q2);
        questionDataArrayList.add(q3);
        questionDataArrayList.add(q4);

        quizData.add(new QuizData("Program si CTS", "Course is Calculus","My name is Janea", 20, 60, questionDataArrayList));
        quizData.add(new QuizData("ITF", "Programming 3", "Bourdau", 50, 60, questionDataArrayList));
        for(int i =0; i < quizData.size(); i++){
            for(int j =0; j < quizData.get(i).getQuestions().size(); j++) {
                dataQuestionObservaleList.addAll(new FilterDataQuestionBank(quizData.get(i).getQuestions().get(j),
                        quizData.get(i).getOwnerQuiz(),
                        quizData.get(i).getProgram()));

            }
        }




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
        searchFieldHBox.setPadding(new Insets(30));
        Label searchLabel = new Label("Search");
        searchLabel.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 24));
        TextField searchKeyWords = new TextField();
        searchKeyWords.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 18));
        searchKeyWords.setMinWidth(200);
        searchKeyWords.setPromptText("Filter");
        Button searchButton = new Button("➔");

        //    FilteredList<FilterDataQuestionBank> filteredData = new FilteredList<>(dataQuestionObservaleList, s ->true);
            searchButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {

                        String filterSearch = searchKeyWords.getText();
                        inputSearchText = Arrays.asList(filterSearch.toLowerCase().split(" "));
                        
                        if(filterSearch == null || filterSearch.length() == 0){

                            System.out.println("nothing");
                            listQuestions.getItems().clear();
                        }else{
                            listQuestions.getItems().clear();
                            for (FilterDataQuestionBank filterDataQuestionBank : dataQuestionObservaleList) {
                                questionsDB = Arrays.asList(filterDataQuestionBank.getQuestions().getQuestion().toLowerCase().split("[\\s.,:;]+"));
                                if (questionsDB.containsAll(inputSearchText)){
                                    filterDataQuestionBankObservableList.add(filterDataQuestionBank);


                                }
                            }




                        }

                }
            });


        searchButton.setFont(Font.font(14));
        searchFieldHBox.getChildren().addAll(searchLabel, searchKeyWords, searchButton);
        return searchFieldHBox;

    }
    // here is the method that call the class to fill data in Listview
    private StackPane initiateListViewData(){
        //HBox listViewHBox = new HBox();

        StackPane pane = new StackPane();
        listQuestions.setItems(filterDataQuestionBankObservableList);
        listQuestions.setCellFactory(questionDataListView -> new FilteredData());
        pane.getChildren().addAll(listQuestions);
        listQuestions.setPrefWidth(660);
        listQuestions.setPrefHeight(310);

        return  pane;
    }

    HBox initiateSaveButton(){
        HBox saveHbox = new HBox();
        Button save = new Button("▼ SAVE QUESTION");
        saveHbox.setPadding(new Insets(10, 0, 0, 530));
        saveHbox.getChildren().addAll(save);
        return saveHbox;
    }
        //class that fills ListView with data
    static class FilteredData extends ListCell<FilterDataQuestionBank> {
       FilteredData filteredData;
       GridPane dataGrid = new GridPane();
       public Button addButton;
       public Button delButton;
       //index from ArrayList to add faster to add Question
       Label indexHide = new Label();
       Text question = new Text();
       Text author = new Text();
       Text studyProgram = new Text();

       //constructor
        private FilteredData(){


            dataGrid.setHgap(10);
            dataGrid.setVgap(0);



            addButton = new Button("+");
            addButton.setMinWidth(30);
            delButton = new Button("-");
            delButton.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, null, null)));
            delButton.setMinWidth(30);
            addButton.setBackground(new Background(new BackgroundFill(Color.LIGHTCYAN, null, null)));




            dataGrid.add(addButton, 0, 0);
            dataGrid.add(delButton, 1, 0);

//            indexHide.getId();
//            indexHide.setVisible(true);
//            dataGrid.add(indexHide, 0, 1);

            question.setWrappingWidth(400);
            dataGrid.add(question, 2,0);

            author.setWrappingWidth(70);
            dataGrid.add(author, 3,0);

            studyProgram.setWrappingWidth(70);
            dataGrid.add(studyProgram, 4, 0);


        }


        @Override
        protected void updateItem(FilterDataQuestionBank questionData, boolean empty) {
            super.updateItem(questionData, empty);
            if (empty || questionData == null ) {
                setGraphic(null);

            }else {

                if (dataGrid != null) {
                    addButton.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            System.out.println(questionData.getQuestions().getQuestion());
//                            //here save the chosen question to be added to Array Question ArrayList
//                           // questionData.getQuestions().getQuestion();
                            dataGrid.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));


                        }
                    });
                    delButton.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            System.out.println(questionData.getQuestions().getQuestion());
                            dataGrid.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                        }
                    });

                    question.setText(questionData.getQuestions().getQuestion());
                    author.setText(questionData.getAuthor());
                    studyProgram.setText(questionData.getStudyProgram());
                    setGraphic(dataGrid);
                }else{
                    setGraphic(null);
                }
          }



        }


    }




}



