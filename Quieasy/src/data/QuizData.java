package data;

import java.io.Serializable;
import java.util.ArrayList;

public class QuizData implements Serializable {
    private String course;
    private String name;
    private double threshold;
    private int timer;
    private UserData user;
    private ArrayList<QuestionData> questions;

    public QuizData() { }

    // studyprogram removed from the constructor of QuizData, because we don't need it:
    // if a course that holds the quiz, belongs to one or two programs, makes no difference for the quiz
    //  only one course with same name allowed per SP
    // SP with same course content will have different names (German/English)
    public QuizData(String course, String name, double threshold, int timer, ArrayList<QuestionData> questions,UserData user) {
        this.course = course;
        this.name = name;
        this.threshold = threshold;
        this.timer = timer;
        this.questions = questions;
        this.user=user;
    }

    // because fetchAllUserQuizzes doesn't need the questions yet
    public QuizData(String course, String name, double threshold, int timer) {
        this.course = course;
        this.name = name;
        this.threshold = threshold;
        this.timer = timer;
    }

    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }
    public UserData getUser() { return user; }

    public void setUser(UserData user) { this.user = user; }

    public void addQuestions(ArrayList<QuestionData> q) {
        for(int i=0; i<q.size(); i++) {
            questions.add(q.get(i));
        }
    }

    public ArrayList<QuestionData> getQuestions() {
        return questions;
    }

    @Override
    public boolean equals(Object o) {
        QuizData quizToCompare = (QuizData) o;
        //String course, String name, double threshold, int timer)
        return (course.equals(quizToCompare.getCourse()) &&
                name.equals(quizToCompare.getName()) &&
                threshold == quizToCompare.threshold &&
                timer == quizToCompare.getTimer()
        );
    }
}