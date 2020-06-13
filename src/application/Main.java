package application;

import application.Controllers.*;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
    public static Stage _mainStage;

    /**
     * Create the primary stage and set the initial scene to the login page
     * @param primaryStage Primary stage
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Set the main stage for use in other classes
        _mainStage = primaryStage;
        primaryStage.setTitle("MediScan");

        // Load the login page
        new Login().load();
    }

    /**
     * Sets the dimensions of the main window
     * @param width Width
     * @param height Height
     */
    public static void setDimensions(int width, int height) {
        width += 10;

        _mainStage.setWidth(width);
        _mainStage.setMaxWidth(width);
        _mainStage.setMinWidth(width);

        _mainStage.setHeight(height);
        _mainStage.setMaxHeight(height);
        _mainStage.setMinHeight(height);
    }

    /**
     * Entry point for the application
     * @param args Init arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Gets the main stage of the application
     * @return the main stagee
     */
    public static Stage get_MainStage() {
        return _mainStage;
    }
}
