package data;

import java.util.ArrayList;

public class Quiz {
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

    public void addQuestion(Question q){
        questions.add(q);
    }
}
