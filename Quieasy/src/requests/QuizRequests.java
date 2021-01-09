package requests;

import application.ClientAgent;
import data.Message;
import data.QuizData;
import data.UserData;
import gui.CreateQuizBox;

import java.util.ArrayList;

public class QuizRequests {
    private static Message request;
    private static Message response;

    public static long createNewQuiz(QuizData newQuizData, UserData user){
        ClientAgent clientAgent = ClientAgent.getClientAgent();
        request = new Message();
        request.task = "CREATE_QUIZ";

        // set user data so that server knows which user created this quiz
        request.userData = user;

        //first time you make an empty quiz persistent
        request.quizData = newQuizData;
        try {
            response = clientAgent.sendAndWaitForResponse(request);
            if(response.status){
                return response.quizData.getId();
            }
        }catch (Exception e) {
            System.out.println("Create quiz exception. " + e.getMessage());
        }
        return 0;
    }

   public static ArrayList<QuizData> fetchAllQuizzes(UserData user){
       ClientAgent clientAgent = ClientAgent.getClientAgent();
       request = new Message();
       request.task = "FETCH_ALL_QUIZZES";
       request.userData = user;
       response = clientAgent.sendAndWaitForResponse(request);
       if(response != null && response.status){
           System.out.println("Fetch quizzes successful.");
          return response.allQuizzes;
       }else if(response != null && (!response.status)){
           //informed user that no SPs returned
           System.out.println("Fetch quizzes failed.");
       }
       return response.allQuizzes;
   }

   public static boolean deleteQuiz(QuizData quiz) {
       ClientAgent clientAgent = ClientAgent.getClientAgent();
       request = new Message();
       request.task = "DELETE_QUIZ";
       request.quizData = quiz;
       response = clientAgent.sendAndWaitForResponse(request);
       return response.status;
   }

}
