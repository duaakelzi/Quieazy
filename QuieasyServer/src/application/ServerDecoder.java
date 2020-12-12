// Decodes the message received from the client and forwards it to the concerned class for execution.

package application;

import data.LoginData;
import data.Message;
import data.QuestionData;
import data.QuizData;
import persistence.Request;

import java.util.ArrayList;

public class ServerDecoder {

    public static Message decode(Message message) {

        if (message.task.equals("LOG_IN")) {

            LoginData data = message.loginData;

            return Request.login(data.email, data.password);

        } else if (message.task.equals("REGISTER")) {

            data.RegisterData data = message.registerData;

            return Request.register(data.firstName, data.lastName, data.email, data.password);

        } else if (message.task.equals("CREATE_QUIZ")) {
            data.QuizData quiz = message.quizlist;
            data.UserData data = message.userData;
            System.out.println(message.quizlist.getName());
            return Request.createQuiz(quiz.getName(), quiz.getThreshold(), false, "user@mail.com", quiz.getCourse(),quiz.getTimer()); //should i return quiz data immediately?
        } else if (message.task.equals("UPDATE_QUIZ")) {
            data.QuizData quiz = message.quizlist;

            return Request.updateQuiz( quiz.getName(), quiz.getThreshold(), false, quiz.getCourse());
        } else if (message.task.equals("DELETE_QUIZ")) {
            QuizData quiz = message.quizlist;

            return Request.deleteQuiz(quiz.getId(), quiz.getCourse());
        }
        //should i return quiz data immediately?
//        else if (message.task.equals("FETCH_ALL_QUIZZES")) {
//            UserData data = message.userData;
//            return Request.retrieveQuizzes(data.email);
//        } else if (message.task.equals("CREATE_QUESTION")) {
//            QuestionData question = message.questionData;
//            return Request.createQuestion(question.getQuestionText(), question.getPoints(), question.getQuestionChoices(), question.getUser().getEmail());
      else if (message.task.equals("CREATE_QUESTION")) {
            data.QuizData quiz = message.quizlist;
           ArrayList< data.QuestionData> questionData = message.questionData;
            //System.out.println(message.task);
            System.out.println(quiz.getName());
            System.out.println(questionData.size());

            for (int i=0;i<questionData.size();i++){
                System.out.println(questionData.get(i).getQuestionText());
            return Request.createQuestion(questionData.get(i).getQuestionText(), 5,questionData.get(i).getQuestionChoices(),quiz.getName(),"user@mail.com");}}
//        } else if (message.task.equals("DELETE_QUESTION")) {
//            QuestionData question = message.questionData;
//            return Request.deleteQuestion(question.getId());
//        } else if (message.task.equals("FETCH_ALL_QUESTIONS")) {
//            QuizData quiz = message.quizlist;
//            return Request.retrieveQuestions(quiz.getId()); //all questions belonging to that quiz
//        }//fetch results for user, save result
        return null;
    }
}
