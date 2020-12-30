package requests;
import application.ClientAgent;
import data.Message;
import data.QuestionData;
import data.QuizData;

import java.util.ArrayList;

public class QuestionC {
    // private UserData ownerQuiz;
    private static Message saveQuestionsMsg = new Message();

    //this method should: 1) persist new questions; 2) ask the message to add questions to quiz 3) maybe update quiz to add
    // new questions there on class level
    public static void persistNewQuestions(QuizData quiz, ArrayList<QuestionData> questionsToPersist) {
        ClientAgent clientAgent = ClientAgent.getClientAgent();

        saveQuestionsMsg.task = "CREATE_QUESTIONS";
        saveQuestionsMsg.quizData = quiz;
        saveQuestionsMsg.questionData = questionsToPersist;
//        saveQuiestionsMsg.userData = new UserData(UserC.getCurrentUser().getFirstName(),
//                                            UserC.getCurrentUser().getLastName(),
//                                            UserC.getCurrentUser().getEmail());
        clientAgent.send(saveQuestionsMsg);
    }

    //only text or choices were updated, not the relationships
    public static void updateEditedQuestions(ArrayList<QuestionData> questionsToUpdate){
        ClientAgent clientAgent = ClientAgent.getClientAgent();

        saveQuestionsMsg.task = "SAVE_QUESTION_EDITS";
        saveQuestionsMsg.questionData = questionsToUpdate;

        clientAgent.send(saveQuestionsMsg);
    }
    //update the relationships of the existing,old questions
    public static void addOldQuestions(QuizData quiz, ArrayList<QuestionData> questionsToAdd) {
        ClientAgent clientAgent = ClientAgent.getClientAgent();

        saveQuestionsMsg.task = "ADD_OLD_QUESTIONS";
        saveQuestionsMsg.quizData = quiz;
        saveQuestionsMsg.questionData = questionsToAdd;
//        saveQuiestionsMsg.userData = new UserData(UserC.getCurrentUser().getFirstName(),
//                                            UserC.getCurrentUser().getLastName(),
//                                            UserC.getCurrentUser().getEmail());
        clientAgent.send(saveQuestionsMsg);
    }
}
