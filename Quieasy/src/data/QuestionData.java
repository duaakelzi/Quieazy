package data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * QuestionData class creates the object that holds question of type String and ArrayList of Choices
 * Along with this data every question has an ID and belongs to an active User
 * fully consistent with client-side QuestionData
 */
public class QuestionData implements Serializable {

    private String question;
    private int points;
    private ArrayList<ChoicesData> answers = new ArrayList<>();
    private Long id;
    private UserData user;

    public QuestionData() {
    }

    /**
     * Constructor that creates QuestionData qith a question, ArrayList of Choices and points
     * All the data is defined by the user in the new Question creation
     *
     * @param question
     * @param answers
     * @param points
     */
    public QuestionData(String question, ArrayList<ChoicesData> answers, int points) {
        this.question = question;
        this.answers = answers;
        this.points = points;
    }

    /**
     * Constructor that creates QuestionData object from a question of type String, ArrayList of answers,
     * ID and the owner of question
     *
     * @param question
     * @param points
     * @param answers
     * @param id
     * @param user
     */
    public QuestionData(String question, int points, ArrayList<ChoicesData> answers, Long id, UserData user) {
        this.question = question;
        this.points = points;
        this.answers = answers;
        this.id = id;
        this.user = user;
    }

    /**
     * Getter for the user variable
     *
     * @return the user
     */
    public UserData getUser() {
        return user;
    }

    /**
     * Setter for the user variable
     *
     * @param user
     */
    public void setUser(UserData user) {
        this.user = user;
    }

    /**
     * Getter to get the points of the question
     *
     * @return number of points
     */
    public int getPoints() {
        return points;
    }

    /**
     * Setter to set the number of point for Question
     *
     * @param points
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Getter Question
     *
     * @return question containt of type String
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Getter for Answers
     *
     * @return an arrayList of Answers
     */
    public ArrayList<ChoicesData> getAnswers() {
        return answers;
    }

    /**
     * Setter for Question
     *
     * @param question
     */

    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * Getter for correct answer of the Question
     *
     * @return the content of correct answer of type String
     */
    public String getCorrectAnswer() {
        String answerCorrect = "";
        for (ChoicesData answer : answers) {
            if (answer.isCorrect()) {
                answerCorrect = answer.getChoiceDescription();
            }
        }
        return answerCorrect;
    }

    /**
     * Setter for Answers
     *
     * @param choicesData as a list of Answers
     */
    public void setAnswers(ArrayList<ChoicesData> choicesData) {
        this.answers = choicesData;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    /**
     * toString method to print all the Answers of the Question
     *
     * @return a string that contain all the Answers
     */
    public String printAnswers() {
        String res = "";
        for (int i = 0; i < this.answers.size(); i++) {
            res += "Choice: " + answers.get(i).getChoiceDescription();
        }
        return res;
    }

    @Override
    public boolean equals(Object o) {
        QuestionData questionToCompare = (QuestionData) o;
        return (question.equals(questionToCompare.getQuestion()) &&
                answers.equals(questionToCompare.getAnswers()) &&
                points == questionToCompare.getPoints());
    }
}