package gui;

import javafx.event.Event;
import javafx.event.EventHandler;
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

    public void closeTab(){
        CreateQuestionBankTab tab = getCreateQuestionBankTab();
        EventHandler<Event> handler = tab.getOnClosed();
        if(handler != null){
            handler.handle(null);
        }else{
            tab.getTabPane().getTabs().remove(tab);
        }

    }
}
