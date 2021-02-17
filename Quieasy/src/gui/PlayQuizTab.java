package gui;

import guib.QuizBrowser;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Tab;

public class PlayQuizTab extends Tab {


    private static PlayQuizTab playQuizTab;

    /**
     * Constructor to create the Tab of the PLay Quiz Window
     */
    private PlayQuizTab() {

        super("PLAY ►", PlayQuizBox.getPlayQuizBox());

        this.setOnClosed(t -> PlayQuizTab.reset());

    }

    /**
     * Singleton pattern that allow to have on tab at a time of play Quiz
     *
     * @return the playQuiz Tab
     */
    public static PlayQuizTab getPlayQuizTab() {

        if (playQuizTab == null) playQuizTab = new PlayQuizTab();

        return playQuizTab;

    }

    public static void reset() {
        QuizBrowser.setQuizToPlay(null);
        PlayQuizBox.reset();
        playQuizTab = null;
    }

    /**
     * Deleting the PlayQuiz Tab from the window
     */
    public void closeTab() {
        PlayQuizTab tab = getPlayQuizTab();
        EventHandler<Event> handler = tab.getOnClosed();
        if (handler != null) {
            handler.handle(null);
        } else {
            tab.getTabPane().getTabs().remove(tab);
        }

        PlayQuizTab.reset();

    }

}

