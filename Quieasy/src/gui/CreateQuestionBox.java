package gui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class CreateQuestionBox extends VBox {

        private static CreateQuestionBox createQuestionBox;


        private CreateQuestionBox() {
            super();
            // initialize the buttons for adding questions
            HBox buttons = initiateaddButton();
            // initialize the table where will be store the Question List
            VBox listofQuestion = initiatelistofQuestions();

            this.getChildren().addAll(buttons, listofQuestion);
        }



        //get the current instance ->Singleton
    public static CreateQuestionBox getCreateQuestionBox(){
            if(createQuestionBox == null){
                createQuestionBox = new CreateQuestionBox();
            }
            return createQuestionBox;
    }

    private HBox initiateaddButton(){
            HBox buttons = new HBox(230);
            buttons.setPadding(new Insets(20));
            Button newQuestion = new Button("New Question");
            newQuestion.setMinWidth(200);
            newQuestion.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 16));
            Button importQuestion = new Button("Import Question");
            importQuestion.setMinWidth(200);
            importQuestion.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 16));
            buttons.getChildren().addAll(newQuestion, importQuestion);
            return buttons;
    }

    private VBox initiatelistofQuestions(){
            VBox  listofQuestion = new VBox();
            listofQuestion.setPadding(new Insets(20));
            TableView listQ = new TableView();
            listQ.setEditable(true);
        TableColumn firstCol = new TableColumn("Question Name");
        TableColumn secondCol = new TableColumn("Author");
        TableColumn thirdCol = new TableColumn("Study Program");
        TableColumn fourthCol = new TableColumn("Edit");
        TableColumn lastCol = new TableColumn("Remove");
        listQ.getColumns().addAll(firstCol,secondCol,thirdCol, fourthCol, lastCol);
        listofQuestion.getChildren().addAll(listQ);

        return listofQuestion;
    }

}
