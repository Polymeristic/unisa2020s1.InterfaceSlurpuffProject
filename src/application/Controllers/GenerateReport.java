package application.Controllers;

import application.AppController;
import application.SimpleDialog;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

//Written by Christopher Plesa - Plecy002

public class GenerateReport extends AppController {

    /** Scene associated with the instance **/
    static private VBox root = null;

    @Override
    public Parent loadAction() {
        // Check if this instances scene has already been created
        if (root != null) return root;

        //Borderpane Setup
        root = new VBox();

        // Get calendar week start and end date
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String startDate = df.format(c.getTime());
        for (int i = 0; i <6; i++) {
            c.add(Calendar.DATE, 1);
        }
        String endDate = df.format(c.getTime());

        //HBox for Button to switch Filter Between Days/Months
        HBox filterHBox = new HBox();
        ToggleGroup filterGroup = new ToggleGroup();
        ToggleButton daysButton = new ToggleButton("Days");
        daysButton.setToggleGroup(filterGroup);
        daysButton.setSelected(true);
        ToggleButton monthsButton = new ToggleButton("Months");
        monthsButton.setToggleGroup(filterGroup);
        ToggleButton yearlyButton = new ToggleButton("Yearly");
        yearlyButton.setToggleGroup(filterGroup);
        filterHBox.setSpacing(5);
        filterHBox.getChildren().addAll(daysButton, monthsButton, yearlyButton);
        filterHBox.setAlignment(Pos.TOP_RIGHT);



        //ExportToPNG Button
        HBox exportHBox = new HBox();
        Button exportButton = new Button("Export Chart To PNG");
        exportHBox.getChildren().add(exportButton);
        exportHBox.setPadding(new Insets(15, 20, 5, 10));
        exportHBox.setAlignment(Pos.BOTTOM_RIGHT);

        //BarChart Daily
        CategoryAxis xaxis = new CategoryAxis();
        xaxis.setAnimated(false);
        xaxis.setLabel("Appointment Day");
        NumberAxis yaxis = new NumberAxis();
        yaxis.setAnimated(false);
        yaxis.setLabel("Number Of Appointments");
        BarChart bcDaily = new BarChart(xaxis, yaxis);
        bcDaily.setTitle("Weekly Report From" + " " + startDate + " " + "-" + " " + endDate);
        String AppointmentsMade = "Appointments Made";
        String AppointmentsCancelled = "Appointments Cancelled";

        //Random Maths for Days, just to generate stats for a visual
        int appointmentMonNum = GetRandomNumOfAppointmentsDays();
        int cancelMonNum = GetRandomNumOfAppointmentCancelDays(appointmentMonNum);
        int appointmentTueNum = GetRandomNumOfAppointmentsDays();
        int cancelTueNum = GetRandomNumOfAppointmentCancelDays(appointmentTueNum);
        int appointmentWensNum = GetRandomNumOfAppointmentsDays();
        int cancelWensNum = GetRandomNumOfAppointmentCancelDays(appointmentWensNum);
        int appointmentThursNum = GetRandomNumOfAppointmentsDays();
        int cancelThursNum = GetRandomNumOfAppointmentCancelDays(appointmentThursNum);
        int appointmentFriNum = GetRandomNumOfAppointmentsDays();
        int cancelFriNum = GetRandomNumOfAppointmentCancelDays(appointmentFriNum);
        int appointmentSatNum = GetRandomNumOfAppointmentsDays();
        int cancelSatNum = GetRandomNumOfAppointmentCancelDays(appointmentSatNum);
        int appointmentSunNum = GetRandomNumOfAppointmentsDays();
        int cancelSunNum = GetRandomNumOfAppointmentCancelDays(appointmentSunNum);


        //Barchart for the Week
        XYChart.Series<String, Integer> Monday = new XYChart.Series<>();
        Monday.setName("Monday");
        Monday.getData().add(new XYChart.Data(AppointmentsMade, appointmentMonNum));
        Monday.getData().add(new XYChart.Data(AppointmentsCancelled, cancelMonNum));

        XYChart.Series<String, Integer> Tuesday = new XYChart.Series<>();
        Tuesday.setName("Tuesday");
        Tuesday.getData().add(new XYChart.Data(AppointmentsMade, appointmentTueNum));
        Tuesday.getData().add(new XYChart.Data(AppointmentsCancelled, cancelTueNum));

        XYChart.Series<String, Integer> Wednesday = new XYChart.Series<>();
        Wednesday.setName("Wednesday");
        Wednesday.getData().add(new XYChart.Data(AppointmentsMade, appointmentWensNum));
        Wednesday.getData().add(new XYChart.Data(AppointmentsCancelled, cancelWensNum));

        XYChart.Series<String, Integer> Thursday = new XYChart.Series<>();
        Thursday.setName("Thursday");
        Thursday.getData().add(new XYChart.Data(AppointmentsMade, appointmentThursNum));
        Thursday.getData().add(new XYChart.Data(AppointmentsCancelled, cancelThursNum));

        XYChart.Series<String, Integer> Friday = new XYChart.Series<>();
        Friday.setName("Friday");
        Friday.getData().add(new XYChart.Data(AppointmentsMade, appointmentFriNum));
        Friday.getData().add(new XYChart.Data(AppointmentsCancelled, cancelFriNum));

        XYChart.Series<String, Integer> Saturday = new XYChart.Series<>();
        Saturday.setName("Saturday");
        Saturday.getData().add(new XYChart.Data(AppointmentsMade, appointmentSatNum));
        Saturday.getData().add(new XYChart.Data(AppointmentsCancelled, cancelSatNum));

        XYChart.Series<String, Integer> Sunday = new XYChart.Series<>();
        Sunday.setName("Sunday");
        Sunday.getData().add(new XYChart.Data(AppointmentsMade, appointmentSunNum));
        Sunday.getData().add(new XYChart.Data(AppointmentsCancelled, cancelSunNum));

        //Add all Chart Series to the chart
        bcDaily.getData().addAll(Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday);


        //BarChart for the Monthly setup
        CategoryAxis monthXAxis = new CategoryAxis();
        monthXAxis.setAnimated(false);
        monthXAxis.setLabel("Appointment Month");
        NumberAxis monthYAxis = new NumberAxis();
        monthYAxis.setAnimated(false);
        monthYAxis.setLabel("Number Of Appointments");
        BarChart bcMonth = new BarChart(monthXAxis, monthYAxis);
        bcMonth.setTitle("Monthly Appointment Report");
        String appointmentsMadeMonthly = "Appointments Made";
        String appointmentsCancelledMonthly = "Appointments Cancelled";

        //Random Maths for Days, just to generate stats for a visual
        int appointmentJanNumMonth = GetRandomNumOfAppointmentsMonth();
        int cancelJanNumMonth = GetRandomNumOfAppointmentCancelDays(appointmentJanNumMonth);
        int appointmentFebNumMonth = GetRandomNumOfAppointmentsMonth();
        int cancelFebNumMonth = GetRandomNumOfAppointmentCancelDays(appointmentFebNumMonth);
        int appointmentMarNumMonth = GetRandomNumOfAppointmentsMonth();
        int cancelMarNumMonth = GetRandomNumOfAppointmentCancelDays(appointmentMarNumMonth);
        int appointmentAprilNumMonth = GetRandomNumOfAppointmentsMonth();
        int cancelAprilNumMonth = GetRandomNumOfAppointmentCancelDays(appointmentAprilNumMonth);
        int appointmentMayNumMonth = GetRandomNumOfAppointmentsMonth();
        int cancelMayNumMonth = GetRandomNumOfAppointmentCancelDays(appointmentMayNumMonth);
        int appointmentJuneNumMonth = GetRandomNumOfAppointmentsMonth();
        int cancelJuneNumMonth = GetRandomNumOfAppointmentCancelDays(appointmentJuneNumMonth);
        int appointmentJulyNumMonth = GetRandomNumOfAppointmentsMonth();
        int cancelJulyNumMonth = GetRandomNumOfAppointmentCancelDays(appointmentJulyNumMonth);
        int appointmentAugNumMonth = GetRandomNumOfAppointmentsMonth();
        int cancelAugNumMonth = GetRandomNumOfAppointmentCancelDays(appointmentAugNumMonth);
        int appointmentSepNumMonth = GetRandomNumOfAppointmentsMonth();
        int cancelSepNumMonth = GetRandomNumOfAppointmentCancelDays(appointmentSepNumMonth);
        int appointmentOctNumMonth = GetRandomNumOfAppointmentsMonth();
        int cancelOctNumMonth = GetRandomNumOfAppointmentCancelDays(appointmentOctNumMonth);
        int appointmentNovNumMonth = GetRandomNumOfAppointmentsMonth();
        int cancelNovNumMonth = GetRandomNumOfAppointmentCancelDays(appointmentNovNumMonth);
        int appointmentDecNumMonth = GetRandomNumOfAppointmentsMonth();
        int cancelDecNumMonth = GetRandomNumOfAppointmentCancelDays(appointmentDecNumMonth);

        //Add Months to the charts, ensuring they grouped.
        XYChart.Series<String, Integer> January = new XYChart.Series<>();
        January.setName("January");
        January.getData().add(new XYChart.Data(appointmentsMadeMonthly, appointmentJanNumMonth));
        January.getData().add(new XYChart.Data(appointmentsCancelledMonthly, cancelJanNumMonth));

        XYChart.Series<String, Integer> February = new XYChart.Series<>();
        February.setName("February");
        February.getData().add(new XYChart.Data(appointmentsMadeMonthly, appointmentFebNumMonth));
        February.getData().add(new XYChart.Data(appointmentsCancelledMonthly, cancelFebNumMonth));

        XYChart.Series<String, Integer> March = new XYChart.Series<>();
        March.setName("March");
        March.getData().add(new XYChart.Data(appointmentsMadeMonthly, appointmentMarNumMonth));
        March.getData().add(new XYChart.Data(appointmentsCancelledMonthly, cancelMarNumMonth));

        XYChart.Series<String, Integer> April = new XYChart.Series<>();
        April.setName("April");
        April.getData().add(new XYChart.Data(appointmentsMadeMonthly, appointmentAprilNumMonth));
        April.getData().add(new XYChart.Data(appointmentsCancelledMonthly, cancelAprilNumMonth));

        XYChart.Series<String, Integer> May = new XYChart.Series<>();
        May.setName("May");
        May.getData().add(new XYChart.Data(appointmentsMadeMonthly, appointmentMayNumMonth));
        May.getData().add(new XYChart.Data(appointmentsCancelledMonthly, cancelMayNumMonth));

        XYChart.Series<String, Integer> June = new XYChart.Series<>();
        June.setName("June");
        June.getData().add(new XYChart.Data(appointmentsMadeMonthly, appointmentJuneNumMonth));
        June.getData().add(new XYChart.Data(appointmentsCancelledMonthly, cancelJuneNumMonth));

        XYChart.Series<String, Integer> July = new XYChart.Series<>();
        July.setName("July");
        July.getData().add(new XYChart.Data(appointmentsMadeMonthly, appointmentJulyNumMonth));
        July.getData().add(new XYChart.Data(appointmentsCancelledMonthly, cancelJulyNumMonth));

        XYChart.Series<String, Integer> August = new XYChart.Series<>();
        August.setName("August");
        August.getData().add(new XYChart.Data(appointmentsMadeMonthly, appointmentAugNumMonth));
        August.getData().add(new XYChart.Data(appointmentsCancelledMonthly, cancelAugNumMonth));

        XYChart.Series<String, Integer> September = new XYChart.Series<>();
        September.setName("September");
        September.getData().add(new XYChart.Data(appointmentsMadeMonthly, appointmentSepNumMonth));
        September.getData().add(new XYChart.Data(appointmentsCancelledMonthly, cancelSepNumMonth));

        XYChart.Series<String, Integer> October = new XYChart.Series<>();
        October.setName("October");
        October.getData().add(new XYChart.Data(appointmentsMadeMonthly, appointmentOctNumMonth));
        October.getData().add(new XYChart.Data(appointmentsCancelledMonthly, cancelOctNumMonth));

        XYChart.Series<String, Integer> November = new XYChart.Series<>();
        November.setName("November");
        November.getData().add(new XYChart.Data(appointmentsMadeMonthly, appointmentNovNumMonth));
        November.getData().add(new XYChart.Data(appointmentsCancelledMonthly, cancelNovNumMonth));

        XYChart.Series<String, Integer> December = new XYChart.Series<>();
        December.setName("December");
        December.getData().add(new XYChart.Data(appointmentsMadeMonthly, appointmentDecNumMonth));
        December.getData().add(new XYChart.Data(appointmentsCancelledMonthly, cancelDecNumMonth));

        //Add all Chart Series to the chart
        bcMonth.getData().addAll(January, February, March, April, May, June, July, August, September, October,
                November, December);


        //BarChart for the Monthly setup
        CategoryAxis yearlyXAxis = new CategoryAxis();
        yearlyXAxis.setAnimated(false);
        yearlyXAxis.setLabel("Appointment Year");
        NumberAxis yearlyYAxis = new NumberAxis();
        yearlyYAxis.setAnimated(false);
        yearlyYAxis.setLabel("Number Of Appointments");
        BarChart bcYearly = new BarChart(yearlyXAxis, yearlyYAxis);
        bcYearly.setTitle("5 year Appointment Report");
        String appointmentsMadeYearly = "Appointments Made";
        String appointmentsCancelledYearly = "Appointments Cancelled";

        //Yearly Charts created
        int appointment2015NumYearly = GetRandomNumOfAppointmentsYearly();
        int cancel2015NumYearly  = GetRandomNumOfAppointmentCancelDays(appointment2015NumYearly);
        int appointment2016NumYearly  = GetRandomNumOfAppointmentsYearly();
        int cancel2016NumYearly  = GetRandomNumOfAppointmentCancelDays(appointment2016NumYearly);
        int appointment2017NumYearly  = GetRandomNumOfAppointmentsYearly();
        int cancel2017NumYearly  = GetRandomNumOfAppointmentCancelDays(appointment2017NumYearly);
        int appointment2018NumYearly  = GetRandomNumOfAppointmentsYearly();
        int cancel2018NumYearly  = GetRandomNumOfAppointmentCancelDays(appointment2018NumYearly);
        int appointment2019NumYearly  = GetRandomNumOfAppointmentsYearly();
        int cancel2019NumYearly  = GetRandomNumOfAppointmentCancelDays(appointment2019NumYearly);
        int appointment2020NumYearly  = GetRandomNumOfAppointmentsYearly();
        int cancel2020NumYearly  = GetRandomNumOfAppointmentCancelDays(appointment2020NumYearly);

        //Barchart for the Years
        XYChart.Series<String, Integer> Year2015 = new XYChart.Series<>();
        Year2015.setName("2015");
        Year2015.getData().add(new XYChart.Data(appointmentsMadeYearly, appointment2015NumYearly));
        Year2015.getData().add(new XYChart.Data(appointmentsCancelledYearly, cancel2015NumYearly));

        XYChart.Series<String, Integer> Year2016 = new XYChart.Series<>();
        Year2016.setName("2016");
        Year2016.getData().add(new XYChart.Data(appointmentsMadeYearly, appointment2016NumYearly));
        Year2016.getData().add(new XYChart.Data(appointmentsCancelledYearly, cancel2016NumYearly));

        XYChart.Series<String, Integer> Year2017 = new XYChart.Series<>();
        Year2017.setName("2017");
        Year2017.getData().add(new XYChart.Data(appointmentsMadeYearly, appointment2017NumYearly));
        Year2017.getData().add(new XYChart.Data(appointmentsCancelledYearly, cancel2017NumYearly));

        XYChart.Series<String, Integer> Year2018 = new XYChart.Series<>();
        Year2018.setName("2018");
        Year2018.getData().add(new XYChart.Data(appointmentsMadeYearly, appointment2018NumYearly));
        Year2018.getData().add(new XYChart.Data(appointmentsCancelledYearly, cancel2018NumYearly));

        XYChart.Series<String, Integer> Year2019 = new XYChart.Series<>();
        Year2019.setName("2019");
        Year2019.getData().add(new XYChart.Data(appointmentsMadeYearly, appointment2019NumYearly));
        Year2019.getData().add(new XYChart.Data(appointmentsCancelledYearly, cancel2019NumYearly));

        XYChart.Series<String, Integer> Year2020 = new XYChart.Series<>();
        Year2020.setName("2020");
        Year2020.getData().add(new XYChart.Data(appointmentsMadeYearly, appointment2020NumYearly));
        Year2020.getData().add(new XYChart.Data(appointmentsCancelledYearly, cancel2020NumYearly));

        //Add all Chart Series to the chart
        bcYearly.getData().addAll(Year2015, Year2016, Year2017, Year2018, Year2019, Year2020);

        daysButton.setOnAction(ActionEvent -> root.getChildren().set(1, bcDaily));
        monthsButton.setOnAction(ActionEvent -> root.getChildren().set(1, bcMonth));
        yearlyButton.setOnAction(ActionEvent -> root.getChildren().set(1, bcYearly));


        exportButton.setOnAction(ActionEvent -> {
            if (yearlyButton.isSelected()){
                saveAsPng(bcYearly);
            } else if (monthsButton.isSelected()){
                saveAsPng(bcMonth);
            } else {
                saveAsPng(bcDaily);
            }
        });

        root.getChildren().addAll(filterHBox, bcDaily, exportHBox);

        root.getChildren().set(1, bcDaily);

        return root;
    }

