package org.example.mererod;

import jssc.SerialPort;

import java.lang.*;
import java.util.ArrayList;
import java.util.List;

public class Filter extends Serialport {



    //private String buffer = "";
    //private boolean start = true;
    //String[] sensorværdier = null;

    /*
    sql
    en konstruktor eller metoder der initialisere connection og statement object.

    en metode som kan lave en læsning og gemmer.

    Serielport klasse
    den skal have en kontruktor eller en metode til at lave forbindelse.

    i skal lave en metode til at lave en læsning

    en metode til at filtrere læsninger mållinger.

    */

//    public void filter() {
//        for (int z = 0; z < 50; z++) {
//            int j = 0;
//            int[] convert = new int[50];
//            openport(port);
//            String[] sensorværdier;
//            String s = hentStrengFraPort(port);
//            if (s != null) {
//                buffer = buffer + s;
//                int i = buffer.indexOf("X");
//                if (i > -1) {
//                    sensorværdier = buffer.split("X");
//                    if (sensorværdier != null && sensorværdier.length > 0) {
//                        if (sensorværdier[0].indexOf("X") < 1) {
//                            sensorværdier[0] = null;
//                        }
//                        if (buffer.charAt(buffer.length() - 1) != 65) {
//                            buffer = sensorværdier[sensorværdier.length - 1];
//                        } else {
//                            buffer = "";
//                        }
//                        while (j < 50) {
//                            convert[j] = Integer.parseInt(sensorværdier[j]);
//
//                        }
//                    }
//                }
//            }
//        }
//    }
}
