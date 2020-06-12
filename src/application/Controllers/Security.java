package application.Controllers;
import java.util.Arrays;
import java.util.List;


import application.AppController;
import application.Employee;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.*;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Security extends AppController {
	
	@Override
	public Parent loadAction() {
		VBox root = new VBox();

		//Set Layout
		MenuBar menuBar = new MenuBar();

	    // Staff menu - new, exit
	    Menu fileMenu = new Menu("Staff");
	    MenuItem newMenuItem = new MenuItem("New");

		//new event
	    newMenuItem.setOnAction(ActionEvent -> {

			//set layout for the new button
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Add Staff");
			BorderPane mainContent = new BorderPane();
			BorderPane alertContent = new BorderPane();
			BorderPane information = new BorderPane();
			BorderPane selectIFM = new BorderPane();
			BorderPane selectOccupation = new BorderPane();

			//input Name
			TextField name = new TextField();
			name.setText("Label");
			name.clear();
			GridPane grid = new GridPane();
			grid.setVgap(4);
			grid.setHgap(10);
			grid.setPadding(new Insets(5, 5, 5, 5));
			grid.add(new Label("Name: "), 0, 0);
			grid.add(name, 1, 0);


			//input Age
			TextField age = new TextField();
			age.setText("Label2");
			age.clear();
			GridPane grid2 = new GridPane();
			grid2.setVgap(4);
			grid2.setHgap(10);
			grid2.setPadding(new Insets(5, 5, 5, 5));
			grid2.add(new Label("Age:     "), 0, 0);
			grid2.add(age, 1, 0);

			//selectSex
			CheckBox male = new CheckBox("Male");
			CheckBox female = new CheckBox("Female");

			//selectOccupation
			ChoiceBox cb = new ChoiceBox();
			cb.setItems(FXCollections.observableArrayList("Radiographer", "Nurse", "Receptionist", "Doctor"));
			final Label label = new Label("Select Occupation");
			label.setStyle("-fx-font: 15 arial;");
			label.setLayoutX(40);

			//set layout
			mainContent.setBottom(alertContent);
			alertContent.setTop(selectOccupation);
			alertContent.setCenter(information);
			alertContent.setBottom(selectIFM);

			information.setTop(grid);
			information.setBottom(grid2);
			selectOccupation.setRight(cb);
			selectOccupation.setLeft(label);
			selectIFM.setLeft(male);
			selectIFM.setRight(female);
			alert.getDialogPane().setContent(alertContent);
			alert.show();
		});
	    
	    
	    fileMenu.getItems().addAll(newMenuItem);
	    
	    //MenuItem for progress
	    Menu progressMenu = new Menu("Progress");
		MenuItem bugMenuItem = new MenuItem("Bug");
	    progressMenu.getItems().add(bugMenuItem);

	    //Bug menu
	    bugMenuItem.setOnAction(a -> {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Bug processing progress");

			ProgressIndicator pi = new ProgressIndicator(0.6);

			BorderPane alertContent = new BorderPane();
			alertContent.setCenter(pi);
			alert.getDialogPane().setContent(alertContent);
			alert.show();
		});
	    
	    //App Requirements menu
		MenuItem demandMenuItem = new MenuItem("App Requirements");
	    progressMenu.getItems().add(demandMenuItem);
	    
	    //MenuItem for Manage
	    Menu manageMenu = new Menu("Manage");
	    ToggleGroup tGroup = new ToggleGroup();
		MenuItem meItem = new MenuItem("Equipment");
		MenuItem othersItem = new MenuItem("Other");

	    manageMenu.getItems().addAll(meItem, othersItem,
	        new SeparatorMenuItem());
	    
	    //Events for manage appointments
	    Menu appointItem = new Menu("Appointment");
	    MenuItem OperatingTheatre = new MenuItem("OperatingTheatre Appointment");
	    appointItem.getItems().addAll(OperatingTheatre,      
	        new CheckMenuItem("Patient Appointment"),
	        new CheckMenuItem("Doctor Appointment"));

	    //Only show one item(OperatingTheatre)
	    OperatingTheatre.setOnAction(a -> {
			Alert alert = new Alert(AlertType.INFORMATION);

			alert.setHeaderText("No appointments scheduled for today.");
			BorderPane alertContent = new BorderPane();

			alert.getDialogPane().setContent(alertContent);
			alert.showAndWait();
		});

	    manageMenu.getItems().add(appointItem);
	    menuBar.getMenus().addAll(fileMenu, progressMenu, manageMenu);	    
	    
	    //Main Window
	    //Show the contact information 
	    List<Employee> employees = Arrays.asList(
	    		new Employee("Zhaohua Song", "zhaohua@unisa.com"),
				new Employee( "Isaac Newton", "isaac@unisa.com"),
				new Employee("Mike Swoski", "mike@unisa.com"),
				new Employee("John Makaro", "john@unisa.com"),
				new Employee("Kate Lemmin", "kate@unisa.com"),
				new Employee("Adam Steven", "adam@unisa.com")
		);

	    final TreeItem<Employee> employeesTree = new TreeItem<>(
	    		new Employee("Doctor Department", "")
		);
	    
	    employeesTree.setExpanded(true);

	    employees.forEach(employee -> employeesTree.getChildren().add(new TreeItem<>(employee)));
	    
	    TreeTableColumn<Employee, String> empColumn = new TreeTableColumn<>("Employee");

		empColumn.setPrefWidth(150);
		empColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getValue().getName()));

		TreeTableColumn<Employee, String> emailColumn = new TreeTableColumn<>("Email");

		emailColumn.setPrefWidth(190);
		emailColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getValue().getEmail()));

        TreeTableView<Employee> treeTableView = new TreeTableView<>(employeesTree);

        treeTableView.getColumns().setAll(empColumn, emailColumn);

		root.getChildren().addAll(menuBar, treeTableView);

		return root;
	}
}