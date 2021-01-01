package gui;

import data.QuestionData;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class CreateQuestionBankBox extends VBox {
    private static CreateQuestionBankBox createQuestionBankBox;

    //constructor
    private CreateQuestionBankBox() {

        HBox searchfield = initiateSearchField();

        HBox scrollPane = initiateScrollPane();

        HBox save = initiateSaveButton();

       // VBox q = initiateFilterData();

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
        searchKeyWords.setMinWidth(200);
        searchKeyWords.setPromptText("Filter");
        Button searchButton = new Button("➔");
        searchButton.setFont(Font.font(14));
        searchFieldHBox.getChildren().addAll(searchLabel, searchKeyWords, searchButton);
        return searchFieldHBox;

    }

    private HBox initiateScrollPane(){
        HBox scrolPaneHbox = new HBox();
        ScrollPane scrollPane = new ScrollPane();
        ListView<GridPane> list = new ListView<>();
        for(int i= 0; i < 10; i++){
            list.getItems().add(initiateFilterData());
        }

        list.setPrefWidth(660);
        list.setPrefHeight(310);
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

    GridPane initiateFilterData(){
        GridPane dataGrid = new GridPane();
        dataGrid.setHgap(10);
        dataGrid.setVgap(10);


        Button addQuestion = new Button("+ ADD");
        addQuestion.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                addQuestion.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, null, null)));
            }
        });
        addQuestion.setBackground(new Background(new BackgroundFill(Color.LIGHTCYAN, null, null)));
        dataGrid.add(addQuestion, 0, 0);

        TextArea question = new TextArea("There was a time when pub quizzes happened in… well, pubs." + "But with the pandemic going on, it has become more common to organise a virtual pub quiz " +
                "on your choice of video chat for the evening (or in the day, we’re not judging you). Whether it’s  " +
                "Google Hangouts, Zoom, Skype, or any other video call platforms, big brains are flexing around the " +
                "internet and laughter ensues.");
        question.setEditable(false);
        question.setWrapText(true);
//        question.setMaxWidth(400);
        question.setMaxHeight(70);
        dataGrid.add(question, 1,1,2,1);
        Label author = new Label("CHen Li");
        author.setMinWidth(70);
        dataGrid.add(author, 3,0);

        Label studyProgram = new Label("CTS");
        studyProgram.setMinWidth(70);
        dataGrid.add(studyProgram, 4, 0);

//        VBox dataQuestionfiltered = new VBox(5);
////        dataQuestionfiltered.setPrefWidth(600);
////        dataQuestionfiltered.setPrefHeight(60);
//        dataQuestionfiltered.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
//        dataQuestionfiltered.setBackground(new Background(new BackgroundFill(Color.LIGHTCYAN, null, null)));
//        HBox dataHBox = new HBox(15);
//        dataHBox.setPrefWidth(600);
//        dataHBox.setPrefHeight(50);
//        CheckBox checkQuestion = new CheckBox();
//        checkQuestion.setText(String.valueOf(1));
//        TextArea question = new TextArea("Hew is meee stioopaaaabvbvnbvnbvnbvnbvbfghfghdfgcbbcvvvvvvvvvvvvxxhjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjerrrdsfggffffffffgffffffffffferrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrreeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeewwwwwwwwwwwwwwwwwwwwwwwwwwwww");
//        question.setEditable(false);
//        Label author = new Label("Chen lifggggggggggggggggggggggggggggggggggggggggggggggg");
//        Label studyProgram = new Label("CTS");
//        dataHBox.getChildren().addAll(checkQuestion, question, author,studyProgram);
//
//        Button viewMore = new Button(">> VIEW MORE");
//        viewMore.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
//
//        dataQuestionfiltered.getChildren().addAll(dataHBox, viewMore);

//        return dataQuestionfiltered;

        return dataGrid;

    }


}

