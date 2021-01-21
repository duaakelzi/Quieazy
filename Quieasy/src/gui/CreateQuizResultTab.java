package gui;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Tab;

public class CreateQuizResultTab extends Tab {

    private static CreateQuizResultTab quizFinalResultTab;

    /**
     * Constructor to create Final results Tab after the Quiz have been submitted
     */
    private CreateQuizResultTab() {
        super("Finish QUIZ", CreateQuizResultBox.getQuizFinalResultBox());
    }

    /**
     * Singleton Pattern that creates only one Tab on the multiple clicked on the buttons Submit
     *
     * @return the view of final results of the Quiz
     */
    public static CreateQuizResultTab getQuizFinalResultTab() {
        if (quizFinalResultTab == null) {
            quizFinalResultTab = new CreateQuizResultTab();
        }
        return quizFinalResultTab;
    }

    /**
     * CLose the Final results Tab and remove the Window from user View
     */
    public void closeTab() {
        CreateQuizResultTab tab = getQuizFinalResultTab();
        EventHandler<Event> handler = tab.getOnClosed();
        if (handler != null) {
            handler.handle(null);
        } else {
            tab.getTabPane().getTabs().remove(tab);
        }

    }


}
