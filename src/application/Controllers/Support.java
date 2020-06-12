package application.Controllers;

import application.AppController;
import application.SimpleDialog;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.awt.*;


public class Support extends AppController {
    @Override
    public Parent loadAction() {
        //Set initial GridPane and add padding and gap.
        GridPane root = new GridPane();
        root.setPadding(new Insets(10));
        root.setHgap(25);
        root.setVgap(15);

        //Add Red Asterisk to signify a required field.
        Text requiredAsterisk = new Text("*");
        requiredAsterisk.setFill(Color.RED);
        Text subjectAsterisk = new Text("*");
        subjectAsterisk.setFill(Color.RED);
        Text emailAsterisk = new Text("*");
        emailAsterisk.setFill(Color.RED);
        Text descriptionAsterisk = new Text("*");
        descriptionAsterisk.setFill(Color.RED);


        //Label title, set to bigger and with Arial font.
        Label supportTitle = new Label("Submit a ticket");
        supportTitle.setFont(new Font("Arial", 24));
        root.add(supportTitle, 1, 1);

        //Label title, set to bigger and with Arial font.
        Text requiredTitle = new Text(" = Details required to submit");
        TextFlow requiredTextFlow = new TextFlow();
        requiredTextFlow.getChildren().addAll(requiredAsterisk, requiredTitle);
        root.add(requiredTextFlow, 1, 2);

        //Subject Section
        Text subjectTitle = new Text("Subject:");
        TextFlow subjectTextFlow = new TextFlow();
        subjectTextFlow.getChildren().addAll(subjectTitle, subjectAsterisk);
        root.add(subjectTextFlow, 1, 3);
        TextField subjectTextField = new TextField();
        subjectTextField.setPrefSize(400, 40);
        root.add(subjectTextField, 1, 4);

        //ChoiceBox Section
        Text priorityTitle = new Text("Priority:");
        root.add(priorityTitle, 1, 5);
        ChoiceBox priorityChoiceBox = new ChoiceBox(FXCollections.observableArrayList(
                "Low", "Medium", "High"));
        priorityChoiceBox.setValue("Low");
        root.add(priorityChoiceBox, 1, 6);

        //Email Section
        Text emailTitle = new Text("Email:");
        TextFlow emailTextFlow = new TextFlow();
        emailTextFlow.getChildren().addAll(emailTitle, emailAsterisk);
        root.add(emailTextFlow, 1, 7);
        TextField emailTextField = new TextField();
        emailTextField.setPrefSize(400, 40);
        root.add(emailTextField, 1, 8);

        //App Version Section
        Text appVersionTitle = new Text("App Version:");
        root.add(appVersionTitle, 1, 9);
        TextField appVersionTextField = new TextField();
        appVersionTextField.setPrefSize(400, 40);
        root.add(appVersionTextField, 1, 10);

        //Description Section
        Text descriptionTitle = new Text("Description:");
        TextFlow descriptionTextFlow = new TextFlow();
        descriptionTextFlow.getChildren().addAll(descriptionTitle, descriptionAsterisk);
        root.add(descriptionTextFlow, 1, 11);
        TextArea descriptionTextArea = new TextArea();
        descriptionTextArea.wrapTextProperty().setValue(true);
        root.add(descriptionTextArea, 1, 12);

        //SubmitButton for after ticket is complete.
        HBox submitTicketHBox = new HBox();
        Button submitButton = new Button("Submit");
        submitTicketHBox.getChildren().add(submitButton);
        HBox.setHgrow(submitButton, Priority.ALWAYS);
        root.add(submitButton, 1, 14);


        //Home Button to go back to home.
        EventHandler<ActionEvent> ReturnHomeSupport = new EventHandler<>() {
            @Override
            public void handle(ActionEvent e) {
                new Home().load();
            }
        };

        //SubmitButton used with if-else loop to ensure that user has filled in all requires TextFields/TextArea.
        EventHandler<ActionEvent> SubmitButtonCheck = new EventHandler<>() {
            @Override
            public void handle(ActionEvent e) {
                if (subjectTextField.getText().isEmpty() || emailTextField.getText().isEmpty() ||
                        descriptionTextArea.getText().isEmpty()){

                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Error Submitting");
                    errorAlert.setHeaderText("Required Fields Empty");
                    errorAlert.setContentText("The fields with a red asterisk must be filled "
                            + "out.\nPlease try again.");
                    errorAlert.showAndWait();

                } else {

                    ImageView successImage = new ImageView("resources/images/greentick.png");

                    Alert successAlert = new Alert(Alert.AlertType.WARNING);
                    successAlert.setTitle("Confirmation");
                    successAlert.setHeaderText("Ticket Successfully Submitted");
                    successAlert.setGraphic(successImage);
                    successAlert.setContentText("Thank you for supporting your ticket, we will try" + "\n"
                            + "to get back to you ASAP.");
                    subjectTextField.clear();
                    priorityChoiceBox.setValue("Low");
                    emailTextField.clear();
                    appVersionTextField.clear();
                    descriptionTextArea.clear();
                    successAlert.showAndWait();

                }
            }
        };

        submitButton.setOnAction(SubmitButtonCheck);

        //return Scene to work with app controller.
        return root;
    }
}
