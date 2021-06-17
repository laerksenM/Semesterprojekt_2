package org.example.database;

import java.sql.*;

public class DBadgang(Connection conn){

    private final Connection con;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private Statement statement;

    public DBadgang(Connection connection) {
        this.con = connection;
    }

    public void insertUser(String mail,String Password){
        try{
            //if no table:
            String lavTabel =
                    "CREATE TABLE if not exists `Persons` (\n" +
                            "  `idPersons` int NOT NULL AUTO_INCREMENT,\n" +
                            "  `mail` varchar(45) DEFAULT NULL,\n" +
                            "  `passwd` varchar(45) NOT NULL,\n" +
                            "  `firstname` varchar(45) DEFAULT NULL,\n" +
                            "  `lastname` varchar(45) DEFAULT NULL,\n" +
                            "  `role` varchar(45) DEFAULT NULL,\n" +
                            "  PRIMARY KEY (`idPersons`),\n" +
                            "  UNIQUE KEY `mail_UNIQUE` (`mail`)\n" +
                            ") ;";
            statement = con.createStatement();
            statement.execute(lavTabel);
            String SQLInsert = "insert into Persons(mail,passwd) values( ? ,? );";
            preparedStatement = con.prepareStatement(SQLInsert);
            preparedStatement.setString(1,mail);
            preparedStatement.setString(2,Password);
            preparedStatement.executeUpdate();
            System.out.println("User inserted with parameters:"+mail+" and:"+Password);
        }catch(SQLException ex){
            ex.printStackTrace();
        }

    }

    public String[] getUserAndPassword(String mail,String password){
        String[] data = new String[2];
        try{
            String SqlSEARCH = "\n" +
                    "select mail,passwd from Persons where mail =" +
                    "" +
                    " '%s' and passwd = '%s';";
            statement = con.createStatement();
            // statement.executeQuery(SqlSEARCH);
            ResultSet resultSet = statement.executeQuery(String.format(SqlSEARCH, mail,password ));
            if(resultSet!=null && resultSet.next()){
                data[0]=resultSet.getString(1);
                //for mail
                data[1]=resultSet.getString(2);
                //for password
                //verify
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return data;
    }

    public int[] returnMeasurementsfromDB(int size){

        return new int[size];
    }


}