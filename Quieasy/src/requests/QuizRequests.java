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
    public static ArrayList<QuizData> fetchAllQuizzes(){
        ClientAgent clientAgent = ClientAgent.getClientAgent();
        request = new Message();
        request.task = "FETCH_ALL_QUIZZES";

        response = clientAgent.sendAndWaitForResponse(request);
        System.out.println(response.allQuizzes.get(0).getName());
        System.out.println(response.allQuizzes.get(0).getQuestions().get(0).getQuestion());
        System.out.println(response.allQuizzes.get(0).getQuestions().get(0).getAnswers().get(0).getChoiceDescription());
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
