package org.example.rod;
import java.sql.*;

public class ConnectionSingleton {

    private String url, user, password;
    private Connection conn = null;
    private ResultSet resultSet;
    private Statement statement;
    private PreparedStatement preparedStatement;

    public Connection ConnectionSingleton(){
        user="root";
        password = "Haj1234!";
        String schema = "EKG";
        url = "jbdc:mysql://"+ "localhost:3306" + schema + "?serverTimezone=Europe/Amsterdam&amp";
        System.out.println("Connection attemp");
        try {
            Class.forName("com.mysql.cj.jbdc.Driver");
            conn = DriverManager.getConnection(url, user, password);

            if (conn != null) {
                System.out.println("Online");
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }
}

