package org.example.mererod;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.*;

public class DataKommunikation {

     //static Connection connection;

    public static void main(String[] arg) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driveren bliver indlæst");


        Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/EKG?serverTimezone=UTC", "root", "Haj1234!");
      //vi laver en connection kaldet "c"
        if(c!=null){
            System.out.println("Nu er der forbindelse");
        }



        Statement stmt = c.createStatement();
        //DBadgang dba = new DBadgang(connection);


        //String[] combo=  dba.getUserAndPassword("1234567890","Haj123");

        String password = "Haj123";
        String CPR="1234567890";

        // indsætter data fra variabler
        int cpr = 567890321;
        String data = "";
        String tid = "";

        //hvad var forskellen på et PREPARED Statement?

        //og et almindeligt statement?
        stmt.executeUpdate(
                "insert into SENSORVÆRDI(CPR,DATA) values('? ,? )");

        // forespørgsler ved søgning
        ResultSet rs = stmt.executeQuery("select CPR, TID, DATA from SENSORVÆRDI");
        while (rs.next()) {
            cpr = rs.getInt("CPR");
            data = rs.getString("DATA");
            tid = rs.getString("TID");

            System.out.println(cpr + " " + data + " " + tid);
        }
    }
}


