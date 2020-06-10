package application.Controllers;
import java.util.Arrays;
import java.util.List;


import application.AppController;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Scene;
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
	protected Scene loadAction() {
		
		//Set Layout
		BorderPane root = new BorderPane();
		BorderPane announce = new BorderPane();
		BorderPane content = new BorderPane();
		content.setTop(root);
		content.setCenter(announce);
	    Scene scene = new Scene(content, 300, 250, Color.WHITE);
	    
	    //MenuBars for main window
	    MenuBar menuBar = new MenuBar();
	    root.setTop(menuBar);

	    // Staff menu - new, exit
	    Menu fileMenu = new Menu("Staff");
	    MenuItem newMenuItem = new MenuItem("New");
	    MenuItem exitMenuItem = new MenuItem("Exit");
	    exitMenuItem.setOnAction(actionEvent -> Platform.exit());
		//new event
	    newMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {		
				
				//set layout for the new button
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText("Add Staff");
				BorderPane mainContent = new BorderPane();
				BorderPane alertContent = new BorderPane(); 
				BorderPane information = new BorderPane(); 
				BorderPane selectIFM = new BorderPane(); 
				BorderPane selectOccupation = new BorderPane(); 
				
				//input Name
			    TextField name = new TextField ();
			    name.setText("Label");
			    name.clear();
			    GridPane grid = new GridPane();
			    grid.setVgap(4);
			    grid.setHgap(10);
			    grid.setPadding(new Insets(5, 5, 5, 5));
			    grid.add(new Label("Name: "), 0, 0);
			    grid.add(name, 1, 0);
		
			    
				//input Age
			    TextField age = new TextField ();
			    age.setText("Label2");
			    age.clear();
			    GridPane grid2 = new GridPane();
			    grid2.setVgap(4);
			    grid2.setHgap(10);
			    grid2.setPadding(new Insets(5, 5, 5, 5));
			    grid2.add(new Label("Age:     "), 0, 0);
			    grid2.add(age, 1, 0);
			 
			    //selectSex
			    CheckBox male = new CheckBox("Male");CheckBox female = new CheckBox("Female");
			    
			    //selectOccupation
			    ChoiceBox cb = new ChoiceBox();
			    cb.setItems(FXCollections.observableArrayList( "Radiographer", "Nurse", "Receptionist", "Doctor"));
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
			}			
		});
	    
	    
	    fileMenu.getItems().addAll(newMenuItem,
	        new SeparatorMenuItem(), exitMenuItem);
	    
	    //MenuItem for progress
	    Menu progressMenu = new Menu("Progress");
	    CheckMenuItem bugMenuItem = new CheckMenuItem("Bug");
	    bugMenuItem.setSelected(true);
	    progressMenu.getItems().add(bugMenuItem);
	    //Bug menu
	    bugMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {	
				
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText("Bug processing progress");

				ProgressIndicator pi = new ProgressIndicator(0.6);
			
				BorderPane alertContent = new BorderPane(); 
				alertContent.setCenter(pi);
				alert.getDialogPane().setContent(alertContent);
				alert.show();
			}			
		});
	    
	    //App Requirements menu
	    CheckMenuItem demandMenuItem = new CheckMenuItem("App Requirements ");
	    demandMenuItem.setSelected(true);
	    progressMenu.getItems().add(demandMenuItem);
	    
	    //MenuItem for Manage
	    Menu manageMenu = new Menu("Manage");
	    ToggleGroup tGroup = new ToggleGroup();
	    RadioMenuItem meItem = new RadioMenuItem("Medical equipment");
	    meItem.setToggleGroup(tGroup);

	    RadioMenuItem othersItem = new RadioMenuItem("others");
	    othersItem.setToggleGroup(tGroup);
	    othersItem.setSelected(true);

	    manageMenu.getItems().addAll(meItem, othersItem,
	        new SeparatorMenuItem());
	    
	    //Events for manage appointments
	    Menu appointItem = new Menu("Appointment");
	    MenuItem OperatingTheatre = new MenuItem("OperatingTheatre Appointment");
	    appointItem.getItems().addAll(OperatingTheatre,      
	        new CheckMenuItem("Patient Appointment"),
	        new CheckMenuItem("Doctor Appointment"));
	    //Only show one item(OperatingTheatre)
	    OperatingTheatre.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {	
	
				Alert alert = new Alert(AlertType.INFORMATION);
	
				alert.setHeaderText("No Appointment Today !");
				BorderPane alertContent = new BorderPane(); 
			
				alert.getDialogPane().setContent(alertContent);
				alert.showAndWait();
			}			
		});
	    manageMenu.getItems().add(appointItem);
	    menuBar.getMenus().addAll(fileMenu, progressMenu, manageMenu);	    
	    
	    //Main Window
	    //Show the contact information 
	    List<Employee> employees = Arrays.<Employee> asList(new Employee(
	    	      "Zhaohua Song", "Zhaohua@unisa.com"), new Employee(
	    	      "Isaac", "isaac@unisa.com"), new Employee("Mike",
	    	      "mike@unisa.com"), new Employee("John",
	    	      "john@unisa.com"), new Employee("Kate",
	    	      "kate@unisa.com"), new Employee("Adam",
	    	      "adam@unisa.com"));
	    final TreeItem<Employee> employeesTree = new TreeItem<>(new Employee(
	    	      "Doctor Department", ""));
	    
	    employeesTree.setExpanded(true);
	    employees.stream().forEach((employee) -> {
	    	employeesTree.getChildren().add(new TreeItem<>(employee));
	    });
	    
	    TreeTableColumn<Employee, String> empColumn = new TreeTableColumn<>(
	            "Employee");
	        empColumn.setPrefWidth(150);
	        empColumn
	            .setCellValueFactory((
	                TreeTableColumn.CellDataFeatures<Employee, String> param) -> new ReadOnlyStringWrapper(
	                param.getValue().getValue().getName()));

	        TreeTableColumn<Employee, String> emailColumn = new TreeTableColumn<>(
	            "Email");
	        emailColumn.setPrefWidth(190);
	        emailColumn
	            .setCellValueFactory((
	                TreeTableColumn.CellDataFeatures<Employee, String> param) -> new ReadOnlyStringWrapper(
	                param.getValue().getValue().getEmail()));
        TreeTableView<Employee> treeTableView = new TreeTableView<>(employeesTree);
        treeTableView.getColumns().setAll(empColumn, emailColumn);
        announce.setCenter(treeTableView);

		return scene;
	}
	//Default staff information
	public class Employee {

		    private SimpleStringProperty name;
		    private SimpleStringProperty email;

		    public SimpleStringProperty nameProperty() {
		      if (name == null) {
		        name = new SimpleStringProperty(this, "name");
		      }
		      return name;
		    }

		    public SimpleStringProperty emailProperty() {
		      if (email == null) {
		        email = new SimpleStringProperty(this, "email");
		      }
		      return email;
		    }

		    private Employee(String name, String email) {
		      this.name = new SimpleStringProperty(name);
		      this.email = new SimpleStringProperty(email);
		    }

		    public String getName() {
		      return name.get();
		    }

		    public void setName(String fName) {
		      name.set(fName);
		    }

		    public String getEmail() {
		      return email.get();
		    }

		    public void setEmail(String fName) {
		      email.set(fName);
		    }
		  }
}