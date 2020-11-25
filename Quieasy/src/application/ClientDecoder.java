// Decodes the message received from the server and forwards it to the concerned class for execution.

package application;

import gui.*;
import domain.UserC;
import data.*;

public class ClientDecoder {
	
	// Decode and forward message to the appropriate class for execution.
	public static void decode(Message message) {
		
		if(message.task.equals("LOGIN_OK")) { // on successful login or account creation
			
			UserData userData = message.userData;
			UserC.getUser(userData.firstName, userData.lastName, userData.email);
			
			// clear user inputs
			Login.clear();
			Register.clear();
			
			// show dash board
			PrimeScene.home();
			
		}else if(message.task.equals("LOGIN_FAILED")) { // user entered incorrect email or password
			
			Login.fail();
			
		}else if(message.task.equals("EMAIL_IN_USE")) { // user tried to register with an email that is already in use
			
			Register.emailInUse();
			
		}
		
	}

}
