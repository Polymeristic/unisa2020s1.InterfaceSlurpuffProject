package application.Controllers;

import application.AppController;
import application.Main;
import application.SimpleDialog;
import com.sun.javafx.css.PseudoClassState;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class to control the login scene
 */
public class Login extends AppController {

    private Scene scene;

    public Login() { }

    /**
     * Runs when the scene is initialized, use this to do any on-load changes
     */
    @Override
    public Parent loadAction() {
        VBox box = new VBox();
        root = box;
        root.setId("root");

        Main.setDimensions(450, 400);

        VBox _inputBox = new VBox(12);

        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Login");
        ImageView logo = new ImageView("resources/images/mediscan_logo.png");

        // If using stylesheets, load before anything else
        loadStylesheet("resources/stylesheets/global.css");
        loadStylesheet("resources/stylesheets/login.css");

        box.setAlignment(Pos.TOP_CENTER);
        box.getChildren().add(_inputBox);
        box.setPadding(new Insets(60, 0, 0, 0));

        _inputBox.getChildren().addAll(logo, usernameField, passwordField, loginButton);
        _inputBox.setMaxWidth(300);

        logo.requestFocus();
        logo.setFitWidth(300);
        logo.setFitHeight(75);
        logo.setSmooth(true);
        logo.setId("logo");

        usernameField.setPromptText("Username");
        usernameField.setId("username");

        passwordField.setPromptText("Password");
        passwordField.setId("password");

        loginButton.setId("login");

        loginButton.setOnAction(ActionEvent -> {
            if (usernameField.getText().length() == 0 ||
                passwordField.getText().length() == 0) {
                SimpleDialog.Warning("Missing Credentials", "Please ensure all fields have been filled out.");
            } else {
                new Home().load();
            }
        });

        return root;
    }
}
