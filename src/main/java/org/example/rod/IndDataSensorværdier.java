package org.example.rod;

import java.sql.*;
import java.sql.DriverManager;
import java.time.Instant;

public class IndDataSensorværdier {
    public static void main (String args []){ // her skal vi have et agument som vi kan kalde på, og indstætte til at kulle ind i vores database.
        Timestamp tid = Timestamp.from(Instant.now());
        Connection conn = null;
        Statement stmt = null;
// i denne klasse indsætter vi vores brugere til vores database.
        // vi skal være opmærksomme på at vi ikke kan run den med
        //de sammen primary keys. Derfor er det kun muligt at run' den en gang for hvert id.
        // vi har brugt denne guide for at finde ud af det: https://www.youtube.com/watch?v=xbGcK7HCmAU
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Forbind så");
            conn = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/EKG?serverTimezone=UTC", "root", "Haj1234!");
            System.out.println("Vi har forbindelse");

            System.out.println("Ind til vores skema kaldet EKG");
            //stmt = conn.createStatement();

            String sql = "INSERT INTO Sensorværdi " +
                    "VALUES(1 , '' , TIMESTAMP , '123456789' )";
            //stmt.executeUpdate(sql);
            sql = "INSERT INTO Sensorværdi (id, data, tid, cpr)" +

                    "VALUES(2 , 1 , tid , '0987654321' )";


            PreparedStatement preparedStatement;
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.execute();

            System.out.println("Indsæt ind i tablen, godkend venligst");
        }catch (SQLException se) {
            se.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (stmt != null)
                    conn.close();
            }catch (SQLException se){

            }
            try {
                if (conn != null)
                    conn.close();
            }catch (SQLException se){
                se.printStackTrace();
            }

        }


    }
}
