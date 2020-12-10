package data;

import java.io.Serializable;
import java.util.ArrayList;

public class QuestionData implements Serializable {

    private final String question;
    private final ArrayList<Answer> answers;



    public QuestionData(String question, ArrayList<Answer> answers) {
        this.question = question;
        this.answers = answers;

    }


    public String getQuestion() {

        return question;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }
}
