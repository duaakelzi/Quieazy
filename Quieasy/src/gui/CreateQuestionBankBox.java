package gui;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class CreateQuestionBankBox extends VBox {
    private static CreateQuestionBankBox createQuestionBankBox;

    //constructor
    private CreateQuestionBankBox() {

        HBox searchfield = initiateSearchField();

        HBox scrollPane = initiateScrollPane();

        HBox save = initiateSaveButton();

        this.getChildren().addAll(searchfield, scrollPane, save);

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

    private HBox initiateScrollPane(){
        HBox scrolPaneHbox = new HBox();
        ScrollPane scrollPane = new ScrollPane();
        ListView<String> list = new ListView<>();
        for(int i= 0; i < 100; i++){
            list.getItems().add(String.valueOf(i));
        }
        //list.getItems(). addAll();
        list.setPrefWidth(650);
        list.setPrefHeight(300);
        scrollPane.setContent(list);
        scrolPaneHbox.getChildren().addAll(scrollPane);
        return  scrolPaneHbox;
    }

    HBox initiateSaveButton(){
        HBox saveHbox = new HBox();
        Button save = new Button("▼ SAVE QUESTION");
        saveHbox.setPadding(new Insets(10, 0, 0, 530));
        saveHbox.getChildren().addAll(save);
        return saveHbox;
    }




}
