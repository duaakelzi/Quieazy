package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import requests.UserRequests;

/**
 * Menu with a list of options.
 */
public class Menu extends VBox {

	private static Menu menu; // singleton

	private static final double BTN_WIDTH = 100; // width for all menu buttons
	private static final double BTN_HEIGHT = 20; // height for all menu buttons

	// Menu buttons
	private static Button profileButton = new Button("Profile");
	private static Button createQuizButton = new Button("Create Quiz");
	private static Button notifButton = new Button("Notifications");
	private static Button settingsButton = new Button("Settings");
	private static Button helpButton = new Button("Help");
	private static Button logoutButton = new Button("Log out");

	/**
	 * Private constructor.
	 */
	private Menu() {

		super(8);
		this.setPadding(new Insets(15, 12, 15, 12));
		profileButton.setPrefSize(BTN_WIDTH, BTN_HEIGHT);
		createQuizButton.setPrefSize(BTN_WIDTH, BTN_HEIGHT);
		notifButton.setPrefSize(BTN_WIDTH, BTN_HEIGHT);
		settingsButton.setPrefSize(BTN_WIDTH, BTN_HEIGHT);
		helpButton.setPrefSize(BTN_WIDTH, BTN_HEIGHT);
		logoutButton.setPrefSize(BTN_WIDTH, BTN_HEIGHT);

		this.getChildren().addAll(profileButton, createQuizButton, notifButton, settingsButton, helpButton,logoutButton);

		logoutButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent e) {

				UserRequests.logout();
				PrimeScene.login();
			}

		});

		createQuizButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent e) {

				MainPane.getMainPane().getTabs().add(CreateQuizTab.getCreateQuizTab());
				MainPane.getMainPane().getSelectionModel().select(CreateQuizTab.getCreateQuizTab());

			}

		});

	}

	/**
	 * Gets the single instance of this class.
	 * @return The single instance of this class.
	 */
	public static Menu getMenu() {

		if (menu == null) menu = new Menu();
		return menu;

	}

}
