// Decodes the message received from the client and forwards it to the concerned class for execution.

package applicationServer;

import dataServer.*;
import persistence.Request;

public class ServerDecoder {
	
	public static Message decode(Message message) {
		
		if(message.task.equals("LOG_IN")) {
			
			LoginData data = message.loginData;
			
			return Request.login(data.email, data.password);
			
		}else if(message.task.equals("REGISTER")){
			
			RegisterData data = message.registerData;
			
			return Request.register(data.firstName, data.lastName, data.email, data.password);
			
		} else if(message.task.equals("CREATE_QUIZ")) {
			QuizData quiz = message.quizData;
			UserData data = message.userData;
			return Request.createQuiz(quiz.getQuizName(), quiz.getThreshold(), quiz.isPublic(), data.email); //should i return quiz data immediately?
		} else if(message.task.equals("FETCH_ALL_QUIZZES")) {
			UserData data = message.userData;
			return Request.retrieveQuizzes(data.email);
		}
		
		return null;
		
	}

}
