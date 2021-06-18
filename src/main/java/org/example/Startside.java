package org.example;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import jssc.SerialPortException;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Startside implements SerialListener {

    private boolean runOnce = false;

    @FXML
    private LineChart Chart;

    @FXML
    public void start() throws IOException {
        // START KNAP
        // her der skal vi have at vi starter for porten, vi skal have vores data ind og lave en dynamisk.
        //med det data der ruller ind
        // slut og gem skal fungere som
        Serialporten.getInstance().OpenPort();
        Serialporten.getInstance().addListener(this);
    }

    public void logud() throws IOException {
        App.setRoot("Login");
        //Denne funktion virker, færdig
    }

    public void slut() throws IOException {
        // her skal vi have at den slutter og gemmer
    }

    public void sog() throws IOException {
        // Her der skal vi søge på et CPR, som vi har gemt nede i i databasen.
    }

    @Override
    public void newData() {
        //if (Serialporten.getInstance().filteredData.toArray().length < 50) { return; }
        //if (runOnce) { return; }
        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                Serialporten serialPort = Serialporten.getInstance();
                Chart.getData().clear();
                XYChart.Series series = new XYChart.Series();

                List<Integer> data = serialPort.filteredData;
                for (int i = 0; i < data.size(); i ++) {
                    // i is the index
                    // yourArrayList.get(i) is the element
                    series.getData().add(new XYChart.Data(String.valueOf(i), data.get(i)));
                }
                Chart.getData().add(series);

                runOnce = true;
            }
        });
    }

}

