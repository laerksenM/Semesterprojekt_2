package org.example;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import jssc.SerialPortException;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
//import org.example.IndDataSensorværdier;
//import org.example.rod.DBadgang;
import org.example.rod.DBconnection;
import java.util.concurrent.ScheduledExecutorService;


public class Startside implements SerialListener {

    //ScheduledExecutorService event;
    //DBconnection dbconn = new DBconnection();
    //DBadgang dbad = new DBadgang();
    boolean control=true;
    //Connection conn= dbconn.getMYSQLConnection();
    //IndDataSensorværdier inddata=new IndDataSensorværdier();
    private boolean runOnce = false;
    XYChart.Series<Number,Number> series; // vi gør den global
    int counter=0;

    @FXML
    private LineChart<Number,Number> Chart;


    @FXML
    public void start() throws IOException {
        // START KNAP
        // her der skal vi have at vi starter for porten, vi skal have vores data ind og lave en dynamisk.
        //med det data der ruller ind
        // slut og gem skal fungere som
        series = new XYChart.Series<Number,Number>();
        Chart.getData().add(series);
        Serialporten.getInstance().OpenPort();
        Serialporten.getInstance().addListener(this);
        System.out.println();

        //Dokumentation på at vi har kunne bruge Evenlistener, og add.Eventlistenter og close.port
        //http://javadox.com/org.scream3r/jssc/2.8.0/javadoc/jssc/SerialPort.html#addEventListener(jssc.SerialPortEventListener)

        //preparedStatement
        ArrayList<Integer[]> array = new ArrayList<Integer[]>();
        //array = Serialporten.add(Serialporten.getInstance()); - -ikke medd
        //event = Executors.newSingleThreadExecutor(() ->
                //Platform.runLater(()->
                        //for (Integer[] data : array) {

                            //dbad.InsertIntoMeasurementsArray(1234567890, data[]))); // meningen var at vi skulle have data i denne.
                        }


    public void logud() throws IOException {
        App.setRoot("Login");
        //Denne funktion virker, færdig
    }

    public void slut() throws IOException, SerialPortException {
        Serialporten.getInstance().port.closePort();
        // her skal vi have at den slutter og gemmer
    }

    public void sog() throws IOException {
        // Her der skal vi søge på et CPR, som vi har gemt nede i i databasen.
    }

    @Override
    public void newData() {
        //if (Serialporten.getInstance().filteredData.toArray().length < 50) { return; }
        //if (runOnce) { return; }
        Platform.runLater(new Runnable(){ //
            @Override
            public void run() {
                Serialporten serialPort = Serialporten.getInstance(); // bruger vi til at returnere implematieret objekt.

                List<Integer> data = serialPort.realdata;
                for (int i = 0; i < data.size(); i ++) {
                    // i is the index
                    // yourArrayList.get(i) is the element
                    series.getData().add(new XYChart.Data(String.valueOf(i), data.get(i))); //
                }

                runOnce = true;
            }
        });
    }

}

