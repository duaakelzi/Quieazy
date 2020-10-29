// Decodes the message received from the client and forwards it to the concerned class for execution.

package application;

import persistence.Database;

public class ServerDecoder {
	
	public static Message decode(Message msg) {
		
		if(msg.arg0.equals("LOG_IN")) {
			
			// NOTE: This task will be assigned to another context class. This is just for now.
			
			if(Database.login(msg.arg1, msg.arg2)) {
				
				return new Message("LOGIN_OK", msg.arg1, "");
				
			}else {
				
				return new Message("LOGIN_FAIL", "", "");
				
			}
			
		}
		
		return null;
		
	}

}
