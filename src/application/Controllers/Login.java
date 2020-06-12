package application.Controllers;

import application.AppController;
import com.sun.javafx.css.PseudoClassState;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Login extends AppController {
    private Label timeLabel;
    private Button loginButton;
    private Scene scene;

    public Login() { }

    /**
     * Runs when the scene is initialized, use this to do any on-load changes
     */
    @Override
    public Scene loadAction() {
        VBox box = new VBox();
        root = box;

        // If using stylesheets, load before anything else
        loadStylesheet("resources/stylesheets/global.css");
        loadStylesheet("resources/stylesheets/login.css");

        scene = new Scene(root, STANDARD_WIDTH, STANDARD_HEIGHT);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        loginButton = new Button("Login");
        timeLabel = new Label("{{ time }}");

        loginButton.setOnAction(this::loginAction);

        box.getChildren().addAll(timeLabel, loginButton);

        timeLabel.setText(dtf.format(now));

        return scene;
    }

    /**
     * Action when the login button is clicked
     * @param actionEvent event instance
     */
    private void loginAction(ActionEvent actionEvent) {
        System.out.println("Attempting to load");
        new Home().load();
    }
}
