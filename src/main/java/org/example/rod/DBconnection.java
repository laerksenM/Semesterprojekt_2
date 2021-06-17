package org.example.rod;

import javafx.fxml.FXML;
import org.example.App;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {

    private static Connection connection = null;
    private static String MYSQLDriver = "jdbc:mysql://" + "localhost:3306/";
    private static String url;
    public DBconnection() {}

    @FXML
    App app = new App();
    public static Connection getMYSQLConnection(String username, String password, String Schema) {
        url = MYSQLDriver + Schema + "?serverTimezone=Europe/Amsterdam&amp";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            if (connection != null) {
                System.out.println("Connected to MYSQL Schema:"+Schema);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
    Connection conn = DBconnection.getMYSQLConnection("root", "1234Hej1234", "EKG");

}
