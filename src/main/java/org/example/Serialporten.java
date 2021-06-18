package org.example;

import jssc.SerialPort;
import jssc.SerialPortException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.*;

import static java.lang.Integer.parseInt;

interface SerialListener {
    void newData();
}

class Serialporten extends Thread {
    private static Serialporten SerialportenInstance = new Serialporten();
    public Serialporten() {}
    public static Serialporten getInstance() {
        return SerialportenInstance;
    }

    List<String> buffer = new ArrayList<>();
    List<Integer> filteredData = new ArrayList<>();

    private List<SerialListener> listeners = new ArrayList<SerialListener>();

    public void addListener(SerialListener toAdd) {
        listeners.add(toAdd);
    }

    public static void main(String[] args) {}

    SerialPort port = new SerialPort("/dev/cu.usbmodem14101");

    public void OpenPort() {
        try {
            port.openPort();
            port.setParams(38400, 8, 1, 0);

            // Vi kobler her vores serial port op til vores java stream
            port.addEventListener(serialPortEvent -> {
                try {
                    String receivedData = port.readString(serialPortEvent.getEventValue());
                    filter(receivedData);

                    for (SerialListener hl : listeners) {
                        hl.newData();
                    }
                } catch (SerialPortException ex) {
                    System.out.println("Error in receiving string from COM-port: " + ex);
                }
            });
        }
        catch (SerialPortException e) {
            System.err.println("Serial port exception: " + e);
        }
    }

    public void filter(String input) {
        if (buffer.size() == 0) {
            buffer.add(input);
        }

        String firstInBuffer = "";
        if (buffer.size() >= 50) {
            firstInBuffer = buffer.get(0);
            buffer.remove(0);
        }

        Optional<String> optionalData = buffer.stream().reduce((first, second) -> first + second);

        String data = optionalData.orElse("");
        if (!firstInBuffer.endsWith("X")) {
            String[] temp = firstInBuffer.split("X");
            data = Array.get(temp, temp.length - 1) + optionalData.orElse("");
        }

        if (!data.endsWith("X")) {
            data = data.substring(0, data.lastIndexOf("X"));
        }

        // Add filtered data
        filteredData = Arrays.stream(data.split("X")).map(e -> {
                return parseInt(e);

        }).collect(Collectors.toList());

        buffer.add(input);
    }
}

