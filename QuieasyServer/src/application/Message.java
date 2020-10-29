// Class for creating message objects to send and receive over network.
// The message content is stored as variables of the class: arg0, arg1, arg2.

package application;

import java.io.Serializable;

public class Message implements Serializable {
	
	// variables to store data
	public String arg0, arg1, arg2;
	
	public Message (String arg0, String arg1, String arg2) {
		
		this.arg0 = arg0;
		this.arg1 = arg1;
		this.arg2 = arg2;
		
	}

}
