package gui;

import javafx.scene.control.Tab;

public class PlayQuizTab extends Tab {

    private static PlayQuizTab playQuizTab;

    public  PlayQuizTab(){
        super("Play", PlayQuizBox.getPlayQuizBox());
    }

    public static PlayQuizTab getPlayQuizTab(){
        if(playQuizTab == null){
            playQuizTab = new PlayQuizTab();
        }
        return playQuizTab;
    }

}
