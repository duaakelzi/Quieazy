package data;

import java.util.ArrayList;

public class Question {

    private final String question;
    private final ArrayList<Answer> answers;



    public Question(String question, ArrayList<Answer> answers) {
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
