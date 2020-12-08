package dataServer;

import actions.RetrieveObjects;
import domainServer.Question;
import domainServer.Quiz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class QuizData implements Serializable {

    private String quizName;
    private boolean isPublic;
    private int threshold;
    //sp, course --> quiz is not in sp, quiz is in course. course is in sp.
    private List<Quiz> allQuizzes = new ArrayList<>();
    //old questionsList and new questionList ?? rationale: processing is faster when we don't need to check for existence of new questions
    private List<Question> oldQuestions;
    private List<Question> newQuestions;

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
        allQuizzes = RetrieveObjects.retrieveQuizzes(email);
    }

}
