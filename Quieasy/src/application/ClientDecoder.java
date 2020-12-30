// Decodes the message received from the server and forwards it to the concerned class for execution.

package application;

import gui.*;
import requests.UserC;
import data.*;

import java.util.ArrayList;

public class ClientDecoder {

	// Decode and forward message to the appropriate class for execution.
	//this may need to return Message
	public static void decode(Message message) {

		if (message.task.equals("LOGIN_OK")) { // on successful login or account creation

			UserData userData = message.userData;
			UserC.getUser(userData.firstName, userData.lastName, userData.email);

			// clear user inputs
			Login.clear();
			Register.clear();

			// show dash board
			PrimeScene.home();

		} else if (message.task.equals("LOGIN_FAILED")) { // user entered incorrect email or password

			Login.fail();

		} else if (message.task.equals("EMAIL_IN_USE")) { // user tried to register with an email that is already in use

			Register.emailInUse();

		} else if (message.task.equals("CREATE_QUIZ_SUCCESSFUL")) {
			CreateQuizBox.getCreateQuizBox().showSuccessful();
		} else if (message.task.equals("CREATE_QUIZ_FAILED")) {
			CreateQuizBox.getCreateQuizBox().showFailed();
		} else if (message.task.equals("CREATE_QUESTIONS_SUCCESSFUL")) {
			System.out.println("Questions created and saved"); //depends on Ion's needs
		} else if (message.task.equals("CREATE_QUESTIONS_FAILED")) {
			System.out.println("Questions not persisted"); //depends on Ion's needs
		} else if (message.task.equals("SAVE_QUESTION_EDITS_SUCCESSFUL")) {
			System.out.println("Questions updated"); //depends on Ion's needs
		} else if (message.task.equals("SAVE_QUESTION_EDITS_FAILED")) {
			System.out.println("Questions not updated"); //depends on Ion's needs
		} else if (message.task.equals("ADD_OLD_QUESTIONS_SUCCESSFUL")) {
			System.out.println("Questions updated"); //depends on Ion's needs
		} else if (message.task.equals("ADD_OLD_QUESTIONS_FAILED")) {
			System.out.println("Questions not updated"); //depends on Ion's needs
		} else if (message.task.equals("QUESTIONS_FETCH_OK")) {
			System.out.println("Questions fetched");
			ArrayList<QuestionData> questionData = message.questionData;
			//add here methods that should be called when quiz questions received
		}
	}
}