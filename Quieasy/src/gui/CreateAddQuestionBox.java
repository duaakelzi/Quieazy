package gui;

import data.Question;
import domain.QuizC;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Callback;

import java.util.ArrayList;


public class CreateAddQuestionBox extends VBox {

        private static CreateAddQuestionBox createAddQuestionBox;
        private TableView<TableFillQuestions> tableViewListQuestions;
        private ObservableList<TableFillQuestions> questionsToList;
        private TableColumn<TableFillQuestions, Integer> idCol;
        private ArrayList<Question> questions = CreateQuizBox.getCreateQuizBox().getQuiz().getQuestions();
        private TableFillQuestions selectedItem;
        private Question addedQuestiontoQuiz;





    //constructor
        private CreateAddQuestionBox() {
            super();
            // initialize the buttons for adding questions
            HBox buttons = initiateaddButton();
            // initialize the table where will be store the Question List
            // Sample data
            VBox listofQuestion = initiatelistofQuestions();
            HBox savebtn = initiateSavedBtns();

            this.getChildren().addAll(buttons, listofQuestion, savebtn);
        }



        //get the current instance ->Singleton
    public static CreateAddQuestionBox getCreateAddQuestionBox(){
            if(createAddQuestionBox == null){
                createAddQuestionBox = new CreateAddQuestionBox();
            }
            return createAddQuestionBox;
    }

