package application.Controllers;

import application.AppController;
import application.Main;
import application.SimpleDialog;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 * Home controller
 */
public class Home extends AppController {

    Button report;

    @Override
    protected Scene loadAction() {
        VBox box = new VBox();
        Scene scene = new Scene(box, STANDARD_WIDTH, STANDARD_HEIGHT);

        report = new Button("report");

        report.setOnAction(ActionEvent -> {
            GenerateReport.LoadInstance();
        });

        box.getChildren().addAll(new Label("Home"), report);

        return scene;
    }
}
