/package org.example.rod;

import java.sql.*;

public class DBadgang {

    private final Connection con;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private Statement statement;
    Connection conn = null;
    Statement stmt = null;

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
    public void insertMeasurementsIntoMeasurementsTable(int[] datasomIfaarfraSensorKlassen,String CPR){

        try{
            //if no table:
            String lavTabel ="CREATE TABLE if not exists `measurements` (\n" +
                    "  `id` int NOT NULL AUTO_INCREMENT,\n" +
                    "  `CPR` varchar(11) NOT NULL,\n" +
                    "  `measurementvalue` int NOT NULL,\n" +
                    "  `lortetid` timestamp NULL DEFAULT CURRENT_TIMESTAMP,\n" +
                    "  PRIMARY KEY (`id`)\n" +
                    ") ";

            statement = conn.createStatement();
            statement.execute(lavTabel);

            String SQLInsert = "insert into measurements(measurementvalue,CPR) values (?,?);";
            preparedStatement = conn.prepareStatement(SQLInsert);
            //loop over the array or arrayList
            for (int i=0;i<datasomIfaarfraSensorKlassen.length;i++){
                preparedStatement.setInt(1,datasomIfaarfraSensorKlassen[i]);
                preparedStatement.setString(2,CPR);
                //alloker første plads i SQL Statementet - til at være det I'te element fra et Array eller ArrayList
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            System.out.println("Data inserted, with "+ preparedStatement.getClass() +" values");
        }catch(SQLException ex){
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

        public void InsertIntoMeasurementsArray(int value1, String[] value2){
            String SQLMeasurementsArray = "INSERT INTO EKG (cpr, data) VALUES (?,?)";
            try {
                preparedStatement = connection.prepareStatement(SQLMeasurementsArray);
                for (int i=0; i < value2.length; i++){
                    preparedStatement.setInt(1, value1);
                    preparedStatement.setString(2, value 2[i]);
                    preparedStatement.addBatch();
                }
                preparedStatement.executeBatch();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    //public int[] returnMeasurementsfromDB(int size) {

       // return new int[size];
    }

