package org.example;

import javafx.application.Platform;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Label;
import org.example.sensorer.SerialPort;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Threads {
    //Label og Linechart til bruge label og linechart fra Controlleren
    private Label label;
    private LineChart lineChart;

    //Executorservice, for at undgå at oprette flere tråde end nødvendigt.
    private final ExecutorService SqlHandler = Executors.newSingleThreadExecutor();

    //Den store tråd der kører alle målinger i programmet
    private final Thread MotherloardThread = new Thread(new Runnable() {
        @Override
        public void run() {
            Platform.runLater(() -> Algorithm.getAlgorithmOBJ().setupChart(lineChart)); //Opsætter LineChart
            SerialPort.getSerialPortOBJ().openPort(); //Åbner SeiralPort
            while (ThreadHandler.getShouldMyThreadBeRunning()) {
                SerialPort.getSerialPortOBJ().filter4000measurements(SerialPort.getSerialPortOBJ().getValueA());  //Filtrer Målinger
                System.out.println("Filter A");
                SerialPort.getSerialPortOBJ().setAorB(true); //flipper Boolean
                Platform.runLater(platformthread); //Får JavaFX til at køre denne tråd når den kan
                getSqlHandler().execute(sqlThread); //Får Executorservice til at køre denne runnable som en task


                //Her gentager overordnede igen, bare med en flippet boolean, og et nyt array
                SerialPort.getSerialPortOBJ().filter4000measurements(SerialPort.getSerialPortOBJ().getValueB());
                System.out.println("Filter B");
                SerialPort.getSerialPortOBJ().setAorB(false);
                Platform.runLater(platformthread);
                getSqlHandler().execute(sqlThread);
            }
            SerialPort.getSerialPortOBJ().closePort(); //slukkerPort
        }
    });

    private final Thread platformthread = new Thread(() -> {
        if (SerialPort.getSerialPortOBJ().getAorB()) {
            Algorithm.getAlgorithmOBJ().populateChart(SerialPortClass.getSerialPortOBJ().getValueA()); //Udfylder LineChart
            Algorithm.getAlgorithmOBJ().BPMalgo(SerialPortClass.getSerialPortOBJ().getValueA(), label); //Skifter BPM
            System.out.println("platform A");
        } else {
            Algorithm.getAlgorithmOBJ().populateChart(SerialPort.getSerialPortOBJ().getValueB()); //Udfylder LineChart
            Algorithm.getAlgorithmOBJ().BPMalgo(SerialPort.getSerialPortOBJ().getValueB(), label); //Skifter BPM
            System.out.println("platform B");
        }
    });

    private final Thread sqlThread = new Thread(() -> {
        SQL.getSqlOBJ().findMeasurementID(Algorithm.getAlgorithmOBJ().getCPR()); //Henter CPR
        if (SerialPort.getSerialPortOBJ().getAorB()) {
            System.out.println("SQl A");
            SQL.getSqlOBJ().writeToMeasurementArray(SerialPort.getSerialPortOBJ().getValueA()); //Printer til Database
        } else {
            System.out.println("SQl B");
            SQL.getSqlOBJ().writeToMeasurementArray(SerialPort.getSerialPortOBJ().getValueB()); //Printer til Database
        }
    });

    //Getters and Setters
    public ExecutorService getSqlHandler() {
        return SqlHandler;
    }

    public Thread getMotherloardThread() {
        return MotherloardThread;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public void setLineChart(LineChart lineChart) {
        this.lineChart = lineChart;
    }
}
