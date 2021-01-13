package gui;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Tab;

public class PlayQuizTab extends Tab{



    private static PlayQuizTab playQuizTab;
    // constructor can only be accessed from within
    public PlayQuizTab() {
        super("PLAY ►", PlayQuizBox.getPlayQuizBox());
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

        }




    }

