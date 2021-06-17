package org.example.database;

public class DatabaseObjects {

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;


        public DatabaseObjects(int cpr, Double temperature, Double sp02, Double heartrate) {
            this.Cpr = cpr;
            this.temperature = temperature;
            this.spO2 = sp02;
            this.heartrate = heartrate;
        }

        private int Cpr;
        private Double temperature,spO2,heartrate;

        public int getCpr() {
            return Cpr;
        }

        public Double getTemperature() {
            return temperature;
        }

        public void setTemperature(Double temperature) {
            this.temperature = temperature;
        }

        public Double getSpO2() {
            return spO2;
        }

        public void setSpO2(Double spO2) {
            this.spO2 = spO2;
        }

        public Double getHeartrate() {
            return heartrate;
        }

        public void setHeartrate(Double heartrate) {
            this.heartrate = heartrate;
        }

        public void setCpr(int cpr) {
            Cpr = cpr;
        }


    }

}
