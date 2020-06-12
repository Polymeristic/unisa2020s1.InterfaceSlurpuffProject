package application.Controllers;

import application.AppController;
import application.Main;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SelectLanguage extends AppController {
    @Override
    public Parent loadAction() {

        VBox root=new VBox();
        root.setAlignment(Pos.TOP_LEFT);
        root.setSpacing(10);
        root.setPadding(new Insets(20));

        Button engButton=new Button("English");
        engButton.setOnAction(event -> showPic("resources/images/eng.jpg", Main.get_MainStage()));
        Button chiButton=new Button("Chinese");
        chiButton.setOnAction(event -> showPic("resources/images/chi.jpg", Main.get_MainStage()));
        Button gerButton=new Button("German");
        gerButton.setOnAction(event -> showPic("resources/images/ger.jpg", Main.get_MainStage()));

        root.getChildren().addAll(engButton, chiButton, gerButton);

        return root;
    }

    private void showPic(String picName,Stage owner){
        Stage picStage=new Stage();
        picStage.setTitle(picName);
        picStage.initModality(Modality.WINDOW_MODAL);

        Group root=new Group();
        ImageView imageView=new ImageView(picName);
        imageView.setFitWidth(300);
        imageView.setFitHeight(400);

        root.getChildren().add(imageView);
        Scene scene=new Scene(root);

        picStage.setX(owner.getX()+owner.getWidth()+10);
        picStage.setY(owner.getY());
        picStage.setScene(scene);
        picStage.initOwner(owner);
        picStage.show();
    }
}
