package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.net.PortUnreachableException;

public abstract class AppController {

    public static int STANDARD_WIDTH = 800;
    public static int STANDARD_HEIGHT = 600;

    @FXML
    protected Parent root;

    /**
     * Loads a stylesheet into this scene
     * @param sheetPath Path of the stylesheet, relative to the project root
     */
    public void loadStylesheet(String sheetPath) {
        assert root != null;
        root.getStylesheets().add(sheetPath);
    }

    /**
     * Loads in a new scene and sets it to be the currently active scene
     */
    public void load() {
        Scene scene = loadAction();

        if (scene == null)
            throw new NullPointerException("A scene was not provided for the load action, scene is null.");

        Main.get_MainStage().setScene(scene);
        Main.get_MainStage().show();
    }

    /**
     * Action that runs when AppController.load() is run
     * @return A scene object
     */
    protected abstract Scene loadAction();
}
