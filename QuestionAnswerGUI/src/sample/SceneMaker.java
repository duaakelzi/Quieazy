package sample;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public abstract class SceneMaker extends Scene {
    protected Controller controller;
    String sceneName;
    Label titleLabel;
    CheckBox checkBox;
    TextField inputField;



    public SceneMaker(Controller controller, double sizeX, double sizeY) {
        super(new AnchorPane(), sizeX, sizeY);
        this.controller = controller;

        initiatePasswordInput();
    }

    public void initiatePasswordInput() {

    }
}
