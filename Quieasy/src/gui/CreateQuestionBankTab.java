package gui;

import javafx.scene.control.Tab;

public class CreateQuestionBankTab extends Tab {

    private static CreateQuestionBankTab createQuestionBankTab;

    public CreateQuestionBankTab(){
        super("Question Bank", CreateQuestionBankBox.getCreateQuestionBankBox());
    }

    public static CreateQuestionBankTab getCreateQuestionBankTab() {
        if(createQuestionBankTab == null){
            createQuestionBankTab = new CreateQuestionBankTab();
        }
        return createQuestionBankTab;
    }
}
