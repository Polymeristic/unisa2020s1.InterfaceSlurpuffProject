package application.Controllers;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SelectLanguage extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {

        VBox root=new VBox();
        root.setAlignment(Pos.TOP_LEFT);
        root.setSpacing(10);
        root.setPadding(new Insets(20));

        Button engButton=new Button("English");
        engButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showPic("eng.jpg",primaryStage);
            }
        });
        Button chiButton=new Button("Chinese");
        chiButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showPic("chi.jpg",primaryStage);
            }
        });
        Button gerButton=new Button("German");
        gerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showPic("ger.jpg",primaryStage);
            }
        });

        Button backButton=new Button("Back");
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
            }
        });
        root.getChildren().addAll(engButton,chiButton,gerButton,backButton);

        Scene scene=new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setWidth(200);
        primaryStage.setHeight(300);
        primaryStage.setTitle("Select Language");
        primaryStage.show();
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

    public static void main(String[] args) {
        launch(args);
    }
}
