package org.example.database;

import java.sql.Connection;

public class DBconnectionTester {


    public static void main(String[] args) {
        DBconnection cdns = new DBconnection();

        Connection connection = cdns.getMYSQLConnection("client","1234Hej1234","EKG");
        DBadgang dba = new DBadgang(connection);
        //

        // dba.insertUser("Niels@dtu.dk","Jakson");

        String[] combo=  dba.getUserAndPassword("Niels@dtu.dk","Jakson");

        String password = "Jakson";
        String Mail="Niels@dtu.dk";

        if(combo[1].equals(password)&& combo[0].equals(Mail) ){

        }

        System.out.println(combo[0]+combo[1]);
    }
}