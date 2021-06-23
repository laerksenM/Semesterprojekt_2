/*package org.example.rod;

import java.sql.Connection;

public class DBconnectionTester {


    public static void main(String[] args) {
        DBconnection cdns = new DBconnection();

        Connection connection = cdns.getMYSQLConnection("client","1234Hej1234","Sensorværdi");
        DBadgang dba = new DBadgang(connection);
        //

        // dba.insertUser("Niels@dtu.dk","Jakson");

        String[] combo=  dba.getUserAndPassword("1234567890","Haj123");

        String password = "Haj123";
        String CPR="1234567890";

        if(combo[1].equals(password)&& combo[0].equals(CPR) ){

        }

        System.out.println(combo[0]+combo[1]);
    }
}


 */