    private HBox initiateaddButton(){
            HBox buttons = new HBox(230);
            buttons.setPadding(new Insets(20));
            Button newQuestion = new Button("➕ Question");
            newQuestion.setOnAction(actionEvent -> {
                MainPane.getMainPane().getTabs().add(CreateQuestionChoicesTab.getCreateQuestionChoicesTab());
                tableViewListQuestions.getSelectionModel().clearSelection();
            });
            newQuestion.setMinWidth(200);
            newQuestion.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 16));
            Button importQuestion = new Button("▲ Import Question");
            importQuestion.setMinWidth(200);
            importQuestion.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 16));
            buttons.getChildren().addAll(newQuestion, importQuestion);
            return buttons;
    }

    private VBox initiatelistofQuestions(){
        VBox  questionVbox = new VBox();
        questionVbox.setSpacing(50);
        questionVbox.setPadding(new Insets(10, 25, 0, 20));
        tableViewListQuestions = new TableView<TableFillQuestions>();

//

        addNrColumn(); // nr column

        TableColumn <TableFillQuestions, String> firstCol = new TableColumn<>("Question Name");
        firstCol.setMinWidth(290);
        firstCol.setCellValueFactory(new PropertyValueFactory<>("questionName"));

        TableColumn<TableFillQuestions, String> secondCol = new TableColumn<>("Author");
        secondCol.setMinWidth(60);
        secondCol.setCellValueFactory(new PropertyValueFactory<>("author"));


        setTableDesign();

        tableViewListQuestions.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        tableViewListQuestions.getColumns().addAll(firstCol, secondCol);

        // add edit button
        addEditButtonstoListQuestion();
        // add delete button to list
        addDeleteButtonstoListQuestion();
        questionVbox.getChildren().addAll(tableViewListQuestions);

        return questionVbox;
    }

    private void setTableDesign(){
        tableViewListQuestions.setEditable(false);
        tableViewListQuestions.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }
    // fill table row with data from added Question
    public void fillTableObservableListWithQuestion(){

        //addedQuestiontoQuiz = questions.get(index);
        questionsToList = FXCollections.observableArrayList();

        for(int i = 0; i < questions.size(); i++){
            questionsToList.add(new TableFillQuestions(String.valueOf(i), questions.get(i).getQuestion(), "Chen Li"));
        }
           tableViewListQuestions.setItems(questionsToList);
           // tableViewListQuestions.setItems(questionsToList);



    }
    private void addNrColumn(){
            idCol = new TableColumn<>("#");
            idCol.setMinWidth(25);
            idCol.setMaxWidth(25);
            idCol.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(Integer.parseInt(data.getValue().getNr())));
            idCol.setCellFactory(new Callback<>() {
                @Override
                public TableCell<TableFillQuestions, Integer> call(TableColumn<TableFillQuestions, Integer> tableFillQuestionsIntegerTableColumn) {
                    return new TableCell<>() {
                        @Override
                        protected void updateItem(Integer item, boolean empty) {
                            super.updateItem(item, empty);
                            if (this.getTableRow() != null && item != null) {
                                setText(this.getTableRow().getIndex() + "");
                            } else {
                                setText("");
                            }
                        }
                    };
                }
            });
            tableViewListQuestions.getColumns().add(idCol);

    }

    private void addEditButtonstoListQuestion(){
         // insert edit button in the fourth colomn
        TableColumn<TableFillQuestions, Void> fourthCol = new TableColumn<>("EDIT");
        //fourthCol.setMinWidth(35);
        Callback<TableColumn<TableFillQuestions, Void>, TableCell<TableFillQuestions, Void>> editbtnFactory = new Callback<>() {
            @Override
            public TableCell<TableFillQuestions, Void> call(TableColumn<TableFillQuestions, Void> tableFillDataVoidTableColumn) {


                return new TableCell<>() {

                    final Button editbtn = new Button("⭯ Edit");

                    {
                        editbtn.setOnAction(actionEvent -> {
                            // action when the button is pressed
                            try {
                                MainPane.getMainPane().getTabs().add(CreateQuestionChoicesTab.getCreateQuestionChoicesTab());
                                int index = indexSelecteditem();
                                CreateQuestionChoicesBox.getCreateQuestionChoicesBox().editQuestion(index);

                            } catch (IndexOutOfBoundsException e){
                                Alert alert = new Alert(Alert.AlertType.NONE);
                                alert.setAlertType(Alert.AlertType.INFORMATION);
                                alert.setContentText("Selecte the item what you want to edit");
                                alert.show();
                            }

                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(editbtn);
                        }
                    }
                };
            }


        };
        fourthCol.setCellFactory(editbtnFactory);
        tableViewListQuestions.getColumns().add(fourthCol);


    }

    private void addDeleteButtonstoListQuestion(){
        // insert delete button in the last colomn
        TableColumn<TableFillQuestions, Void> lastCol = new TableColumn<>("REMOVE");
        //lastCol.setMinWidth(40);
        Callback<TableColumn<TableFillQuestions, Void>, TableCell<TableFillQuestions, Void>> deletebtnFactory = new Callback<>() {
            @Override
            public TableCell<TableFillQuestions, Void> call(TableColumn<TableFillQuestions, Void> tableFillDataVoidTableColumn) {


                return new TableCell<>() {

                    final Button deletebtn = new Button("× Delete");

                    {
                        deletebtn.setOnAction(actionEvent -> {
                            // remove the question from list
                            try {
                                selectedItem = tableViewListQuestions.getSelectionModel().getSelectedItem();
                                int index = tableViewListQuestions.getSelectionModel().getSelectedIndex();
                                questions.remove(questions.get(index));
                                tableViewListQuestions.getItems().remove(selectedItem);
                                tableViewListQuestions.getSelectionModel().clearSelection(); // clear all the selection
                            }catch (IndexOutOfBoundsException e){
                                Alert alert = new Alert(Alert.AlertType.NONE);
                                alert.setAlertType(Alert.AlertType.INFORMATION);
                                alert.setContentText("Select the Question to be removed");
                                alert.show();
                            }

                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(deletebtn);
                        }
                    }
                };
            }


        };
        lastCol.setCellFactory(deletebtnFactory);
        tableViewListQuestions.getColumns().add(lastCol);

    }


    // data model for list of Question in CreateQuestionBox
    public static class TableFillQuestions {
        private final SimpleStringProperty nr;
        private final SimpleStringProperty questionName;
        private final SimpleStringProperty author;



        private TableFillQuestions(String nr, String questionName, String author) {
            this.nr = new SimpleStringProperty(nr);
            this.questionName = new SimpleStringProperty(questionName);
            this.author = new SimpleStringProperty(author);



        }

        public String getNr() {
            return nr.get();
        }

        public SimpleStringProperty nrProperty() {
            return nr;
        }

        public void setNr(String nr) {
            this.nr.set(nr);
        }

        public String getQuestionName() {
            return questionName.get();
        }

        public void setquestionName(String questionName) {
            this.questionName.set(questionName);
        }

        public String getAuthor() {
            return author.get();
        }

        public void setAuthor(String author) {
            this.author.set(author);
        }


    }


    private HBox initiateSavedBtns(){
        HBox savedbtn = new HBox(360);
        savedbtn.setPadding(new Insets(30,10,30,30));
        Button save = new Button("▼ SAVE");
        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //savequiz();

                //QuestionC.createnewQuestions(CreateQuizBox.getCreateQuizBox().getQuiz()); // not connected to server yet

                for(int i = 0; i < CreateQuizBox.getCreateQuizBox().getQuiz().getQuestions().size(); i ++){
                    System.out.println(CreateQuizBox.getCreateQuizBox().getQuiz().getQuestions().get(i).getQuestion());
                }

            }
        });
        save.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, 16));
        Button savePublish = new Button("▶ SAVE & PUBLISH");
        savePublish.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, 16));
        savedbtn.getChildren().addAll(save, savePublish);
        return savedbtn;
    }


    public void savequiz(){
        QuizC.createNewQuiz(CreateQuizBox.getCreateQuizBox().getQuiz());
    }

    public int indexSelecteditem(){
        return tableViewListQuestions.getSelectionModel().getSelectedIndex();
    }

}
