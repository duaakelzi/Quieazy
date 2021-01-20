package gui;

import guib.QuizBrowser;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Tab;

public class PlayQuizTab extends Tab{



    private static PlayQuizTab playQuizTab;
    // constructor can only be accessed from within
    private PlayQuizTab() {

        super("PLAY ►", PlayQuizBox.getPlayQuizBox());

        this.setOnClosed(new EventHandler<Event>() {
            @Override
            public void handle(Event t) {

                PlayQuizTab.reset();

            }

        });

    }



        // Gets the current instance -> Singleton
        public static PlayQuizTab getPlayQuizTab() {

            if (playQuizTab == null) playQuizTab = new PlayQuizTab();

            return playQuizTab;

        }

        public void closeTab(){
            PlayQuizTab tab = getPlayQuizTab();
            EventHandler<Event> handler = tab.getOnClosed();
            if(handler != null){
                handler.handle(null);
            }else{

                tab.getTabPane().getTabs().remove(tab);


            }

            PlayQuizTab.reset(); // ADDED BY CHERNET

        }

        public static void reset(){

            QuizBrowser.setQuizToPlay(null);
            PlayQuizBox.reset();
            playQuizTab = null;

        }

    }

