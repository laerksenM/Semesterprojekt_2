package org.example.sensorer;
import jssc.SerialPort;
import java.lang.*;

import java.util.Arrays;

public class Filter extends Serialport{

    SerialPort port = getport();
    private String buffer = "";
    private boolean start = true;
    String[] sensorværdier = null;


    public void filter() {
        int j=0;
        openport(port);
        String[] sensorværdier;
        while (j<50){
          String s= hentStrengFraPort(port);
          sensorværdier = s.split("X");
          //int[]convert=Integer.parseInt(s.split(j));

        if (s!= null){
            buffer = buffer + s;
            int i= buffer.indexOf("X");
            if(i>-1) {
                sensorværdier = buffer.split("X");
                if (sensorværdier != null && sensorværdier.length>0){
                    if (sensorværdier[0].indexOf("X")<1){
                        sensorværdier[0] = null;
                    }
                if (buffer.charAt(buffer.length()-1)!=65){
                    buffer = sensorværdier[sensorværdier.length-1];
                }
                else{
                    buffer = "";
                }
                }
            }
        }
        }
    }
}
