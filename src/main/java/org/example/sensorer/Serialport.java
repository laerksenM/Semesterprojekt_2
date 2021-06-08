package org.example.sensorer;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;


    public class Serialport {

        public static void main(String[] args) throws Exception {
            System.out.println("Viser sensorer");


            String[] portnavne = SerialPortList.getPortNames();
            for (int n = 0; n < portnavne.length; n++) {
                String portnavn = portnavne[n];
                System.out.println("port nummer "+n+ " er " + portnavn);
            }

            if (portnavne.length>0) {
                String portnavn = portnavne[0];
                SerialPort port = new SerialPort(portnavn);

                try {
                    port.openPort();
                    port.setParams(SerialPort.BAUDRATE_38400, SerialPort.DATABITS_8,
                            SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

                    for (int i=0; i<10; i++) {
                        Thread.sleep(2500);
                        String strengFraPort = hentStrengFraPort(port);
                        System.out.println(i +  " læst fra port: " + strengFraPort);
                    }

                    while(true) {
                        //NU kører den - 5 ever fordi det er længere end 4 ever.
                        Thread.sleep(2500);
                        String strengFraPort = hentStrengFraPort(port);
                        System.out.println(" læst fra port: " + strengFraPort);
                    }

                    //port.closePort();
                } catch(SerialPortException e) {
                    System.err.println("Serial port exception: " + e);
                }
            }

            System.out.println("Slut");
        }

        /**
         * Læser en streng fra en port
         * @param port porten, der skal læses fra. Porten skal være åben
         * @return en streng læst fra porten. Strengen kan være tom. Returnerer null hvis der ikke kunne læses fra porten
         */
        private static String hentStrengFraPort(SerialPort port) {
            try {
                int antalByteHentet = port.getInputBufferBytesCount();
                System.out.println("Der var " + antalByteHentet + " byte klar på porten.");
                if (antalByteHentet==-1) return null;
                String tekst = port.readString();
                return tekst;
            } catch (Exception e) {
                System.out.println("Der skete en fejl ved hentning fra porten");
                e.printStackTrace();
            }
            return null;
        }
    }

