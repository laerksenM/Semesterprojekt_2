package org.example.database;

import javafx.fxml.FXML;
import org.example.App;

import java.sql.*;

public class ConnectionSQL {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/EKG?serverTimezone=UTC";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "Haj1234!";
    private static final String SELECT_QUERY = "SELECT * FROM Sensorværdi WHERE Bruger = cpr and Kodeord = kodeord";

    @FXML
    App app = new App();

    public boolean validate(String Bruger, String Kodeord) throws SQLException {


        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY)) {
            preparedStatement.setString(1, Bruger);
            preparedStatement.setString(2, Kodeord);

            System.out.println(preparedStatement);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }


        } catch (SQLException e) {
            printSQLException(e);
        }
        return false;
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLHvad: " + ((SQLException) e).getSQLState());
                System.err.println("FEJL: " + ((SQLException) e).getErrorCode());
                System.err.println("Besked: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Problem: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
