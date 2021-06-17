package org.example;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class EKGController extends Application {

    @Override
    public void start(Stage stage) throws Exception {
     stage.setTitle("EKG dynamisk");
        NumberAxis xakse = new NumberAxis();
        NumberAxis yakse = new NumberAxis();
        xakse.setLabel("Tid");
        LineChart<Number,Number> lineChart = new LineChart (xakse,yakse);
        lineChart.setTitle("Stock Monitoring, 2010");
        XYChart.Series series = new XYChart.Series();
        series.setName("My portfolio");

        series.getData().add(new XYChart.Data(1, 23));
        series.getData().add(new XYChart.Data(2, 14));
        series.getData().add(new XYChart.Data(3, 15));
        series.getData().add(new XYChart.Data(4, 24));
        series.getData().add(new XYChart.Data(5, 34));
        series.getData().add(new XYChart.Data(6, 36));
        series.getData().add(new XYChart.Data(7, 22));
        series.getData().add(new XYChart.Data(8, 45));
        series.getData().add(new XYChart.Data(9, 43));
        series.getData().add(new XYChart.Data(10, 17));
        series.getData().add(new XYChart.Data(11, 29));
        series.getData().add(new XYChart.Data(12, 25));
        Scene scene = new Scene(lineChart, 800.0D, 600.0D);
        lineChart.getData() .add(series);
        stage.setScene(scene);
        stage.show();
    }

    public void main(String[] args){
        launch();
    }



}


