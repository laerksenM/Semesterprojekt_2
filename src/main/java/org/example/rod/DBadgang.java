package org.example.rod;

import java.sql.*;

public class DBadgang {

    private final Connection con;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private Statement statement;

    public DBadgang(Connection connection) {
        this.con = connection;
    }



    public void insertUser(String CPR, String Password) {
        try {
            //if no table:
            String lavTabel =
                    "CREATE TABLE if not exists `Persons` (\n" +
                            "  `id` int NOT NULL AUTO_INCREMENT,\n" +
                            "  `CPR` varchar(45) DEFAULT NULL,\n" +
                            "  `Password` varchar(45) NOT NULL,\n" +
                            "  PRIMARY KEY (`id`),\n" +
                            "  UNIQUE KEY (`id`),\n" +
                            ") ;";
            statement = con.createStatement();
            statement.execute(lavTabel);



            String SQLInsert = "insert into EKG(int[] CPR,password) values( ? ,? );";
            preparedStatement = con.prepareStatement(SQLInsert);
            preparedStatement.setString(1, CPR);
            preparedStatement.setString(2, Password);
            preparedStatement.executeUpdate();
            System.out.println("User inserted with parameters:" + CPR + " and:" + Password);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public String[] getUserAndPassword(String CPR, String password) {
        String[] data = new String[2];
        try {
            String SqlSEARCH = "\n" +
                    "select mail,passwd from Persons where mail =" +
                    "" +
                    " '%s' and passwd = '%s';";
            statement = con.createStatement();
            // statement.executeQuery(SqlSEARCH);
            ResultSet resultSet = statement.executeQuery(String.format(SqlSEARCH, CPR, password));
            if (resultSet != null && resultSet.next()) {
                data[0] = resultSet.getString(1);
                //for mail
                data[1] = resultSet.getString(2);
                //for password
                //verify
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return data;
    }

    public int[] returnMeasurementsfromDB(int size) {

        return new int[size];
    }


}
