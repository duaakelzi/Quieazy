// Decodes the message received from the client and forwards it to the concerned class for execution.

package application;

import data.Message;
import data.RegisterData;
import data.LoginData;
import persistence.Database;

public class ServerDecoder {
	
	public static Message decode(Message message) {
		
		if(message.task.equals("LOG_IN")) {
			
			LoginData data = message.loginData;
			
			return Database.login(data.email, data.password);
			
		}else if(message.task.equals("REGISTER")){
			
			RegisterData data = message.registerData;
			
			return Database.register(data.firstName, data.lastName, data.email, data.password);
			
		}
		
		return null;
		
	}

}
