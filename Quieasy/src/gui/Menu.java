// Menu with a list of options

package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import requests.UserRequests;

public class Menu extends VBox {

    private static final double BTN_WIDTH = 100;
    private static final double BTN_HEIGHT = 20;
    private static Menu menu;
    private static final Button profileButton = new Button("Profile");
    private static final Button createQuizButton = new Button("Create Quiz");
    private static final Button notifButton = new Button("Notifications");
    private static final Button settingsButton = new Button("Settings");
    private static final Button helpButton = new Button("Help");
    private static final Button logoutButton = new Button("Log out");

    // constructor can only be accessed from within
    private Menu() {
        super(8);
        this.setPadding(new Insets(15, 12, 15, 12));
        profileButton.setPrefSize(BTN_WIDTH, BTN_HEIGHT);
        createQuizButton.setPrefSize(BTN_WIDTH, BTN_HEIGHT);
        notifButton.setPrefSize(BTN_WIDTH, BTN_HEIGHT);
        settingsButton.setPrefSize(BTN_WIDTH, BTN_HEIGHT);
        helpButton.setPrefSize(BTN_WIDTH, BTN_HEIGHT);
        logoutButton.setPrefSize(BTN_WIDTH, BTN_HEIGHT);

        this.getChildren().addAll(profileButton, createQuizButton, notifButton, settingsButton, helpButton, logoutButton);

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

    // Gets the current instance -> Singleton
    public static Menu getMenu() {

        if (menu == null) menu = new Menu();
        return menu;

    }

}
