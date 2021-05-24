package database;

import data.Customer;
import data.Transaction;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

/**
 * Created By: Naman Agarwal
 * User ID: naman2807
 * Package Name: database
 * Project Name: BankingApplication
 * Date: 23-05-2021
 */

public class DataSource {
    public static void addCustomer(Connection connection, Customer customer){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.addCustomerQuery());
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setInt(2, customer.getAge());
            preparedStatement.setString(3, customer.getAddress());
            preparedStatement.setString(4, customer.getParentName());
            preparedStatement.setString(5, customer.getPhoneNumber());
            preparedStatement.setString(6, customer.getAccountNumber());
            int result = preparedStatement.executeUpdate();
            if(result != 0){
                createAlert(Alert.AlertType.CONFIRMATION,"SUCCESS!!","Added Successfully",
                        "The customer with " + customer.getAccountNumber() + " has been added " +
                                "successfully to database.\n Thank You!");
            }else {
                createAlert(Alert.AlertType.WARNING, "FAILED!!", "Error occurred",
                        "The customer, " + customer.getName() + " can't be added to database. " +
                                "Kindly check the details and try adding again. \nThank You!");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void addTransaction(Connection connection, Transaction transaction){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.addTransactionQuery());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }











    private static void createAlert(Alert.AlertType type, String title, String headerText, String context){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(context);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            alert.close();
        }
    }
}
