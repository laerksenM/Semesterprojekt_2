package org.example.database;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseDTO {

    package org.example.database;

import java.sql.*;
import java.util.ArrayList;

    public class MeasurementDTO {

        private Connection connection;
        private Statement statement;
        private PreparedStatement preparedStatement;
        private ResultSet resultSet;


        public MeasurementDTO(Connection connection) {
            this.connection = connection;
        }
        //den er void fordi den sætter ind
        public void InsertInMeasurements(int value1, double value2, double value3, double value4) {


            String SQLMeasurements = "INSERT INTO Semesterprojekt 2 (id, cpr, data, tid) VALUES (?,?,?);";
            try {
                preparedStatement = connection.prepareStatement(SQLMeasurements);
                preparedStatement.setInt(1, value1);
                preparedStatement.setInt(2, value2);
                preparedStatement.setInt(3, value3);
                preparedStatement.setTimestamp(4, value3);
                preparedStatement.execute();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        public ArrayList<DatabaseObjects> FindAllMeasurementResultsByCPR (int cprTal) {
            ArrayList<DatabaseObjects> liste = new ArrayList<>();

            String SQLResults = "SELECT temperature, spO2, heartrate, time FROM measurements WHERE cpr = " + cprTal + ";";
            try {
                statement = connection.createStatement();
                resultSet = statement.executeQuery(SQLResults);
// vi laver en connection, statement og resultset som laver en forespørgsmål(query)
                while (resultSet.next()) {
                    liste.add(new DatabaseObjects(cprTal,
                            resultSet.getDouble("temperature"),
                            resultSet.getDouble("spO2"),
                            resultSet.getDouble("heartrate")));
                }


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return liste;
        }


    }

}
