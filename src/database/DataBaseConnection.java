package database;

import javax.xml.crypto.Data;
import java.sql.Connection;

/**
 * Created By: Naman Agarwal
 * User ID: naman2807
 * Package Name: database
 * Project Name: BankingApplication
 * Date: 22-05-2021
 */

public class DataBaseConnection {
    private static Connection connection;

    private DataBaseConnection(){}

    public static Connection getConnection(){
        return connection;
    }

    public static void connectToDataBase(){

    }
}
