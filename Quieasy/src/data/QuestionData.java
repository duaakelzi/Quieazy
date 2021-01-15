package data;

import java.io.Serializable;
import java.util.ArrayList;

public class QuestionData implements Serializable {

    private String question;
    private int points;
    private ArrayList<ChoicesData> answers = new ArrayList<>();
    private Long id;

    public QuestionData(){}

    public QuestionData(String question, ArrayList<ChoicesData> answers,int points) {
        this.question = question;
        this.answers = answers;
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getQuestion() {  return question;  }

    public ArrayList<ChoicesData> getAnswers() {   return answers;  }

    public void setQuestion(String question){ this.question = question; }

    public String getCorrectAnswer(){
        String answerCorrect = "";
        for (ChoicesData answer : answers) {
            if (answer.isCorrect()) {
                answerCorrect = answer.getChoiceDescription();
            }
        }
        return answerCorrect;
    }

    public void setAnswers(ArrayList<ChoicesData> choicesData) { this.answers = choicesData;}
    public void setId (Long id) { this.id = id; }
    public Long getId() {return this.id;}

    public String printAnswers() {
        String res = "";
        for(int i = 0; i < this.answers.size();i++) {
            res += "Choice: " + answers.get(i).getChoiceDescription();
        }
        return res;
    }

    @Override
    public boolean equals(Object o) {
        QuestionData questionToCompare = (QuestionData) o;
        //String question, ArrayList<ChoicesData> answers,int points
        return (question.equals(questionToCompare.getQuestion()) &&
                answers.equals(questionToCompare.getAnswers()) &&
                points == questionToCompare.getPoints());
    }
}