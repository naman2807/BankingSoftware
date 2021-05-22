package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created By: Naman Agarwal
 * User ID: naman2807
 * Package Name: database
 * Project Name: BankingApplication
 * Date: 22-05-2021
 */

public final class DataBaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/banking_software";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static Connection connection;

    private DataBaseConnection(){}

    public static void connectToDataBase(){
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("DataBase Connected");
        } catch (SQLException throwables) {
            System.err.println("Cannot connect to database");
            throwables.printStackTrace();
        }

    }

    public static Connection getConnection(){
        return connection;
    }

}
