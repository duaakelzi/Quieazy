package dataServer;

import data.ChoicesData;
import domainServer.Question;
import domainServer.Quiz;
import domainServer.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class QuestionData {
    private Long id;
    private String questionText;
    private int points;
    private boolean isCorrect; //so far not mapped to DB ----> how do we inform the client about correctly-answered questions?
    private User user;
    
    private List<Question> allQuestions = new ArrayList<>();
    private Set<Quiz> quiz = new HashSet<Quiz>(0);
    private List<ChoicesData> questionChoices;
    
    //c'tors
    public QuestionData(Long id, String questionText, int points, User user) {
        this.id = id;
        this.questionText = questionText;
        this.points = points;
        this.user = user;
    }
    public QuestionData(String questionText, int points, boolean isCorrect) {
        this.questionText = questionText;
        this.points = points;
        this.isCorrect = isCorrect;
    }
    public QuestionData(Long id, String questionText, List<ChoicesData> questionChoices, int points, User user) {
        this.id = id;
        this.questionText = questionText;
        this.questionChoices = questionChoices;
        this.points = points;
        this.user = user;
    }

    //getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean getCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        this.isCorrect = correct;
    }

    public List<Question> getAllQuestions() {
        return allQuestions;
    }

    public void setAllQuestions(List<Question> allQuestions) {
        this.allQuestions = allQuestions;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Quiz> getQuiz() {
        return quiz;
    }

    public void setQuiz(Set<Quiz> quiz) {
        this.quiz = quiz;
    }

    public List<ChoicesData> getQuestionChoices() {
        return questionChoices;
    }

    public void setQuestionChoices(List<ChoicesData> questionChoices) {
        this.questionChoices = questionChoices;
    }
}
