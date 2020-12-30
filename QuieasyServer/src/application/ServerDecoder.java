// Decodes the message received from the client and forwards it to the concerned class for execution.

package application;

import data.*;
import persistence.Request;

import java.util.ArrayList;

public class ServerDecoder {

    //can we get rid of the if-else??
    public static Message decode(Message message) {

        if (message.task.equals("LOG_IN")) {

            LoginData data = message.loginData;

            return Request.login(data.email, data.password);

        } else if (message.task.equals("REGISTER")) {

            data.RegisterData data = message.registerData;

            return Request.register(data.firstName, data.lastName, data.email, data.password);

        } else if (message.task.equals("CREATE_QUIZ")) {
            data.QuizData quiz = message.quizData;
            data.UserData userData = message.userData;
            System.out.println(message.quizData.getName());
            return Request.createQuiz(quiz.getName(), quiz.getThreshold(), false, userData.getEmail(), quiz.getCourse(),quiz.getTimer()); //should i return quiz data immediately?
        } else if (message.task.equals("UPDATE_QUIZ")) {
            data.QuizData quiz = message.quizData;

            return Request.updateQuiz( quiz.getName(), quiz.getThreshold(), false, quiz.getCourse());
        } else if (message.task.equals("DELETE_QUIZ")) {
            QuizData quiz = message.quizData;
            return Request.deleteQuiz(quiz.getId(), quiz.getCourse());

        } else if (message.task.equals("FETCH_ALL_QUIZZES")) {
            UserData userData = message.userData;
            return Request.retrieveQuizzes(userData.getEmail());
        } else if (message.task.equals("CREATE_QUESTIONS")) {
            System.out.println("server: create questions method entered >> ");
            QuizData quiz = message.quizData;
            ArrayList<QuestionData> questionData = message.questionData;

            //go through array of new questions and make each persistent
            int i;
            for (i=0; i<questionData.size(); i++) {
                Request.createQuestion(questionData.get(i).getQuestion(), 5,questionData.get(i).getAnswers(),quiz.getName(),"user@mail.com");
            }
            if (i != questionData.size()) {
                message.task = "CREATE_QUESTIONS_FAILED";
            }else {
                message.task = "CREATE_QUESTIONS_SUCCESSFUL";
            }
            return message; //this message contains new task: "Create_question_successfull"
        } else if (message.task.equals("SAVE_QUESTION_EDITS")) {
            //to be added
        } else if (message.task.equals("ADD_OLD_QUESTIONS")) {
            //to be added
        } else if (message.task.equals("DELETE_QUESTION")) {
            QuizData quiz = message.quizData;
            ArrayList<QuestionData> questionData = message.questionData;
            int i;
            for (i=0; i<questionData.size(); i++) {
                Request.deleteQuestion((long)i);   //this is only to make the comp. happy. figure out if id's will be there
            }
            if (i != questionData.size()) {
                message.task = "DELETE_QUESTIONS_FAILED";
            }else {
                message.task = "DELETE_QUESTIONS_SUCCESSFUL";
            }
            return message;
        } else if (message.task.equals("FETCH_ALL_QUESTIONS")) {
            QuizData quiz = message.quizData;
            return Request.retrieveQuestions(quiz.getId()); //all questions belonging to that quiz
        }else if (message.task.equals("SAVE_RESULT")) {
            UserData userData = message.userData;
            QuizData quizData = message.quizData;
            ArrayList<ResultData> resultData = message.resultData;

            //go through array of results and make each persistent
            int i;
            for (i = 0; i < resultData.size(); i++) {
                //ideally, we should be working with IDs here
                //session closed after each call to persist. Might be interfering
                Request.createResult(resultData.get(i).getPoints(), resultData.get(i).isPassed(), quizData.getName(), userData.getEmail());
            }

            if (i != resultData.size()) {
                message.task = "CREATE_RESULT_FAILED";
            }else {
                message.task = "CREATE_RESULT_SUCCESSFUL";
            }
            return message;
        }else if (message.task.equals("FETCH_RESULTS")) {
            UserData userData = message.userData;
            return Request.retrieveResults(userData.getEmail());
        }else if (message.task.equals("UPDATE_RESULT")) {
            //to be added once repeat quiz is ready
        }
        return null;
    }
}
