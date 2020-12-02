package gui;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;



public class CreateQuestionChoicesBox extends VBox {

    private static CreateQuestionChoicesBox createQuestionChoicesBox;

    private CreateQuestionChoicesBox(){
        super();
        Button btn = new Button("New Hero");
        this.getChildren().addAll(btn);
    }

    public static CreateQuestionChoicesBox getCreateQuestionChoicesBox(){
        if(createQuestionChoicesBox == null) createQuestionChoicesBox = new CreateQuestionChoicesBox();
        return createQuestionChoicesBox;
    }




}
