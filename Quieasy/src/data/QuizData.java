package data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Quiz holds several data in order to saved and published
 * 1. Quiz belongs to a specific course
 * 2.Quiz has a unique name
 * 3.Quiz has a threshold as a indicator for pass o fail of the quiz
 * 4.Quiz is solved in a specific time constraint having a count down timer
 * 5.Quiz has a owner who it created
 * 6.Quiz have at least one question
 */

public class QuizData implements Serializable {
    private String course;
    private String name;
    private double threshold;
    private int timer;
    private UserData user;
    private ArrayList<QuestionData> questions;

    public QuizData() {
    }

    // studyprogram removed from the constructor of QuizData, because we don't need it:
    // if a course that holds the quiz, belongs to one or two programs, makes no difference for the quiz
    //  only one course with same name allowed per SP
    // SP with same course content will have different names (German/English)

    /**
     * Constructor to create new Quiz
     *
     * @param course
     * @param name
     * @param threshold
     * @param timer
     * @param questions
     * @param user
     */
    public QuizData(String course, String name, double threshold, int timer, ArrayList<QuestionData> questions, UserData user) {
        this.course = course;
        this.name = name;
        this.threshold = threshold;
        this.timer = timer;
        this.questions = questions;
        this.user = user;
    }

    /**
     * Constructor that creates a new Quiz without questions
     *
     * @param course
     * @param name
     * @param threshold
     * @param timer
     */
    public QuizData(String course, String name, double threshold, int timer) {
        this.course = course;
        this.name = name;
        this.threshold = threshold;
        this.timer = timer;
    }

    /**
     * Getter for name of the Quiz
     *
     * @return
     */

    public String getName() {
        return name;
    }

    /**
     * Setter to change the name of the Quiz
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for Course to which belongs the Quiz
     *
     * @return the course where the quiz can be found
     */
    public String getCourse() {
        return course;
    }

    /**
     * Setter for course of the Quiz
     *
     * @param course
     */

    public void setCourse(String course) {
        this.course = course;
    }

    /**
     * Getter for threshold of the quiz
     *
     * @return threshold
     */
    public double getThreshold() {
        return threshold;
    }

    /**
     * Setter for Threshold of the Quiz
     *
     * @param threshold
     */
    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    /**
     * Getter for timer of the Quiz
     *
     * @return timer
     */
    public int getTimer() {
        return timer;
    }

    /**
     * Setter for new value of timer for Quiz
     *
     * @param timer
     */
    public void setTimer(int timer) {
        this.timer = timer;
    }

    /**
     * Getter of user of the Quiz that represent the owner
     *
     * @return user
     */
    public UserData getUser() {
        return user;
    }

    /**
     * Setter of the user
     *
     * @param user
     */

    public void setUser(UserData user) {
        this.user = user;
    }

    /**
     * Add questions to Quiz
     *
     * @param questionData
     */
    public void addQuestions(ArrayList<QuestionData> questionData) {
        for (int i = 0; i < questionData.size(); i++) {
            questions.add(questionData.get(i));
        }
    }

    /**
     * Getter for Questions
     *
     * @return all question from Quiz
     */
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