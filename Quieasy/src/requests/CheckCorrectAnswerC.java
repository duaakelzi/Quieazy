package requests;

import application.ClientAgent;
import data.*;

import java.util.ArrayList;

public class CheckCorrectAnswerC {
    private int result=0;
    private  Message request = new Message();
    ArrayList<ChoicesData> answers;
    ResultData re=new ResultData();

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    String [] correctAnswers;
    public CheckCorrectAnswerC() {
    }



    public Message checkAnswers(QuizData quiz,String [] SelectedAnswer) {
        ClientAgent clientAgent = ClientAgent.getClientAgent();
        ArrayList<QuestionData> questions;
        answers = new ArrayList<ChoicesData>();


        System.out.println("quizResult"+quiz.getName());
       // System.out.println("select"+SelectedAnswer[2]);


        questions = quiz.getQuestions();
        questions.get(0).getAnswers();
        boolean correctres[]=new boolean[questions.size()];
        correctAnswers = new String[questions.size()];
        for (int i = 0; i < questions.size(); i++)
            for (int j=0;j<questions.get(i).getAnswers().size();j++){
                if(questions.get(i).getAnswers().get(j).isCorrect())
               answers.add(questions.get(i).getAnswers().get(j));

                 }

for (int i=0;i<answers.size();i++)
{
    System.out.println("answer "+answers.get(i).getChoiceDescription());
    correctAnswers[i] = answers.get(i).getChoiceDescription();
}



        for (int m = 0; m  < questions.size(); m++) {
            if (SelectedAnswer[m] == correctAnswers[m]) {
                correctres[m]=true;
                result += questions.get(m).getPoints();
                System.out.println("res "  +result);

            }
            else
                {
                    correctres[m]=false;
                }

        re.setCorrectAnswers(correctres);

        }
        //this.setResult(result);
        request.task = "SAVE_RESULT";
        request.quizData=quiz;

        re.setStatistics(result);
        if (result > quiz.getThreshold())
        {
            re.setPassed(true);
            request.resultData=re;
        }
        else
            { re.setPassed(false);
                request.resultData=re;
             }
        Message response =  clientAgent.sendAndWaitForResponse(request);
        if(response != null && response.status){
            System.out.println("Result Saved successfully.");
        }else if(response != null && (!response.status)){
            //informed user that no SPs returned
            System.out.println("Result not saved.");
        }
        return request;
    }


}
