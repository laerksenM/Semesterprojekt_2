package org.example;
import java.sql.*;
import java.sql.DriverManager;

public class IndDataBrugere {

    public static void main (String args []){
        Connection conn = null;
        Statement stmt = null;
// i denne klasse indsætter vi vores brugere til vores database.
        // vi skal være opmærksomme på at vi ikke kan run den med
        //de sammen primary keys. Derfor er det kun muligt at run' den en gang for hvert id.
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Forbind så");
            conn = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/EKG?serverTimezone=UTC", "root", "Haj1234!");
            System.out.println("Vi har forbindelse");

            System.out.println("Ind til vores skema kaldet EKG");
            stmt = conn.createStatement();

            String sql = "INSERT INTO Brugere " +
                    "VALUES(1 , '1234567890' , 'Haj123' )";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO Brugere " +
                    "VALUES(2 , '0987654321' , 'wow123' )";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO Brugere " +
                    "VALUES(3,'9012345678','nej123' )";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO Brugere " +
                    "VALUES(4,'8012345678','bye123' )";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO Brugere " +
                    "VALUES(5,'7012345678','nej123' )";
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
*/