    //Method used to generate png image, saves to resources folder by default.
    public void saveAsPng(BarChart barChart) {
        WritableImage image = barChart.snapshot(new SnapshotParameters(), null);

        File file = new File(System.getProperty("user.home") + "/Desktop/chart.png");

        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
            SimpleDialog.New(Alert.AlertType.INFORMATION, "Export Complete", "Snapshot exported to your desktop.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Get Random number of appointment days.
    private int GetRandomNumOfAppointmentsDays(){
        Random rand = new Random();
        int minAppointment = 1;
        int maxAppointment = 100;
        return rand.nextInt(maxAppointment - minAppointment + 1) + minAppointment;
    }

    //Get Random Number of Appointment Month.
    private int GetRandomNumOfAppointmentsMonth(){
        Random rand = new Random();
        int minAppointment = 100;
        int maxAppointment = 500;
        return rand.nextInt(maxAppointment - minAppointment + 1) + minAppointment;
    }

    //Get Random numbers of appointments yearly.
    private int GetRandomNumOfAppointmentsYearly(){
        Random rand = new Random();
        int minAppointment = 500;
        int maxAppointment = 1000;
        return rand.nextInt(maxAppointment - minAppointment + 1) + minAppointment;
    }

    //Take the parameter of maxNumber(depending on daily/monthly/yearly) then generate cancelled appointments.
    private int GetRandomNumOfAppointmentCancelDays(int maxNumber){
        Random rand = new Random();
        int minCancelAppointment = 1;
        return rand.nextInt(maxNumber - minCancelAppointment + 1) + minCancelAppointment;
    }



}
