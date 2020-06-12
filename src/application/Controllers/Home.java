package application.Controllers;

import application.AppController;
import application.Main;
import application.SimpleDialog;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import java.awt.event.ActionEvent;
import java.beans.EventHandler;
import java.io.IOException;

/**
 * Home controller
 */
public class Home extends AppController {

    private Scene scene;
    private StackPane view;

    @Override
    public Parent loadAction() {
        HBox box = new HBox(16);
        root = box;

        VBox sidebar = new VBox(16);
        view = new StackPane();
        ImageView logo = new ImageView("resources/images/mediscan_logo.png");
        ListView<String> sidebarList = new ListView<>();

        root.setId("root");
        view.setId("view");
        sidebar.setId("sidebar");
        sidebarList.setId("sidebar-list");

        Main.setDimensions(STANDARD_WIDTH, STANDARD_HEIGHT);

        loadStylesheet("resources/stylesheets/global.css");
        loadStylesheet("resources/stylesheets/home.css");
        loadStylesheet("resources/stylesheets/maintenance.css");

        box.getChildren().addAll(sidebar, view);
        sidebar.getChildren().addAll(logo, sidebarList);
        sidebar.setPrefWidth(50);

        sidebarList.getItems().addAll("Med. Report", "Scan", "Reports", "Security", "Maintenance", "Support", "Language");
        sidebarList.getSelectionModel().selectFirst();

        sidebarList.setOnMouseClicked(EventHandler -> {
            switch (sidebarList.getSelectionModel().getSelectedItem()) {
                case "Med. Report": setScene(new Report()); break;
                case "Scan": setScene(new MachineScan()); break;
                case "Reports": setScene(new GenerateReport()); break;
                case "Security": setScene(new Security()); break;
                case "Maintenance": setScene(new Maintenance()); break;
                case "Support": setScene(new Support()); break;
                case "Language": setScene(new SelectLanguage()); break;
            }
        });

        setScene(new Report());

        return root;
    }

    private void setScene(AppController controller) {
        Parent root = controller.loadAction();

        if (view.getChildren().size() == 0)
            view.getChildren().add(root);
        else
            view.getChildren().set(0, root);
    }
}
