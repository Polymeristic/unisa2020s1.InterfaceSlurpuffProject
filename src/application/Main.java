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

        // Load the login page
//        new Login().load();
        new Report().load();
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
