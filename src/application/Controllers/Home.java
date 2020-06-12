package application.Controllers;

import application.AppController;
import application.Main;
import application.SimpleDialog;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Home controller
 */
public class Home extends AppController {

    @Override
    protected Scene loadAction() {
        VBox box = new VBox();
        Button button = new Button("Report");
        Scene scene = new Scene(box, STANDARD_WIDTH, STANDARD_HEIGHT);

        box.getChildren().addAll(new Label("Home"), button);

        //Home Button to go back to home.
        EventHandler<ActionEvent> ReturnHome = new EventHandler<>() {
            @Override
            public void handle(ActionEvent e) {
                new GenerateReport().load();
            }
        };

        button.setOnAction(ReturnHome);

        return scene;
    }
}
