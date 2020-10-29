// Decodes the message received from the server and forwards it to the concerned class for execution.

package application;

import dialog.Login;

public class ClientDecoder {
	
	// Decode and forward message to the appropriate class for execution.
	public static void decode(Message msg) {
		
		if(msg.arg0.equals("LOGIN_FAIL")) {
			
			Login.getLogin().fail();
			
		}else if(msg.arg0.equals("LOGIN_OK")) {
			
			PrimeScene.getPrimeScene().home();
			
		}
		
	}

}
