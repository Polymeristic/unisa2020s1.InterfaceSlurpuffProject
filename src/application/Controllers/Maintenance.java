package application.Controllers;

import application.AppController;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

import java.awt.event.ActionEvent;
import java.beans.EventHandler;

public class Maintenance extends AppController {
    @Override
    public Parent loadAction() {
        ScrollPane pane = new ScrollPane();
        pane.setMaxHeight(700);
        pane.setPrefWidth(850);

        VBox root = new VBox(8);

        pane.setContent(root);

        root.setPrefWidth(830);
        root.setMaxHeight(700);

        VBox buildingA = createScannerPanel("Building A");
        VBox buildingB = createScannerPanel("Building B");
        VBox buildingC = createScannerPanel("Building C");
        VBox buildingD = createScannerPanel("Building D");

        root.getChildren().addAll(buildingA, buildingB, buildingC, buildingD);

        ((FlowPane) buildingA.getChildren().get(1)).getChildren().addAll(
                createMachineBox("MRI [SC-001]", true),
                createMachineBox("XRay [SC-005]", true),
                createMachineBox("XRay [SC-021]", false),
                createMachineBox("CAT [SC-004]", false),
                createMachineBox("CAT [SC-009]", false),
                createMachineBox("Ultra [SC-012]", false)
        );

        ((FlowPane) buildingB.getChildren().get(1)).getChildren().addAll(
                createMachineBox("CAT [SC-020]", true),
                createMachineBox("CAT [SC-002]", false),
                createMachineBox("Ultra [SC-025]", false)
        );

        ((FlowPane) buildingC.getChildren().get(1)).getChildren().addAll(
                createMachineBox("XRay [SC-020]", false),
                createMachineBox("CAT [SC-002]", false),
                createMachineBox("Ultra [SC-025]", false)
        );

        ((FlowPane) buildingD.getChildren().get(1)).getChildren().addAll(
                createMachineBox("CAT [SC-020]", false),
                createMachineBox("CAT [SC-002]", false),
                createMachineBox("Ultra [SC-025]", false)
        );

        return pane;
    }

    private VBox createMachineBox(String name, boolean maintenance) {
        VBox view = new VBox();
        view.setId("machine");
        ImageView image = new ImageView("resources/images/" + (maintenance ? "scanner_repair.png" : "scanner.png"));
        Label title = new Label(name);

        image.prefWidth(50);
        image.prefHeight(50);
        image.resize(50, 50);

        view.getChildren().addAll(image, title);

        return view;
    }

    private VBox createScannerPanel(String name) {
        VBox p = new VBox();
        HBox header = new HBox();
        FlowPane flow = new FlowPane(15, 15);
        flow.setId("machine-flow");

        Label title = new Label(name);
        title.setId("header-title");
        title.setTextAlignment(TextAlignment.CENTER);

        header.setOnMouseClicked(EventHandler -> toggleVisible(p));
        header.setId("header");

        header.getChildren().addAll(title);

        p.getChildren().addAll(header, flow);

        return p;
    }

    /**
     * Toggles the visibility of an element
     * @param tab The element
     */
    private void toggleVisible(Parent tab) {
        Parent p = (Parent) tab.getChildrenUnmodifiable().get(1);
        boolean b = !p.isVisible();

        p.setVisible(b);
        p.setManaged(b);
    }
}
