package data;

import java.util.ArrayList;

public class Quiz {
    private Long id;
    private String program;
    private String course;
    private String name;
    private double threshold;
    private int timer;
    private ArrayList<Question> questions;
    private String firstName;
    private String lastName;

    public Quiz() {

    }

    public Quiz(String program, String course, String name, double threshold, int timer, ArrayList<Question> questions) {
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

    public String getName() {
        return name;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addQuestion(Question q){
        questions.add(q);
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }
    public String getOwnerQuiz() {
        return firstName +" " + lastName;
    }

}
