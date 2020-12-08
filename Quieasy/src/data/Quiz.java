package data;

import java.util.ArrayList;

public class Quiz {
    private Long id;
    private String program;
    private String course;
    private String name;
    private double threshold;
    private String description;

    private ArrayList<Question> questions;

    public Quiz(String program, String course, String name, double threshold, String description, ArrayList<Question> questions) {
        this.program = program;
        this.course = course;
        this.name = name;
        this.threshold = threshold;
        this.description = description;
        this.questions = questions;
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
}
