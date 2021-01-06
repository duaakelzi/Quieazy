package data;

import java.io.Serializable;
import java.util.ArrayList;

//fully consistent with client-side QuestionData
public class QuestionData implements Serializable {

    private String question;
    private int points;
    private ArrayList<ChoicesData> answers;

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

    public void setAnswers(ArrayList<ChoicesData> choicesData) { this.answers = choicesData;}
    public String printAnswers() {
        String res = "";
        for(int i = 0; i < this.answers.size();i++) {
            res += "Choice: " + answers.get(i).getChoiceDescription();
        }
        return res;
    }
}