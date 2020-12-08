package gui;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Tab;

public class CreateQuestionChoicesTab extends Tab {
    private static CreateQuestionChoicesTab createQuestionChoicesTab;

    public CreateQuestionChoicesTab() {
        super("+ new Question", CreateQuestionChoicesBox.getCreateQuestionChoicesBox());
    }

    public static CreateQuestionChoicesTab getCreateQuestionChoicesTab() {
        if(createQuestionChoicesTab == null){
            createQuestionChoicesTab = new CreateQuestionChoicesTab();
        }
        return createQuestionChoicesTab;
    }


    public void closeTab(){
        CreateQuestionChoicesTab tab = getCreateQuestionChoicesTab();
        EventHandler<Event> handler = tab.getOnClosed();
        if(handler != null){
            handler.handle(null);
        }else{
            tab.getTabPane().getTabs().remove(tab);
        }

    }
}
