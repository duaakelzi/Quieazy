package requests;

import application.ClientAgent;
import data.*;

import java.util.ArrayList;

public class CheckCorrectAnswerC {
    public CheckCorrectAnswerC() {
    }

    private  Message request = new Message();
    ArrayList<ChoicesData> answers;
    String [] correctAnswers;
    int result=0;
    public void checkAnswers(QuizData quiz,String [] SelectedAnswer) {
        ClientAgent clientAgent = ClientAgent.getClientAgent();
        ArrayList<QuestionData> questions;
        answers = new ArrayList<ChoicesData>();

        System.out.println("quizResult"+quiz.getName());
        questions = quiz.getQuestions();
        correctAnswers = new String[questions.size()];
        for (int i = 0; i < questions.size(); i++) {
            answers = questions.get(i).getAnswers();
        }
        for (int l = 0; l < correctAnswers.length; l++) {
            for (int j = 0; j < answers.size(); j++) {
                if (answers.get(j).isCorrect()) {
                    correctAnswers[l] = answers.get(j).getChoiceDescription();
                }

            }
        }
        for (int m = 0; m  < questions.size(); m++) {
            if (SelectedAnswer[m] == correctAnswers[m]) {
                result += questions.get(m).getPoint();
                System.out.println("res "  +result);
            }


        }
        request.task = "SAVE_RESULT";
        request.quizData=quiz;
        ResultData re=new ResultData();
        re.setStatistics(result);
        if (result > quiz.getThreshold())
        {
            re.setPassed(true);

        }
        else { re.setPassed(false);}
        request.resultData=re;
        //Message response =
        clientAgent.sendAndWaitForResponse(request);
    }
}
