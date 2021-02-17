package gui;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Tab;

public class CreateQuestionChoicesTab extends Tab {
    private static CreateQuestionChoicesTab createQuestionChoicesTab;

    /**
     * Constructor creates the Box with fields ready to accept the Questions and answers from the user
     */
    private CreateQuestionChoicesTab() {
        super("Question Editor", CreateQuestionChoicesBox.getCreateQuestionChoicesBox());
    }

    /**
     * Singleton that creates a add new Question only on time on the button click add Question to keep the data consistent
     *
     * @return layout to insert the question with answers, correct answer and points
     */
    public static CreateQuestionChoicesTab getCreateQuestionChoicesTab() {
        if (createQuestionChoicesTab == null) {
            createQuestionChoicesTab = new CreateQuestionChoicesTab();
        }
        return createQuestionChoicesTab;
    }

    /**
     * Close the tab from window
     */
    public void closeTab() {
        CreateQuestionChoicesTab tab = getCreateQuestionChoicesTab();
        EventHandler<Event> handler = tab.getOnClosed();
        if (handler != null) {
            handler.handle(null);
        } else {
            tab.getTabPane().getTabs().remove(tab);
        }

    }
}
