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
    private Connection connection;
    private DataBaseConnection instance = new DataBaseConnection();

    private DataBaseConnection(){}
}
