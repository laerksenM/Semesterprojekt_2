package org.example;

import jssc.SerialPort;
import jssc.SerialPortException;
import java.lang.*;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

import static java.lang.Integer.parseInt;


interface SerialListener {
    void newData();
}

class Serialporten extends Thread {
    //implementering af observer pattern ? giv en ny Thread et

    private static Serialporten SerialportenInstance = new Serialporten();

    public Serialporten() {
    }

    public static Serialporten getInstance() {
        return SerialportenInstance;
    }

    List<String> buffer = new ArrayList<>();
    List<Integer> filteredData = new ArrayList<>();
    List<Integer> realdata = new ArrayList<>();

    private boolean start = true;
    String[] sensorværdier = null;

    private List<SerialListener> listeners = new ArrayList<SerialListener>();

    public void addListener(SerialListener toAdd) {
        listeners.add(toAdd);
    }

    public static void main(String[] args) {
    }

    SerialPort port = new SerialPort("/dev/cu.usbmodem14101");

    public void OpenPort() {
        try {
            port.openPort();
            port.setParams(38400, 8, 1, 0);

            // Vi kobler her vores serial port op til vores java stream
            port.addEventListener(serialPortEvent -> {
                try {
                    String receivedData = port.readString(serialPortEvent.getEventValue());
                    if (receivedData.contains("�")) { // da vores målinger sendte et tegn stoppede dette for vores data flow.
                        receivedData = ""; // så hvis vi modtager data der indeholder dette tegne vil den blive erstattet af en tom streng.
                    }
                    filter(receivedData);
                    System.out.println(realdata.size());

                    for (SerialListener hl : listeners) {
                        realdata.addAll(filteredData);
                        if (realdata.size() >= 2000) {
                            hl.newData();
                            realdata.clear();
                        }

                    }
                } catch (SerialPortException ex) {
                    System.out.println("Error in receiving string from COM-port: " + ex);
                }
            });
        } catch (SerialPortException e) {
            System.err.println("Serial port exception: " + e);
        }
    }

    public void filter(String input) {
        while (true) {
            if (buffer.size() == 0) { //
                buffer.add(input);
            }

            String firstInBuffer = "";
            if (buffer.size() >= 50) { // vi vil have 50 målinger
                firstInBuffer = buffer.get(0);
                buffer.remove(0);//fjerner hvad
            }

            Optional<String> optionalData = buffer.stream().reduce((first, second) -> first + second);

            String data = optionalData.orElse(""); // hvis
            if (!firstInBuffer.endsWith("X")) {
                String[] temp = firstInBuffer.split("X");//skiller den
                data = Array.get(temp, temp.length - 1) + optionalData.orElse(""); //
            }

            if (!data.endsWith("X")) { // hvis den sidste ender med X så vil vi bruge vores sub.string
                data = data.substring(0, data.lastIndexOf("X")); //
            }

            // tilføj filteret data
            filteredData = Arrays.stream(data.split("X")).map(e -> { // vi mapper det som den splitter, bruger lamda
                return parseInt(e);// vi parser den til en int

            }).collect(Collectors.toList());

            buffer.add(input); // vi tilføjer vores input

            try {
                Thread.sleep(2000); // vi sætter den til at sleep når vi modtager 2000 målinger.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

