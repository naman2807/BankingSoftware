package database;

import data.Bank;
import data.Branch;
import data.Customer;
import data.Transaction;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.*;
import java.util.Optional;

/**
 * Created By: Naman Agarwal
 * User ID: naman2807
 * Package Name: database
 * Project Name: BankingApplication
 * Date: 23-05-2021
 */

public class DataSource {

    public static boolean findEmployee(Connection connection, String userID, String password){
        boolean found = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.findEmployeeQuery());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String id = resultSet.getString(1);
                String pass = resultSet.getString(2);
                if(userID.equals(id) && pass.equals(password)){
                    found = true;
                    break;
                }
            }
            return found;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return found;
    }

    public static void addCustomer(Connection connection, Customer customer){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.addCustomerQuery());
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setInt(2, customer.getAge());
            preparedStatement.setString(3, customer.getAddress());
            preparedStatement.setString(4, customer.getParentName());
            preparedStatement.setString(5, customer.getPhoneNumber());
            preparedStatement.setDouble(6, customer.getAmount());
            preparedStatement.setString(7, customer.getAccountNumber());
            int result = preparedStatement.executeUpdate();
            checkResult(result,"SUCCESS!!","Added Successfully",
                    "The customer with " + customer.getAccountNumber() + " has been added " +
                            "successfully to database.\n Thank You!");
        } catch (SQLException throwables) {
            createAlert(Alert.AlertType.ERROR,"ERROR","Cannot Add to Record", "Kindly check the entered details.");
            throwables.printStackTrace();
        }
    }

    public static void addTransaction(Connection connection, Transaction transaction){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.addTransactionQuery());
            preparedStatement.setString(1, transaction.getAccountNumber());
            preparedStatement.setString(2, String.valueOf(transaction.getAmount()));
            preparedStatement.setString(3, transaction.getTransactionDate());
            preparedStatement.setString(4, transaction.getTransactionTime());
           int result =  preparedStatement.executeUpdate();
           checkResult(result,"NEW TRANSACTION","Added to Record",
                   "Transaction of Rs." + transaction.getAmount() + " from " + transaction.getAccountNumber()
                           + " on " + transaction.getTransactionDate() + " has been done successfully.");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public static void addBranch(Connection connection, Branch branch){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.addBranchQuery());
            preparedStatement.setString(1, branch.getBranchCode());
            preparedStatement.setString(2, branch.getName());
            int result = preparedStatement.executeUpdate();
            checkResult(result,"CONFIRMATION", "Success", "Branch : " + branch.getName() +
                    "\nBranch code : " + branch.getBranchCode() + "\nHas been added successfully to record.\nThank You!" );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public static void addBank(Connection connection, Bank bank){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.addBankQuery());
            preparedStatement.setString(1, bank.getName());
            int result = preparedStatement.executeUpdate();
            checkResult(result, "CONFIRMATION", "Success", "Bank : " + bank.getName() +
                    "Has been added successfully to record.\nThank You!" );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void updateBalance(Connection connection, double amount, String accountNumber){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.updateBalanceQuery());
            preparedStatement.setDouble(1, amount);
            preparedStatement.setString(2, accountNumber);
            int result = preparedStatement.executeUpdate();
            checkResult(result,"SUCCESS","Updated amount success", "Account number : " + accountNumber +
                    " has been updated its amount.");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static ResultSet getTransactionHistory(Connection connection, String accountNumber){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.getTransactionHistoryQuery());
            preparedStatement.setString(1, accountNumber);
            return preparedStatement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static void addLoan(Connection connection, String accountNumber, double amount, Date date){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.addLoanQuery());
            preparedStatement.setString(1,accountNumber );
            preparedStatement.setDouble(2, amount);
            preparedStatement.setDate(3, date);
            int result = preparedStatement.executeUpdate();
            checkResult(result, "SUCCESS", "New Loan" , "New loan of amount Rs. " +
                    amount + " has been assigned to Account Number: " + accountNumber);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static ResultSet getLoanRecord(Connection connection){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.getLoanRecordQuery());
            return preparedStatement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static ResultSet getCustomersRecord(Connection connection){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.getCustomersRecordQuery());
            return preparedStatement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static ResultSet getCustomerByAccountNumber(Connection connection, String accountNumber){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.findCustomerQueryByAccountNumber());
            preparedStatement.setString(1, accountNumber);
            return preparedStatement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static ResultSet getBranchRecord(Connection connection){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.getBranchesQuery());
            return preparedStatement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    private static void checkResult(int result, String title, String headerText, String context) {
        if(result != 0){
            createAlert(Alert.AlertType.CONFIRMATION,title,headerText, context);
        }else {
            createAlert(Alert.AlertType.WARNING,"WARNING","Some Error occurred","Kindly check your details " +
                    "and other information");
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
