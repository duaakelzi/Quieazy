package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class CreateQuestionBankBox extends VBox {
    private static CreateQuestionBankBox createQuestionBankBox;

    //constructor
    private CreateQuestionBankBox() {

        HBox searchfield = initiateSearchField();
        this.getChildren().addAll(searchfield);

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
        searchKeyWords.setMinWidth(100);
        searchKeyWords.setPromptText("Filter");
        Button searchButton = new Button("➔");
        searchButton.setFont(Font.font(14));
        searchFieldHBox.getChildren().addAll(searchLabel, searchKeyWords, searchButton);
        return searchFieldHBox;

    }
}
