package gui;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Tab;

public class CreateQuestionBankTab extends Tab {

    private static CreateQuestionBankTab createQuestionBankTab;

    /**
     * Constructor creates the Tab that holds the Question Bank View Layer
     */
    private CreateQuestionBankTab() {
        super("Question Bank", CreateQuestionBankBox.getCreateQuestionBankBox());
    }

    /**
     * Singleton tab that is opened only one at time to avoid inconsistent data
     *
     * @return the tab that holds the view of add Question from Question bank
     */
    public static CreateQuestionBankTab getCreateQuestionBankTab() {
        if (createQuestionBankTab == null) {
            createQuestionBankTab = new CreateQuestionBankTab();
        }
        return createQuestionBankTab;
    }

    /**
     * Close the tab from user view
     */
    public void closeTab() {
        CreateQuestionBankTab tab = getCreateQuestionBankTab();
        EventHandler<Event> handler = tab.getOnClosed();
        if (handler != null) {
            handler.handle(null);
        } else {
            tab.getTabPane().getTabs().remove(tab);
        }

    }
}