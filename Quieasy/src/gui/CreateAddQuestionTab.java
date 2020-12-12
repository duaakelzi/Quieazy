package gui;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Tab;

public class CreateAddQuestionTab extends Tab {

    private static CreateAddQuestionTab createAddQuestionTab;



    private CreateAddQuestionTab(){
        super("+ Questions to Quiz", CreateAddQuestionBox.getCreateAddQuestionBox());

    }

    public static CreateAddQuestionTab getCreateAddQuestionTab() {
        if(createAddQuestionTab == null){

            createAddQuestionTab = new CreateAddQuestionTab();
        }
        return createAddQuestionTab;
    }


    public void closeTab(){
        CreateAddQuestionTab tab = getCreateAddQuestionTab();
        EventHandler<Event> handler = tab.getOnClosed();
        if(handler != null){
            handler.handle(null);
        }else{
            tab.getTabPane().getTabs().remove(tab);
        }

    }


}
