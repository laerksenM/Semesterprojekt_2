package org.example.mererod;

import java.util.ArrayList;

public class Datagatherer implements Observable {




    private ArrayList<SensorObserver> observers = new ArrayList();
    @Override
    public void registerObservers(SensorObserver sensorObserver) {
observers.add(sensorObserver);
    }

    @Override
    public void run() {

        readFromPort();

    }

    private void readFromPort() {
    }
}
