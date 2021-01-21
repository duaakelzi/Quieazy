package gui;

import data.QuestionData;
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
import requests.QuestionRequests;
import requests.UserRequests;

import java.util.ArrayList;


public class CreateAddQuestionBox extends VBox {
    /**
     * CreateAddQuestionBox class give to the user the way either to crate his own Questions or to import from already in use Questions from another Quizzes
     */
    private static CreateAddQuestionBox createAddQuestionBox;
    private static ArrayList<QuestionData> allQuestions; //to list all questions
    private static ArrayList<QuestionData> newQuestions; //for new questions only
    private static ArrayList<QuestionData> oldQuestions; //for questions from the QuestionBank
    private static ArrayList<QuestionData> updatedQuestions;
    private TableView<TableFillQuestions> tableViewListQuestions;
    private TableFillQuestions selectedItem;
    private HBox buttonsAddImportHBox;
    private VBox listofQuestion;
    private HBox savebtn;

    /**
     * Constructor that create:
     * 1 Add Question Button
     * 2 Import Question Button
     * 3.Display the lists of Questions that form the Quiz
     * 4.Save and Publish button to save the Question in the already new created Quiz
     */
    private CreateAddQuestionBox() {
        super();
        initiateAddButton();
        initiateListOfQuestions();
        initiateSaveBtns();
        this.getChildren().addAll(buttonsAddImportHBox, listofQuestion, savebtn);
        allQuestions = new ArrayList<>(); //to list all questions
        newQuestions = new ArrayList<>(); //for new questions only
        oldQuestions = new ArrayList<>(); //for questions from the QuestionBank
        updatedQuestions = new ArrayList<>();
    }

    /**
     * Getter for new Question
     *
     * @return the list of question created by user
     */
    public static ArrayList<QuestionData> getNewQuestions() {
        return newQuestions;
    }

    /**
     * Getter for the UpdatedQuestion after being edited
     *
     * @return the list of questions after some questions have been updated
     */
    public static ArrayList<QuestionData> getUpdatedQuestions() {
        return updatedQuestions;
    }

    /**
     * Getter for all Questions
     *
     * @return all Questions- old(from Question Bank) and new(created by ownser of Quiz) Question
     */
    public static ArrayList<QuestionData> getAllQuestions() {
        return allQuestions;
    }

    /**
     * Getter to get old question to the newst created Quiz
     *
     * @return existed question in DB into new Quiz
     */
    public static ArrayList<QuestionData> getOldQuestions() {
        return oldQuestions;
    }


    /**
     * CreateAddQuestionBox is a singleton that is created only once to add question avoinding the error that the user is selecting again
     *
     * @return the view where user can insert the Question in the new Quiz
     */
    public static CreateAddQuestionBox getCreateAddQuestionBox() {
        if (createAddQuestionBox == null) {
            createAddQuestionBox = new CreateAddQuestionBox();
        }
        return createAddQuestionBox;
    }

