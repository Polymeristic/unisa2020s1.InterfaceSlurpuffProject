package application.Controllers;

import application.AppController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;


public class Support extends AppController {
    @Override
    protected Scene loadAction() {



        GridPane root = new GridPane();
        root.setPadding(new Insets(10));
        root.setHgap(25);
        root.setVgap(15);
        Text asterisk = new Text("*");
        asterisk.setFill(Color.RED);
        Text asterisk2 = new Text("*");
        asterisk2.setFill(Color.RED);


        //Label title, set to bigger and with Arial font.
        Label supportTitle = new Label("Submit a request");
        supportTitle.setFont(new Font("Arial", 24));
        root.add(supportTitle, 1, 1);

        //Subject Section
        Text subjectTitle = new Text("Subject:");
        TextFlow subjectTextFlow = new TextFlow();
        subjectTextFlow.getChildren().addAll(subjectTitle, asterisk);
        root.add(subjectTextFlow, 1, 3);
        TextField subjectTextField = new TextField();
        subjectTextField.setPrefSize(400, 40);
        root.add(subjectTextField, 1, 4);

        //Description Section
        Text descriptionTitle = new Text("Description:");
        TextFlow descriptionTextFlow = new TextFlow();
        descriptionTextFlow.getChildren().addAll(descriptionTitle, asterisk2);
        root.add(descriptionTextFlow, 1, 5);
        TextArea descriptionTextField = new TextArea();
        descriptionTextField.wrapTextProperty().setValue(true);
        root.add(descriptionTextField, 1, 6);









        //Set up scene to have a Gridpane root
        Scene scene = new Scene(root, 600, 600);




        return scene;
    }



















}
