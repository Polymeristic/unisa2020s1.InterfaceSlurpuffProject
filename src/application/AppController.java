package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public abstract class AppController {
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
        Main.get_MainStage().setScene(loadAction());
        Main.get_MainStage().show();
    }

    /**
     * Action that runs when AppController.load() is run
     * @return A scene object
     */
    protected abstract Scene loadAction();
}
