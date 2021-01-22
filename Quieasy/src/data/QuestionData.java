package data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * this class QuestionData is serializable forms of the domain class Question to be sent using messages
 * with constructor and all getter and setter
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
     * constructor 1 for QuestionData
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
     * constructor 2 for QuestionData
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

    public UserData getUser() {
        return user;
    }

    public void setUser(UserData user) {
        this.user = user;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ArrayList<ChoicesData> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<ChoicesData> choicesData) {
        this.answers = choicesData;
    }

    /**
     * method to print the correct answer
     *
     * @return String
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

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String printAnswers() {
        String res = "";
        for (int i = 0; i < this.answers.size(); i++) {
            res += "Choice: " + answers.get(i).getChoiceDescription();
        }
        return res;
    }

    /**
     * override to enable comparisons for testing
     *
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        QuestionData questionToCompare = (QuestionData) o;
        //String question, ArrayList<ChoicesData> answers,int points
        return (question.equals(questionToCompare.getQuestion()) &&
                answers.equals(questionToCompare.getAnswers()) &&
                points == questionToCompare.getPoints());
    }
}