    /**
     * Create Add Question that leads to another windows where user can create a new Question
     */
    private void initiateAddButton() {
        buttonsAddImportHBox = new HBox(330);
        buttonsAddImportHBox.setPadding(new Insets(20));
        Button newQuestion = new Button("➕ Question");
        newQuestion.setOnAction(actionEvent -> {
            MainPane.getMainPane().getTabs().add(CreateQuestionChoicesTab.getCreateQuestionChoicesTab());
            tableViewListQuestions.getSelectionModel().clearSelection();
        });
        newQuestion.setMinWidth(200);
        newQuestion.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 16));
        Button importQuestion = new Button("▲ Import Question");
        importQuestion.setOnAction(actionEvent -> {
            MainPane.getMainPane().getTabs().add(CreateQuestionBankTab.getCreateQuestionBankTab());
        });
        importQuestion.setMinWidth(200);
        importQuestion.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 16));
        buttonsAddImportHBox.getChildren().addAll(newQuestion, importQuestion);
    }

    /**
     * The Questions are displayed to the user in the tableView
     * The row of the list holds the Question, the owner and 2 buttons: edit and remove
     * Edit Button give possibility to user to modify the question from list
     * Remove Button give the option to remove the question from listtable and from Quiz
     */
    private void initiateListOfQuestions() {
        listofQuestion = new VBox();
        listofQuestion.setSpacing(50);
        listofQuestion.setPadding(new Insets(10, 25, 0, 20));
        tableViewListQuestions = new TableView<TableFillQuestions>();
        addNrColumn();
        TableColumn<TableFillQuestions, String> firstCol = new TableColumn<>("Question Name");
        firstCol.setMinWidth(290);
        firstCol.setCellValueFactory(new PropertyValueFactory<>("questionName"));
        TableColumn<TableFillQuestions, String> secondCol = new TableColumn<>("Author");
        secondCol.setMinWidth(60);
        secondCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        setTableDesign();
        tableViewListQuestions.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableViewListQuestions.getColumns().addAll(firstCol, secondCol);
        // add edit button to list
        addEditButtonstoListQuestion();
        // add delete button to list
        addDeleteButtonstoListQuestion();
        listofQuestion.getChildren().addAll(tableViewListQuestions);

    }

    /**
     * Setting the desired deisgne of the tableView
     */
    private void setTableDesign() {
        tableViewListQuestions.setEditable(false);
        tableViewListQuestions.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    /**
     * The Table is filled with the questions
     * FXCollections is used to hold the questions what are in the list
     * If the user edit or delete the observableArrayList is updated not needs to iterate throw to display
     */
    public void fillTableObservableListWithQuestion() {
        ObservableList<TableFillQuestions> questionsToList = FXCollections.observableArrayList();
        for (int i = 0; i < allQuestions.size(); i++) {
            questionsToList.add(new TableFillQuestions(String.valueOf(i), allQuestions.get(i).getQuestion(),
                    allQuestions.get(i).getUser().getFirstName() + " " + allQuestions.get(i).getUser().getLastName()));
        }
        tableViewListQuestions.setItems(questionsToList);
    }

    /**
     * Numbering each question in the table using the factory Pattern
     * Each row is filled up with a number if the question exists otherwise the rows remain empty
     */
    private void addNrColumn() {
        TableColumn<TableFillQuestions, Integer> idCol = new TableColumn<>("#");
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

    /**
     * Every row in the table holds an Edit Button that gives to the user possibility to edit the question added to the Quiz
     * TableCell factory as a pattern is used
     */
    private void addEditButtonstoListQuestion() {
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

                            } catch (IndexOutOfBoundsException e) {
                                Alert alert = new Alert(Alert.AlertType.NONE);
                                alert.setAlertType(Alert.AlertType.INFORMATION);
                                alert.setContentText("Select the item what you want to edit");
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

    /**
     * Delete Button as part of the question added is provided in case if the user when to delete the Question from the Quiz
     * TableCell Factory pattern is used
     */
    private void addDeleteButtonstoListQuestion() {
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
                                allQuestions.remove(allQuestions.get(index));
                                tableViewListQuestions.getItems().remove(selectedItem);
                                tableViewListQuestions.getSelectionModel().clearSelection(); // clear all the selection
                            } catch (IndexOutOfBoundsException e) {
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

    /**
     * Creates 2 buttons:
     * Save - to save the Quiz and it is visible only for owner
     * Save and Publish to save and to be visible to another users
     *
     * @return Save, Save and Publish buttons to user
     */
    private void initiateSaveBtns() {
        savebtn = new HBox(465);
        savebtn.setPadding(new Insets(30, 10, 30, 30));
        Button save = new Button("▼ SAVE");
        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                QuestionRequests.persistNewQuestions(UserRequests.getCurrentUser(), CreateQuizBox.getCreateQuizBox().getQuiz(), newQuestions);
                CreateAddQuestionTab.getCreateAddQuestionTab().closeTab();
            }
        });
        save.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, 16));

        Button savePublish = new Button("▶ SAVE & PUBLISH");
        savePublish.setOnAction(actionEvent -> {
            QuestionRequests.persistNewQuestions(UserRequests.getCurrentUser(), CreateQuizBox.getCreateQuizBox().getQuiz(), newQuestions);

        });

        savePublish.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, 16));
        savebtn.getChildren().addAll(save, savePublish);

    }

    public int indexSelecteditem() {
        return tableViewListQuestions.getSelectionModel().getSelectedIndex();
    }

    /**
     * Subclass of CreateAddQuestion to Quiz that model which data should be shown in the table of questions
     */
    public static class TableFillQuestions {
        private final SimpleStringProperty nr;
        private final SimpleStringProperty questionName;
        private final SimpleStringProperty author;


        /**
         * Constructor of data of wich row should hold a number, question and an author
         *
         * @param nr           of the row, updated by Factory Cell
         * @param questionName si the question added to the Quiz
         * @param author       of the added Question to the Quiz
         */
        private TableFillQuestions(String nr, String questionName, String author) {
            this.nr = new SimpleStringProperty(nr);
            this.questionName = new SimpleStringProperty(questionName);
            this.author = new SimpleStringProperty(author);
        }

        /**
         * Getter ans Setter for model data for displaying in the tableList
         */
        public String getNr() {
            return nr.get();
        }

        public void setNr(String nr) {
            this.nr.set(nr);
        }

        public SimpleStringProperty nrProperty() {
            return nr;
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

}