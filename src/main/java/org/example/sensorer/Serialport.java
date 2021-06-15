package org.example.sensorer;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;


    public class Serialport {

        public SerialPort getport(){
            System.out.println("Viser sensorer");

            String[] portnavne = SerialPortList.getPortNames();
            for (int n = 0; n < portnavne.length; n++) {
                String portnavn = portnavne[n];
                System.out.println("port nummer "+n+ " er " + portnavn);
                //Todo printer portnavne ud . skal bruges til at se om der er bluetooth porte?
            }

            if (portnavne.length>0) {
                String portnavn = portnavne[0];
                //Todo check efter om jeres første port er den rigtige
                SerialPort port = new SerialPort(portnavn);
                return port;
            }
            return null;
        }

        public void openport(SerialPort port) {
                try {
                    port.openPort();
                    port.setParams(SerialPort.BAUDRATE_38400, SerialPort.DATABITS_8,
                            SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                } catch(SerialPortException e) {
                    System.err.println("Serial port exception: " + e);
                }
        }

        /**
         * Læser en streng fra en port
         * @param port porten, der skal læses fra. Porten skal være åben
         * @return en streng læst fra porten. Strengen kan være tom. Returnerer null hvis der ikke kunne læses fra porten
         */
        public String hentStrengFraPort(SerialPort port) {
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

