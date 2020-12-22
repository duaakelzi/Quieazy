package data;

import java.io.Serializable;
import java.util.ArrayList;

public class QuestionData implements Serializable {

    private String question;
    private ArrayList<ChoicesData> answers;

    public QuestionData(){}

    //called in CreateQuestionChoicesBox.createnewQuestion
    public QuestionData(String question, ArrayList<ChoicesData> answers) {
        this.question = question;
        this.answers = answers;

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