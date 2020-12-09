// Decodes the message received from the client and forwards it to the concerned class for execution.

package applicationServer;

import dataServer.*;
import persistence.Request;

public class ServerDecoder {

    public static Message decode(Message message) {

        if (message.task.equals("LOG_IN")) {

            LoginData data = message.loginData;

            return Request.login(data.email, data.password);

        } else if (message.task.equals("REGISTER")) {

            RegisterData data = message.registerData;

            return Request.register(data.firstName, data.lastName, data.email, data.password);

        } else if (message.task.equals("CREATE_QUIZ")) {
            QuizData quiz = message.quizData;
            UserData data = message.userData;
            return Request.createQuiz(quiz.getQuizName(), quiz.getThreshold(), quiz.isPublic(), data.email, quiz.getCourse()); //should i return quiz data immediately?
        } else if (message.task.equals("UPDATE_QUIZ")) {
            QuizData quiz = message.quizData;

            return Request.updateQuiz(quiz.getId(), quiz.getQuizName(), quiz.getThreshold(), quiz.isPublic(), quiz.getCourse());
        } else if (message.task.equals("DELETE_QUIZ")) {
            QuizData quiz = message.quizData;

            return Request.deleteQuiz(quiz.getId(), quiz.getCourse());
        }
        //should i return quiz data immediately?
        else if (message.task.equals("FETCH_ALL_QUIZZES")) {
            UserData data = message.userData;
            return Request.retrieveQuizzes(data.email);
        } else if (message.task.equals("CREATE_QUESTION")) {
            QuestionData question = message.questionData;
            return Request.createQuestion(question.getQuestionText(), question.getPoints(), question.getQuestionChoices(), question.getUser().getEmail());
        } else if (message.task.equals("UPDATE_QUESTION")) {
            QuestionData question = message.questionData;
            return Request.updateQuestion(question.getId(), question.getQuestionChoices(), question.getQuestionText(), question.getPoints(), question.getCorrect());
        } else if (message.task.equals("DELETE_QUESTION")) {
            QuestionData question = message.questionData;
            return Request.deleteQuestion(question.getId());
        } else if (message.task.equals("FETCH_ALL_QUESTIONS")) {
            QuizData quiz = message.quizData;
            return Request.retrieveQuestions(quiz.getId()); //all questions belonging to that quiz
        }//fetch results for user, save result
        return null;
    }
}
