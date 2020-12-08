package dataServer;

import actions.RetrieveObjects;
import domainServer.Question;
import domainServer.Quiz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class QuizData implements Serializable {

    private Long id;
    private String quizName;
    private boolean isPublic;
    private int threshold;
<<<<<<< HEAD
    //sp, course --> quiz is not in sp, quiz is in course. course is in sp.
=======
    private String course;
>>>>>>> d2cb75b2d24947efef7b882f953cfc1b573a8057
    private List<Quiz> allQuizzes = new ArrayList<>();
    //old questionsList and new questionList ?? rationale: processing is faster when we don't need to check for existence of new questions
    private List<Question> oldQuestions;
    private List<Question> newQuestions;

    //c'tor
    public QuizData(Long id,String quizName, boolean isPublic, int threshold,String course) {
        this.id=id;
        this.quizName = quizName;
        this.isPublic = isPublic;
        this.threshold = threshold;
        this.course=course;
    }
    //getter and setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }
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

    public String getCourse() { return course; }

    public void setCourse(String course) { this.course = course; }


    public List<Quiz> getAllQuizzes() {
        return allQuizzes;
    }

    //methods
    //retrieve all quizzes for that user
    public void fetchAll(String email) {
        allQuizzes = RetrieveObjects.retrieveQuizzes(email);
    }

}
