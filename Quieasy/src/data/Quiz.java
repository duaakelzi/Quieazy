package data;

import java.util.ArrayList;

public class Quiz {
    private String program;
    private String course;
    private String name;
    private double threshold;
    private int timer;

    private ArrayList<Question> questions;

    public Quiz(String program, String course, String name, double threshold, int timer, ArrayList<Question> questions) {
        this.program = program;
        this.course = course;
        this.name = name;
        this.threshold = threshold;
        this.timer = timer;
        this.questions = questions;
    }

    public void addQuestion(Question q){
        questions.add(q);
    }


}
