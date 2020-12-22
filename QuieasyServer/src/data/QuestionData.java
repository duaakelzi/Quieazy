package data;

import java.io.Serializable;
import java.util.ArrayList;

//fully consistent with client-side QuestionData
public class QuestionData implements Serializable {

    private String question;
    private ArrayList<ChoicesData> answers;

    public QuestionData(){}

    //called in CreateQuestionChoicesBox.createnewQuestion
    public QuestionData(String question, ArrayList<ChoicesData> answers) {
        this.question = question;
        this.answers = answers;

    }


    public String getQuestion() {  return question; }

    public ArrayList<ChoicesData> getAnswers() { return answers; }

    public void setQuestion(String question){
        this.question = question;
    }

    public void setAnswers(ArrayList<ChoicesData> answers) {
        this.answers = answers;
    }

    //temporary print statement => to be improved
    //method to check if Question from client is passed with Choices
    public String printAnswers() {
        String res = "";
        for(int i = 0; i < this.answers.size();i++) {
            res += " Choice: " + answers.get(i).getChoiceDescription();
        }
        return res;
    }
}