// Decodes the message received from the client and forwards it to the concerned class for execution.

package application;

import data.*;
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
            data.QuizData quiz = message.quizData;
            data.UserData data = message.userData;
            System.out.println(message.quizData.getName());
            return Request.createQuiz(quiz.getName(), quiz.getThreshold(), false, "user@mail.com", quiz.getCourse(),quiz.getTimer()); //should i return quiz data immediately?
        } else if (message.task.equals("UPDATE_QUIZ")) {
            data.QuizData quiz = message.quizData;

            return Request.updateQuiz( quiz.getName(), quiz.getThreshold(), false, quiz.getCourse());
        } else if (message.task.equals("DELETE_QUIZ")) {
            QuizData quiz = message.quizData;

            return Request.deleteQuiz(quiz.getId(), quiz.getCourse());
        }
        //should i return quiz data immediately?
//        else if (message.task.equals("FETCH_ALL_QUIZZES")) {
//            UserData data = message.userData;
//            return Request.retrieveQuizzes(data.email);
//        } else if (message.task.equals("CREATE_QUESTION")) {
//            QuestionData question = message.questionData;
//            return Request.createQuestion(question.getQuestionText(), question.getPoints(), question.getQuestionChoices(), question.getUser().getEmail());
      else if (message.task.equals("CREATE_QUESTIONS")) {
            System.out.println("server: create questions method entered >> ");
            data.QuizData quiz = message.quizData;
            ArrayList<QuestionData> questionData = message.questionData;
            System.out.println("Message task" + message.task);
            System.out.println("Quiz name: " + quiz.getName());
            System.out.println("Question array size = " + questionData.size());

            //go through array of new questions and make each persistent
            int i;
            for (i=0; i<questionData.size(); i++) {
                Request.createQuestion(questionData.get(i).getQuestion(), 5,questionData.get(i).getAnswers(),quiz.getName(),"user@mail.com");
            }
            if (i == questionData.size()+1) {
                message.task = "CREATE_QUESTIONS_SUCCESSFUL";
            }
            return message; //this message contains new task: "Create_question_successfull"
      }
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
