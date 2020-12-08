package gui;

import data.Quiz;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Tab;

public class CreateAddQuestionTab extends Tab {

    private static CreateAddQuestionTab createAddQuestionTab;
    private Quiz quiz;


    private CreateAddQuestionTab(Quiz quiz){
        super("+ Questions to Quiz", CreateAddQuestionBox.getCreateAddQuestionBox());
        this.quiz = quiz;
    }

    public static CreateAddQuestionTab getCreateAddQuestionTab(Quiz quiz) {
        if(createAddQuestionTab == null){

            createAddQuestionTab = new CreateAddQuestionTab(quiz);
        }
        return createAddQuestionTab;
    }


    public void closeTab(){
        CreateAddQuestionTab tab = getCreateAddQuestionTab(quiz);
        EventHandler<Event> handler = tab.getOnClosed();
        if(handler != null){
            handler.handle(null);
        }else{
            tab.getTabPane().getTabs().remove(tab);
        }

    }
}
