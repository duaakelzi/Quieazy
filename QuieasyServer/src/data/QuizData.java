package data;

import java.io.Serializable;
import java.util.ArrayList;

public class QuizData implements Serializable {
    private Long id;
    private String course;
    private String name;
    private double threshold;
    private int timer;
    private ArrayList<QuestionData> questions;

    public QuizData() {  }
    // fetchQuestions requires quizID
    public QuizData(Long id, String course, String name, double threshold, int timer) {
        this.id = id;
        this.course = course;
        this.name = name;
        this.threshold = threshold;
        this.timer = timer;
    }
    // studyprogram removed from the constructor of QuizData, because we don't need it:
    // if a course that holds the quiz, belongs to one or two programs, makes no difference for the quiz
    public QuizData(String course, String name, double threshold, int timer, ArrayList<QuestionData> questions) {
        this.course = course;
        this.name = name;
        this.threshold = threshold;
        this.timer = timer;
        this.questions = questions;
    }

    // because fetchAllQuizzes doesn't need the questions yet
    public QuizData(String course, String name, double threshold, int timer) {
        // this.program = program;
        this.course = course;
        this.name = name;
        this.threshold = threshold;
        this.timer = timer;
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

//    public String getProgram() {
//        return program;
//    }
//
//    public void setProgram(String program) {
//        this.program = program;
//    }

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
