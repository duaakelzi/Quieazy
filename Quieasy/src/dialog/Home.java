// GUI for home page of the app when logged in.

package dialog;

import javafx.scene.text.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.paint.Color;

public class Home extends GridPane {
	
	private static Home home;
	
	public final double WIDTH = 300;
	public final double HEIGHT = 275;
	
	private Home() {
		super();
		
		this.setAlignment(Pos.CENTER);
		this.setHgap(10);
		this.setVgap(10);
		this.setPadding(new Insets(25, 25, 25, 25));
		
		Text scenetitle = new Text("Hi Chernet!"); // This is just for now. User name should be set programmatically.
		
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		this.add(scenetitle, 0, 0, 2, 1);
		
		Button btn = new Button("Play Quiz");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.CENTER);
		hbBtn.getChildren().add(btn);
		this.add(hbBtn, 1, 4);
		
		Text msg = new Text("You are logged in.");
		msg.setFill(Color.GREEN);
        this.add(msg, 1, 6);
		
	}
	
	public static Home getHome() {
		
		if (home == null) home = new Home();
		
		return home;
		
	}
	
}
