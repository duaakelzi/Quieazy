package gui;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Tab;

public class CreateQuizResultTab extends Tab {
    private static CreateQuizResultTab createQuizResultTab;



    private CreateQuizResultTab(){
        //super("+ Questions to Quiz", CreateQuizResultBox.getCreateQuizResultBox());

    }

    public static CreateQuizResultTab getCreateQuizResultTab() {
        if(createQuizResultTab == null){

            createQuizResultTab = new CreateQuizResultTab();
        }
        return createQuizResultTab;
    }


    public void closeTab(){
        CreateQuizResultTab tab = getCreateQuizResultTab();
        EventHandler<Event> handler = tab.getOnClosed();
        if(handler != null){
            handler.handle(null);
        }else{
            tab.getTabPane().getTabs().remove(tab);
        }

    }


}
