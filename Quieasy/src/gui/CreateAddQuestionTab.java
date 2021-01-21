package gui;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Tab;

public class CreateAddQuestionTab extends Tab {

    private static CreateAddQuestionTab createAddQuestionTab;


    /**
     * Constructor that create the tab that holds the Add Question window
     */
    private CreateAddQuestionTab() {
        super("+ Questions to Quiz", CreateAddQuestionBox.getCreateAddQuestionBox());

    }

    /**
     * Singleton tab that is opened on time at once to add questions to the quiz
     *
     * @return the tab of add question window
     */
    public static CreateAddQuestionTab getCreateAddQuestionTab() {
        if (createAddQuestionTab == null) {

            createAddQuestionTab = new CreateAddQuestionTab();
        }
        return createAddQuestionTab;
    }

    /**
     * Close the tab that holds the add Questions to the Quiz
     */
    public void closeTab() {
        CreateAddQuestionTab tab = getCreateAddQuestionTab();
        EventHandler<Event> handler = tab.getOnClosed();
        if (handler != null) {
            handler.handle(null);
        } else {
            tab.getTabPane().getTabs().remove(tab);
        }

    }


}
