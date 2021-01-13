package requests;
import application.ClientAgent;
import data.*;

import java.util.ArrayList;

public class QuestionRequests {
    // private UserData ownerQuiz;
    private static Message request;

    //this method should: 1) persist new questions; 2) ask the message to add questions to quiz 3) maybe update quiz to add
    // new questions there on class level
    public static boolean persistNewQuestions(UserData user, QuizData quiz, ArrayList<QuestionData> questionsToPersist) {
        ClientAgent clientAgent = ClientAgent.getClientAgent();
        request = new Message();
        request.task = "CREATE_QUESTIONS";
        request.quizData = quiz;
        request.questionData = questionsToPersist;
        request.userData = user;
        Message response = clientAgent.sendAndWaitForResponse(request);
        if(response.status) {
            int i = 0;
            for(QuestionData q : questionsToPersist) {
                q.setId(response.questionData.get(i).getId());
                System.out.println("question id set (client side).");
                i++;
            }
        }
        return response.status;
    }

    //only text or choices were updated, not the relationships
    public static void updateEditedQuestions(ArrayList<QuestionData> questionsToUpdate){
        ClientAgent clientAgent = ClientAgent.getClientAgent();
        request = new Message();
        request.task = "SAVE_QUESTION_EDITS";
        request.questionData = questionsToUpdate;

        clientAgent.sendAndWaitForResponse(request);
    }
    //update the relationships of the existing,old questions
    public static void addOldQuestions(QuizData quiz, ArrayList<QuestionData> questionsToAdd) {
        ClientAgent clientAgent = ClientAgent.getClientAgent();
        request = new Message();
        request.task = "ADD_OLD_QUESTIONS";
        request.quizData = quiz;
        request.questionData = questionsToAdd;
//        saveQuiestionsMsg.userData = new UserData(UserC.getCurrentUser().getFirstName(),
//                                            UserC.getCurrentUser().getLastName(),
//                                            UserC.getCurrentUser().getEmail());
        clientAgent.sendAndWaitForResponse(request);
    }

    //method to request all questions for given quiz. For now the received arraylist is not passed back to the gui from client decoder.
    public static ArrayList<QuestionData> fetchQuizQuestions(QuizData quiz) {
        ClientAgent clientAgent = ClientAgent.getClientAgent();
        request = new Message();
        request.task = "FETCH_ALL_QUESTIONS";
        request.quizData = quiz;
        Message response = clientAgent.sendAndWaitForResponse(request);

        if(response != null && response.status){
            System.out.println("Questions retrieved successfully.");
        }else if(response != null && (!response.status)){
            //informed user that no SPs returned
            System.out.println("Questions not retrieved.");
        }
        return response.questionData;
    }

    public static boolean deleteQuestions(QuizData quiz, ArrayList<QuestionData> questions) {
        ClientAgent clientAgent = ClientAgent.getClientAgent();
        request = new Message();
        request.task = "DELETE_QUESTIONS";
        request.quizData = quiz;
        request.questionData = questions;
        Message response = clientAgent.sendAndWaitForResponse(request);
        //can return null
        return response.status;
    }
}
