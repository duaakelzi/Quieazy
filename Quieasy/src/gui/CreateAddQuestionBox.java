package gui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Callback;


public class CreateAddQuestionBox extends VBox {

        private static CreateAddQuestionBox createAddQuestionBox;
        private TableView<TableFillQuestions> listQ;
        private ObservableList<TableFillQuestions> questionsToList;
        //constructor
        private CreateAddQuestionBox() {
            super();
            // initialize the buttons for adding questions
            HBox buttons = initiateaddButton();
            // initialize the table where will be store the Question List
            // Sample data
            VBox listofQuestion = initiatelistofQuestions();

            this.getChildren().addAll(buttons, listofQuestion);
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
            Button newQuestion = new Button("New Question");
            newQuestion.setOnAction(actionEvent -> {


                MainPane.getMainPane().getTabs().add(CreateQuestionChoicesTab.getCreateQuestionChoicesTab());



            });
            newQuestion.setMinWidth(200);
            newQuestion.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 16));
            Button importQuestion = new Button("Import Question");
            importQuestion.setMinWidth(200);
            importQuestion.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 16));
            buttons.getChildren().addAll(newQuestion, importQuestion);
            return buttons;
    }

    private VBox initiatelistofQuestions(){
        VBox  questionVbox = new VBox();
        questionVbox.setSpacing(50);
        questionVbox.setPadding(new Insets(10, 20, 70, 20));
        listQ = new TableView<TableFillQuestions>();


        TableColumn <TableFillQuestions, String> firstCol = new TableColumn<>("Question Name");
        firstCol.setMinWidth(250);
        firstCol.setCellValueFactory(new PropertyValueFactory<TableFillQuestions, String>("questionName"));

        TableColumn<TableFillQuestions, String> secondCol = new TableColumn<>("Author");
        secondCol.setMinWidth(75);
        secondCol.setCellValueFactory(new PropertyValueFactory<TableFillQuestions, String>("author"));


        setTableDesign();
        fillTableObservableListWithQuestion();
        listQ.setItems(questionsToList);

        listQ.getColumns().addAll(firstCol,secondCol);

        // add edit button
        addEditButtonstoListQuestion();
        // add delete button to list
        addDeleteButtonstoListQuestion();
        questionVbox.getChildren().addAll(listQ);

        return questionVbox;
    }

    private void setTableDesign(){
        listQ.setEditable(false);
        listQ.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    private void fillTableObservableListWithQuestion(){
        questionsToList = FXCollections.observableArrayList();
                questionsToList.addAll(new TableFillQuestions("What is a Singleton", "Che Li", "CTS"));
                questionsToList.addAll(new TableFillQuestions("What is copy constructor", "Iva Beu", "INFO"));

    }
    private void addEditButtonstoListQuestion(){
         // insert edit button in the fourth colomn
        TableColumn<TableFillQuestions, Void> fourthCol = new TableColumn<>("EDIT");
        //fourthCol.setMinWidth(35);
        Callback<TableColumn<TableFillQuestions, Void>, TableCell<TableFillQuestions, Void>> editbtnFactory = new Callback<>() {
            @Override
            public TableCell<TableFillQuestions, Void> call(TableColumn<TableFillQuestions, Void> tableFillDataVoidTableColumn) {


                return new TableCell<>() {

                    final Button editbtn = new Button("Edit");

                    {
                        editbtn.setOnAction(actionEvent -> {
                            // action when the button is pressed

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
        listQ.getColumns().add(fourthCol);


    }

    private void addDeleteButtonstoListQuestion(){
        // insert delete button in the last colomn
        TableColumn<TableFillQuestions, Void> lastCol = new TableColumn<>("REMOVE");
        //lastCol.setMinWidth(40);
        Callback<TableColumn<TableFillQuestions, Void>, TableCell<TableFillQuestions, Void>> deletebtnFactory = new Callback<>() {
            @Override
            public TableCell<TableFillQuestions, Void> call(TableColumn<TableFillQuestions, Void> tableFillDataVoidTableColumn) {


                return new TableCell<>() {

                    final Button deletebtn = new Button("Delete");

                    {
                        deletebtn.setOnAction(actionEvent -> {
                            // remove the question from list

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
        listQ.getColumns().add(lastCol);

    }


    // data model for list of Question in CreateQuestionBox
    public static class TableFillQuestions {

        private final SimpleStringProperty questionName;
        private final SimpleStringProperty author;



        private TableFillQuestions(String questionName, String author, String studyProgramHS) {
            this.questionName = new SimpleStringProperty(questionName);
            this.author = new SimpleStringProperty(author);


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
