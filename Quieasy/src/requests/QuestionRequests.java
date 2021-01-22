package requests;
import application.ClientAgent;
import data.*;

import java.util.ArrayList;

public class QuestionRequests {
    // private UserData ownerQuiz;
    private static Message request;
    /**
     * this method should make a request to the server code to persist new Questions in the DB
     * that is hold by the message which will be sent with the request
     * then get the response that tell us if the Question are persisted successfully or not
     * @param user the user that wants to create the questions
     * @param quiz the quiz that contains the questions
     * @param questionsToPersist arrayList of the created questions
     * @return response : the response message telling us if the work is done
     */
    //  2) ask the message to add questions to quiz 3) maybe update quiz to add
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
    /**
     * this method to request all questions for a specific given quiz from the DB
     * then get the response from the server that is holding the requested Question
     * @param quiz the quiz that we want to retrieve the questions from
     * @return response.questionData list of the retrieved questions
     */
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
    /**
     * this method to request all Questions from the server
     * then get the response from the server that is holding the requested Questions
     * @return response.questionData list of the retrieved questions
     */
    public static ArrayList<QuestionData> fetchAllQuestions() {
        ClientAgent clientAgent = ClientAgent.getClientAgent();
        request = new Message();
        request.task = "FETCH_ALL_EXISTING_QUESTIONS";
        ;
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
