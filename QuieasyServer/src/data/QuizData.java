package data;

import java.io.Serializable;
import java.util.ArrayList;

public class QuizData implements Serializable {
    private Long id;
    private String program;
    private String course;
    private String name;
    private double threshold;
    private int timer;
    private ArrayList<QuestionData> questions;
    private String firstName;
    private String lastName;

    public QuizData() {

    }

    public QuizData(String program, String course, String name, double threshold, int timer, ArrayList<QuestionData> questions) {
        this.program = program;
        this.course = course;
        this.name = name;
        this.threshold = threshold;
        this.timer = timer;
        this.questions = questions;
        //firstName = UserC.getCurrentUser().getFirstName();
        //lastName = UserC.getCurrentUser().getLastName();
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
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

    public void addQuestions(ArrayList<QuestionData> q) {
        for (int i = 0; i < q.size(); i++) {
            questions.add(q.get(i));
        }
    }

    public ArrayList<QuestionData> getQuestions() {
        return questions;
    }
    public String getOwnerQuiz() {
        return firstName +" " + lastName;
    }

}
