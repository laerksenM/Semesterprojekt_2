package org.example.sensorer;
import jssc.SerialPort;

import java.util.Arrays;

public class Filter extends Serialport{

    SerialPort port = getport();


    public void filter() {
        int i=0;
        openport(port);
        String[] sensorværdier;
        while (i<50){
          String s= hentStrengFraPort(port);
          sensorværdier = s.split("xD");







        }

    }
}

