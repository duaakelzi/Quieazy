package dataServer;

import domain.Question;
import domain.Quiz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class QuizData implements Serializable {

    private Long id;
    private String quizName;
    private boolean isPublic;
    private double threshold;
    private String course;
    int timer;
    private List<Quiz> allQuizzes = new ArrayList<>();
    //old questionsList and new questionList ?? rationale: processing is faster when we don't need to check for existence of new questions
    private List<Question> oldQuestions;
    private List<Question> newQuestions;
    public QuizData() {

    }
    //c'tor
    public QuizData(String quizName, boolean isPublic, double threshold,String course) {

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

    public double getThreshold() {
        return threshold;
    }
    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    public String getCourse() { return course; }

    public void setCourse(String course) { this.course = course; }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public List<Quiz> getAllQuizzes() {
        return allQuizzes;
    }

    //methods
    //retrieve all quizzes for that user
    //public void fetchAll(String email) {
       // allQuizzes = RetrieveObjects.retrieveQuizzes(email);
  //  }

}
