package requests;

import application.ClientAgent;
import data.*;
import gui.PlayQuizBox;

import java.util.ArrayList;

public class CheckCorrectAnswerC {
    private int result=0;
    private  Message request = new Message();
    ResultData re=new ResultData();


    public CheckCorrectAnswerC() {
    }

    public Message checkAnswers(QuizData quiz) {
        ClientAgent clientAgent = ClientAgent.getClientAgent();

        //this.setResult(result);
        request.task = "SAVE_RESULT";
        request.quizData=quiz;
        request.userData=UserRequests.getCurrentUser();

        re.setPoints((int) PlayQuizBox.getPlayQuizBox().calculationUserPoints());
        if ((PlayQuizBox.getPlayQuizBox().calculationUserPoints()/PlayQuizBox.getPlayQuizBox().calculationTotalQuizPoints())*100 > quiz.getThreshold())
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
