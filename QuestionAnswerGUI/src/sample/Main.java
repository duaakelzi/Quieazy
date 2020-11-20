package sample;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Controller controller = new Controller(primaryStage);
        primaryStage.setScene(controller.getQuestionAnswerScene());
        primaryStage.setTitle("QuieazyTHU");
        primaryStage.centerOnScreen();
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }


}
