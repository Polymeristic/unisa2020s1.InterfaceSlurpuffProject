package application.Controllers;
import application.AppController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
public class Report extends AppController {

    private final TableView<Person> table = new TableView<>();
    private final ObservableList<Person> data = FXCollections.observableArrayList(new Person("A", "B", "C", "D"));
    final HBox hb = new HBox();

    @Override
    protected Scene loadAction() {
        Scene scene = new Scene(new Group());

        //Table Module
        //Show the detail data of patients
        //4 Columns for patients
        TableColumn DateCol = new TableColumn("Date");
        DateCol.setMinWidth(100);
        DateCol.setCellValueFactory(new PropertyValueFactory<>("Date"));
 
        TableColumn NameCol = new TableColumn("Name");
        NameCol.setMinWidth(100);
        NameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
 
        TableColumn RecordCol = new TableColumn("Record");
        RecordCol.setMinWidth(100);
        RecordCol.setCellValueFactory(new PropertyValueFactory<>("Record"));
        
        TableColumn RemarkCol = new TableColumn("Remark");
        RemarkCol.setMinWidth(100);
        RemarkCol.setCellValueFactory(new PropertyValueFactory<>("Remark"));
        
        //Add Columns to the table
        table.setItems(data);
        table.getColumns().addAll(DateCol, NameCol, RecordCol, RemarkCol);
        
        //A button for doctor to add record
        final Button addButton = new Button("Add");
        addButton.setOnAction((ActionEvent e) -> {
            data.add(new Person("Z","X", "Y", "W"));
         });
        hb.getChildren().addAll(addButton);
        hb.setSpacing(3);
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(table, hb);
        
        //Remark Module
		final Label label = new Label("Select One to Send Msg");
	    label.setStyle("-fx-font: 20 arial;");
	    label.setLayoutX(40);
	    ChoiceBox cb = new ChoiceBox();
	    cb.setItems(FXCollections.observableArrayList(
	        "Radiographer", "Patient","Nurse" ,new Separator(),"Admin Staff", "Receptionist")
	    );
	 
		//Set Area
		BorderPane content = new BorderPane(); 
		BorderPane buttonArea = new BorderPane(); 
		BorderPane selectArea = new BorderPane(); 
		TextArea textArea = new TextArea();
        
		//Button
		Button sendMsg = new Button("Send Msg");
		
		//Set Direction
		content.setBottom(buttonArea);
		content.setCenter(textArea);
		content.setTop(selectArea);
		buttonArea.setRight(sendMsg);
		selectArea.setRight(cb);
		selectArea.setCenter(label);

		//Set Style
		Font buttonFont = Font.font("Arial", FontWeight.BOLD,20); //button style
		Font textFont = Font.font("Arial",15); //text style
		sendMsg.setTextFill(Color.GREY); 
		sendMsg.setFont(buttonFont); 
		
		//Send Msg Event
		sendMsg.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {	
				Alert alert = new Alert(AlertType.CONFIRMATION);
				//Set image and text of the alert
				Text text = new Text("Send Msg Success");
				text.setFont(textFont);
				BorderPane alertContent = new BorderPane(); 
				alert.getDialogPane().setContent(alertContent);
				alert.showAndWait();
			}			
		});
		
		//Medical Report Module
		//New a PieChart
	    PieChart pieChart = new PieChart();
	    pieChart.setData(getChartData());
	    pieChart.setTitle("Disease Probability Distribution");
	    pieChart.setLegendSide(Side.LEFT);
	    pieChart.setClockwise(false);
	    pieChart.setLabelsVisible(false);
	
	    //New a Scanned image
	    Group root = new Group();
        GridPane gridpane = new GridPane();
        gridpane.setPadding(new Insets(5));
        gridpane.setHgap(10);
        gridpane.setVgap(10);
        final ImageView imv = new ImageView("resources/images/brain.jpg");
        imv.setFitHeight(200); imv.setFitWidth(200);
        final HBox pictureRegion = new HBox();
        pictureRegion.getChildren().add(imv);
        gridpane.add(pictureRegion, 1, 1);  
        root.getChildren().add(gridpane);   
	    
        //Set distribution
        BorderPane medicalScan = new BorderPane(); 
	    BorderPane medicalScan1 = new BorderPane(); 
	    BorderPane medicalScan2 = new BorderPane(); 
	    medicalScan.setTop(medicalScan1);
	    medicalScan.setBottom(medicalScan2);
	    pieChart.setMaxSize(350, 350);
		medicalScan1.setRight(pieChart);
		medicalScan2.setLeft(root);

		//Main Window
		//Set up tabs
		TabPane tabPane = new TabPane();

		tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
		
		//Table Tab Pane
		Tab tab1 = new Tab();
		tab1.setText("Table");
		tab1.setContent(vbox);
		
		//Remark Tab Pane
		Tab tab2 = new Tab();
		tab2.setText("Remark");
		tab2.setContent(content);
		
		//Medical Tab Pane
		Tab tab3 = new Tab();
		tab3.setText("Medical Report");
		tab3.setContent(medicalScan);

		
		//Add all tabs to tabpanes
		tabPane.getTabs().addAll(tab1,tab2,tab3);		 

		//Set up scene and stage with the buttons and default size
		((Group) scene.getRoot()).getChildren().addAll(tabPane);

		return scene;
	}
	//No Database, default related PieChart
    private ObservableList<Data> getChartData() {
	    ObservableList<Data> answer = FXCollections.observableArrayList();

	    answer.addAll(
	            new PieChart.Data("Diabetes", 17),
	            new PieChart.Data("Solid Tumor",31),
	            new PieChart.Data("Genetic",10),
	            new PieChart.Data("Cardiovascular",20),
	            new PieChart.Data("Other",21)
        );

	    return answer;
	}

//No Database, Default persona database
    public static class Person {
    	 
        private final SimpleStringProperty Date;
        private final SimpleStringProperty Name;
        private final SimpleStringProperty Record;
        private final SimpleStringProperty Remark;
        private Person(String date, String name, String record, String remark) {
            this.Date = new SimpleStringProperty(date);
            this.Name = new SimpleStringProperty(name);
            this.Record = new SimpleStringProperty(record);
            this.Remark = new SimpleStringProperty(remark);
        }
 
        public String getDate() {
            return Date.get();
        }
 
        public void setDate(String date) {
            Date.set(date);
        }
 
        public String getName() {
            return Name.get();
        }
 
        public void setName(String date) {
            Name.set(date);
        }
        
        public String getRecord() {
            return Name.get();
        }
 
        public void setRecord(String date) {
            Name.set(date);
        }
        
        public String getRemark() {
            return Name.get();
        }
 
        public void setRemark(String date) {
            Name.set(date);
        }
    }
}
