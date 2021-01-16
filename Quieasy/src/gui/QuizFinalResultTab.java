package gui;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Tab;

public class QuizFinalResultTab extends Tab{

    private static QuizFinalResultTab quizFinalResultTab;

    public QuizFinalResultTab() {
        super("Finish QUIZ", QuizFinalResultBox.getQuizFinalResultBox());
    }

    public static QuizFinalResultTab getQuizFinalResultTab(){
        if(quizFinalResultTab == null){
            quizFinalResultTab = new QuizFinalResultTab();
        }
        return quizFinalResultTab;
    }

    public void closeTab(){
        QuizFinalResultTab tab = getQuizFinalResultTab();
        EventHandler<Event> handler = tab.getOnClosed();
        if(handler != null){
            handler.handle(null);
        }else{
            tab.getTabPane().getTabs().remove(tab);
        }

    }




}
