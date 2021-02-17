// create quiz tab

package gui;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Tab;


public class CreateQuizTab extends Tab {

    private static CreateQuizTab createQuizTab;


    /**
     * Constructor of CreateQuizBox
     */
    private CreateQuizTab() {
        super("New Quiz", CreateQuizBox.getCreateQuizBox());


    }

    /**
     * Creates a tab one time, Singleton Pattern
     *
     * @return create Quiz Tab that holds the Create Quiz Box responsible to create the Quiz
     */
    public static CreateQuizTab getCreateQuizTab() {
        if (createQuizTab == null) createQuizTab = new CreateQuizTab();
        return createQuizTab;
    }

    /**
     * Remove the Tab and the Box from the user View
     */
    public void closeTab() {
        CreateQuizTab tab = getCreateQuizTab();
        EventHandler<Event> handler = tab.getOnClosed();
        if (handler != null) {
            handler.handle(null);
        } else {

            tab.getTabPane().getTabs().remove(tab);


        }

    }


}
