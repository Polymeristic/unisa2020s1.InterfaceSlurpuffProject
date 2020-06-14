package application.Controllers;

import application.AppController;
import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class MedicalRecordClerk extends AppController {

    @Override
	public Parent loadAction(){
        Rectangle rectangle = new Rectangle();
        rectangle.setArcHeight(4);
        rectangle.setArcWidth(4);
        rectangle.setStroke(Color.rgb(204,204,204));
        rectangle.setStrokeWidth(1);
        rectangle.setWidth(400);
        rectangle.setHeight(28);
        LinearGradient linearGradient = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, new Stop[] {
                new Stop(0, Color.rgb(242, 242, 242)),
                new Stop(1, Color.rgb(229, 229, 229))
        });
        rectangle.setFill(linearGradient);
        rectangle.setTranslateX(0);
        rectangle.setTranslateY(0);
 
        String fontFamily = "Arial";
        int fontSize = 12;
        javafx.scene.text.Font javafxFont = new javafx.scene.text.Font(fontFamily, fontSize);
 
        String labelText = "Search";
        Label label = new Label(labelText);
        label.setFont(javafxFont);
 
        TextField queryTextField = new TextField();
        queryTextField.setMaxWidth(400);
        queryTextField.setPromptText("Search");
 
        Button button = new Button("Search");
 
        FlowPane flowPane = new FlowPane();
        flowPane.setHgap(5);
        flowPane.setOrientation(Orientation.HORIZONTAL);
        flowPane.setAlignment(Pos.CENTER);
        flowPane.getChildren().addAll(label, queryTextField, button);
 
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(rectangle, flowPane);
 
        return stackPane;
		
	}
	
	
}
