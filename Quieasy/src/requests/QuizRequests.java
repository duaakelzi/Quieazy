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

    /**
     * this method should make a request to the server to persist new Quiz in the DB
     * the Quiz is hold by the message which will be sent with the request,
     * then get the response that telling us if the Question are persisted successfully or not
     */
    public static boolean createNewQuiz(QuizData newQuizData, UserData user){
        ClientAgent clientAgent = ClientAgent.getClientAgent();
        request = new Message();
        request.task = "CREATE_QUIZ";

        // set user data so that server knows which user created this quiz
        request.userData = user;

        //first time you make an empty quiz persistent
        request.quizData = newQuizData;
        response = clientAgent.sendAndWaitForResponse(request);
        return response.status;
    }
    /**
     * this method to request all Quizzes for a specific given user from the DB
     * then get the response from the server that is holding the requested Quizzes
     */
   public static ArrayList<QuizData> fetchAllUserQuizzes(UserData user){
       ClientAgent clientAgent = ClientAgent.getClientAgent();
       request = new Message();
       request.task = "FETCH_ALL_USER_QUIZZES";
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
    /**
     * this method to request all Quizzes from the server
     * then get the response from the server that is holding the requested Quizzes
     */
    public static ArrayList<QuizData> fetchAllQuizzes(){
        ClientAgent clientAgent = ClientAgent.getClientAgent();
        request = new Message();
        request.task = "FETCH_ALL_QUIZZES";

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
}
