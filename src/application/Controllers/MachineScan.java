package application.Controllers;

import application.AppController;
import application.Main;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import static java.lang.Math.random;

public class MachineScan extends AppController {
    @Override
    protected Scene loadAction() {
        VBox root = new VBox();
        Scene scene = new Scene(root);

        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);
        root.setPadding(new Insets(20));

        Button preButton=new Button("Prepare");
        preButton.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent event) {
                preScan();
            }
        });

        root.getChildren().add(preButton);
        Main.get_MainStage().setTitle("Machine Scan");

        return scene;
    }

    private void preScan() {
        Stage preStage=new Stage();
        preStage.setTitle("Prepare Scan");
        preStage.initModality(Modality.WINDOW_MODAL);

        VBox root=new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);
        root.setPadding(new Insets(10));

        ImageView imageView=new ImageView("resources/images/scan_prepare.jpg");
        imageView.setFitWidth(400);
        imageView.setFitHeight(300);
        root.getChildren().add(imageView);

        Button startButton=new Button("Start");
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                scan(preStage);
            }
        });

        root.getChildren().add(startButton);

        Scene scene=new Scene(root);
        Stage owner = Main.get_MainStage();

        preStage.setX(owner.getX()+owner.getWidth()+10);
        preStage.setY(owner.getY());
        preStage.setScene(scene);
        preStage.initOwner(owner);
        preStage.show();
    }

    private void scan(Stage owner) {
        Stage scanStage=new Stage();
        scanStage.setTitle("Scan Scan");
        scanStage.initModality(Modality.WINDOW_MODAL);

        VBox root=new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);
        root.setPadding(new Insets(10));



        StackPane sp=new StackPane();
        sp.setBackground(new Background(new BackgroundFill(Color.GREEN,null,null)));
        root.getChildren().add(sp);

        ImageView imageView=new ImageView("resources/images/scan_start.jpg");
        imageView.setFitWidth(200);
        imageView.setFitHeight(300);
        imageView.setVisible(false);
        sp.getChildren().add(imageView);

        Pane colorBack= getColorfulCircles(200,300,imageView);
        sp.getChildren().add(colorBack);

        Button finishButton=new Button("Finish");
        finishButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                scanStage.close();

            }
        });

        root.getChildren().add(finishButton);

        Scene scene=new Scene(root);
        scanStage.setX(owner.getX()+owner.getWidth()+10);
        scanStage.setY(owner.getY());
        scanStage.setScene(scene);
        scanStage.initOwner(owner);
        scanStage.show();
    }

    private Pane getColorfulCircles(double w, double h, ImageView imageView){
        //Prepare scene
        Pane root = new Pane();
        root.setPrefWidth(w);
        root.setPrefHeight(h);

        //Set up circles
        Group circles = new Group();
        for (int i = 0; i < 30; i++) {
            //Radius and Colour
            Circle circle = new Circle(150, Color.web("white", 0.05));
            //Circle circle = new Circle(random() * 800, random() * 600, 150);
            circle.setStrokeType(StrokeType.OUTSIDE);
            circle.setStroke(Color.web("white", 0.16));
            circle.setStrokeWidth(4);
            circles.getChildren().add(circle);
        }

        root.getChildren().add(circles);


        Rectangle colors = new Rectangle(w, h,
                //Start X,Y, End X,Y, proportional to the shape, cycle(reflect, repeat, none), how to distribute color
                new LinearGradient(0f, 1f, 1f, 0f, true, CycleMethod.NO_CYCLE, new Stop[] {
                        new Stop(0, Color.web("#f8bdcc")),
                        new Stop(0.14, Color.web("#ccff66")),
                        new Stop(0.28, Color.web("#5dfbc1")),
                        new Stop(0.43, Color.web("#ff66cc")),
                        new Stop(0.57, Color.web("#ff9966")),
                        new Stop(0.71, Color.web("#ed5fc2")),
                        new Stop(0.85, Color.web("#66ccff")),
                        new Stop(1, Color.web("#f2660f"))
                }));
        colors.setWidth(w);
        colors.setHeight(h);

        //Blend the colours with the circles
        Group blendModeGroup =
                new Group(new Group(new Rectangle(w, h,
                        Color.BLACK), circles), colors);
        colors.setBlendMode(BlendMode.OVERLAY);

        root.getChildren().add(blendModeGroup);

        //Add blur
        circles.setEffect(new BoxBlur(10, 10, 3));

        root.setClip(new Rectangle(0,0,w,h));

        //Animate for 5 seconds, move circle around randomly.
        Timeline timeline = new Timeline();
        for (Node circle : circles.getChildren()) {
            timeline.getKeyFrames().addAll(
                    new KeyFrame(Duration.ZERO, // set start position at 0
                            new KeyValue(circle.translateXProperty(), random() * w),
                            new KeyValue(circle.translateYProperty(), random() * h)),
                    new KeyFrame(new Duration(5000), // set end position at 5
                            new KeyValue(circle.translateXProperty(), random() * w),
                            new KeyValue(circle.translateYProperty(), random() * h)));
        }
        // play 5s of animation
        timeline.play();

        timeline.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                imageView.setVisible(true);
                root.setVisible(false);
            }
        });


        return root;
    }
}
