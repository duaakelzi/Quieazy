package dataServer;

import actions.RetrieveObjects;
import domainServer.Quiz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class QuizData implements Serializable {

    private String quizName;
    private boolean isPublic;
    private int threshold;
    private List<Quiz> allQuizzes = new ArrayList<>();

    //c'tor
    public QuizData(String quizName, boolean isPublic, int threshold) {
        this.quizName = quizName;
        this.isPublic = isPublic;
        this.threshold = threshold;
    }
    //getter and setters
    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }
    public List<Quiz> getAllQuizzes() {
        return allQuizzes;
    }

    //methods
    //retrieve all quizzes for that user
    public void fetchAll(String email) {
        allQuizzes = RetrieveObjects.retrieveQuizzes();
    }

}
