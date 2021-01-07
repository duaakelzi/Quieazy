package requests;

import application.ClientAgent;
import data.Message;
import data.QuizData;
import gui.CreateQuizBox;

public class QuizC {
    private static Message request = new Message();

    public static void createNewQuiz(QuizData newQuizData){
        ClientAgent clientAgent = ClientAgent.getClientAgent();
        request.task = "CREATE_QUIZ";

        // set user data so that server knows which user created this quiz
       /* saveQuizMsg.userData = new UserData(UserC.getCurrentUser().getFirstName(),
                                            UserC.getCurrentUser().getLastName(),
                                            UserC.getCurrentUser().getEmail());*/

        //first time you make an empty quiz persistent
        request.quizData = new QuizData(newQuizData.getProgram(),newQuizData.getCourse(),newQuizData.getName(),newQuizData.getThreshold(),newQuizData.getTimer(),newQuizData.getQuestions());

        Message response = clientAgent.sendAndWaitForResponse(request);
        if(response != null && response.status){
           CreateQuizBox.showSuccessful();
        }else if(response != null && (!response.status)){
            //informed user that no SPs returned
            CreateQuizBox.showFailed();
        }
    }

   // public static ArrayList<QuizData>

}
