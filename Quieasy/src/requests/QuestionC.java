package requests;
import application.ClientAgent;
import data.Message;
import data.QuestionData;
import data.QuizData;

import java.util.ArrayList;

public class QuestionC {
    // private UserData ownerQuiz;
    private static Message request = new Message();

    //this method should: 1) persist new questions; 2) ask the message to add questions to quiz 3) maybe update quiz to add
    // new questions there on class level
    public static void persistNewQuestions(QuizData quiz, ArrayList<QuestionData> questionsToPersist) {
        ClientAgent clientAgent = ClientAgent.getClientAgent();

        request.task = "CREATE_QUESTIONS";
        request.quizData = quiz;
        request.questionData = questionsToPersist;
//        saveQuiestionsMsg.userData = new UserData(UserC.getCurrentUser().getFirstName(),
//                                            UserC.getCurrentUser().getLastName(),
//                                            UserC.getCurrentUser().getEmail());
        Message response = clientAgent.sendAndWaitForResponse(request);
        if(response != null && response.status){
            System.out.println("Questions saved successfully.");
        }else if(response != null && (!response.status)){
            //informed user that no SPs returned
            System.out.println("Questions not saved.");
        }
    }

    //only text or choices were updated, not the relationships
    public static void updateEditedQuestions(ArrayList<QuestionData> questionsToUpdate){
        ClientAgent clientAgent = ClientAgent.getClientAgent();

        request.task = "SAVE_QUESTION_EDITS";
        request.questionData = questionsToUpdate;

        clientAgent.sendAndWaitForResponse(request);
    }
    //update the relationships of the existing,old questions
    public static void addOldQuestions(QuizData quiz, ArrayList<QuestionData> questionsToAdd) {
        ClientAgent clientAgent = ClientAgent.getClientAgent();

        request.task = "ADD_OLD_QUESTIONS";
        request.quizData = quiz;
        request.questionData = questionsToAdd;
//        saveQuiestionsMsg.userData = new UserData(UserC.getCurrentUser().getFirstName(),
//                                            UserC.getCurrentUser().getLastName(),
//                                            UserC.getCurrentUser().getEmail());
        clientAgent.sendAndWaitForResponse(request);
    }

    //method to request all questions for given quiz. For now the received arraylist is not passed back to the gui from client decoder.
    public static void fetchQuizQuestions(QuizData quiz) {
        ClientAgent clientAgent = ClientAgent.getClientAgent();
        request.task = "FETCH_ALL_QUESTIONS";
        request.quizData = quiz;
        clientAgent.sendAndWaitForResponse(request);
    }
}
