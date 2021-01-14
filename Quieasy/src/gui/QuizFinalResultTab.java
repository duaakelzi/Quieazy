package gui;

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




}
