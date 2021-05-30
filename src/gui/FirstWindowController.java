package gui;


import data.Customer;
import data.Loan;
import data.Transaction;
import database.DataBaseConnection;
import database.DataSource;
import database.Functionality;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import operations.OTP;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * Created By: Naman Agarwal
 * User ID: naman2807
 * Package Name: gui
 * Project Name: BankingApplication
 * Date: 23-05-2021
 */

public class FirstWindowController {
    @FXML
    private TextField userID;
    @FXML
    private TextField password;
    @FXML
    private Button login;
    @FXML
    private Button addCustomer;
    @FXML
    private TextField name;
    @FXML
    private TextField age;
    @FXML
    private TextField otp;
    @FXML
    private TextField accountNumber;
    @FXML
    private TextField amount;
    @FXML
    private TextField address;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField parentName;
    @FXML
    private Button newCustomer, generateOTP, doTransaction;
    @FXML
    private Label headerLabel;
    @FXML
    private Button newTransaction;
    @FXML
    private Pane headerPane;
    @FXML
    private Pane newCustomerPane;
    @FXML
    private Pane newTransactionPane;
    @FXML
    private Pane transactionHistoryPane;
    @FXML
    private ToggleGroup transactionToggleGroup;
    @FXML
    private Button verifyOTP;
    @FXML
    private TableView<Transaction> transactionTable;
    @FXML
    private Button showTransactionPane;
    @FXML
    private TextField accountNumberForTransaction;
    @FXML
    private Button search;
    @FXML
    private Button showNewLoanPane;
    @FXML
    private Pane newLoanPane;
    @FXML
    private TextField loanAccountField;
    @FXML
    private TextField loanAmount;
    @FXML
    private ComboBox<String> loanType;
    @FXML
    private DatePicker dueDate;
    @FXML
    private Button issueLoan;
    @FXML
    private Button loanRecordPane;
    @FXML
    private TableView<Loan> loanTableView;
    @FXML
    private Pane loanRecordPaneWindow;


    @FXML
    public void handleButtonClicked(ActionEvent event) throws IOException, SQLException {
        if (event.getSource() == login) {
            Functionality.login(userID, password);

        } else if (event.getSource() == newCustomer) {
            resetTransactionHistoryPane();
            resetLoanRecordPane();
            Functionality.openNewCustomerWindow(headerLabel, headerPane, newCustomerPane);

        } else if (event.getSource() == addCustomer) {
            Functionality.addNewCustomer(name, age, phoneNumber, address, parentName);

        } else if (event.getSource() == newTransaction) {
            resetLoanRecordPane();
            resetTransactionHistoryPane();
            Functionality.openNewTransactionWindow(headerLabel, headerPane, newTransactionPane, doTransaction);

        } else if (event.getSource() == generateOTP) {
            long generatedOTP = OTP.generateOTP();
            System.out.println(generatedOTP);

        } else if (event.getSource() == verifyOTP) {
            verifyOTP();

        } else if (event.getSource() == doTransaction) {
            doTransaction(getSelectedToggleButton());

        } else if (event.getSource() == showTransactionPane) {
            resetLoanRecordPane();
            showTransactionTable();

        } else if (event.getSource() == search) {
            searchTransactionRecordAndShow();

        } else if (event.getSource() == showNewLoanPane) {
            resetTransactionHistoryPane();
            resetLoanRecordPane();
            showLoanPane();

        } else if (event.getSource() == issueLoan) {
            issueLoanToCustomer();

        } else if (event.getSource() == loanRecordPane) {
            resetTransactionHistoryPane();
            showLoanRecordPane();
        }
    }

    private void openNewTransactionWindow() {
        headerLabel.setText("Do Your New Transaction By Entering Details!");
        headerPane.setBackground(new Background(new BackgroundFill(Color.rgb(113, 86, 221), CornerRadii.EMPTY, Insets.EMPTY)));
        newTransactionPane.toFront();
        doTransaction.setDisable(true);
    }

    private String getSelectedToggleButton() {
        ToggleButton selectedButton = (ToggleButton) transactionToggleGroup.getSelectedToggle();
        return selectedButton.getText();
    }

    private void doTransaction(String selectedToggle) throws SQLException {
        String account = accountNumber.getText();
        String amount1 = amount.getText();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd, MMMM yyyy");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("hh:mm:ss");
        if (account.isEmpty() || account.trim().isEmpty() || amount1.isEmpty() || amount1.trim().isEmpty()) {
            createAlert(Alert.AlertType.ERROR, "ERROR", "Empty Fields", "Kindly enter all the required fields.");
        } else {
            Customer customer = getCustomer(account);
            if (customer == null) {
                createAlert(Alert.AlertType.WARNING, "WARNING", "Cannot do transaction", "Kindly check your account number.");
            } else {
                if (selectedToggle.equals("Deposit")) {
                    double newAmount = customer.addAmount(Double.parseDouble(amount1));
                    DataSource.updateBalance(DataBaseConnection.getConnection(), newAmount, account);
                    LocalDate date = LocalDate.now();
                    LocalTime time = LocalTime.now();
                    addTransaction(account, Double.parseDouble(amount1), dateFormat.format(date), timeFormat.format(time), selectedToggle);
                    resetTransactionSection();
                } else {
                    double newAmount = customer.deductAmount(Double.parseDouble(amount1));
                    if (newAmount == customer.getAmount()) {
                        createAlert(Alert.AlertType.WARNING, "FAILURE", "Cannot do transaction!", "" +
                                "There is insufficient balance in your account.");
                    } else {
                        DataSource.updateBalance(DataBaseConnection.getConnection(), newAmount, account);
                        LocalDate date = LocalDate.now();
                        LocalTime time = LocalTime.now();
                        addTransaction(account, Double.parseDouble(amount1), dateFormat.format(date), timeFormat.format(time), selectedToggle);
                    }
                    System.out.println(newAmount);
                }
                resetTransactionSection();
            }
        }
    }

    private void verifyOTP() {
        String enteredOTP = otp.getText();
        if (OTP.verifyOTP(enteredOTP)) {
            doTransaction.setDisable(false);
            createAlert(Alert.AlertType.INFORMATION, "VERIFICATION SUCCESS", "Congratulations", "" +
                    "Your OTP has been verified successfully.");
        } else {
            createAlert(Alert.AlertType.WARNING, "ERROR", "Incorrect OTP", "Please enter correct OTP");
        }
    }

    private Customer getCustomer(String account) throws SQLException {
        ResultSet resultSet = DataSource.getCustomerByAccountNumber(DataBaseConnection.getConnection(), account);
        if (resultSet != null) {
            while (resultSet.next()) {
                String name = resultSet.getString(1);
                int age = resultSet.getInt(2);
                String address = resultSet.getString(3);
                String parent = resultSet.getString(4);
                String phone = resultSet.getString(5);
                double amount = resultSet.getDouble(6);
                String accountNumber = resultSet.getString(7);
                return new Customer(name, age, address, parent, phone, accountNumber, amount);
            }
        }
        return null;
    }

    private void addTransaction(String accountNumber, double amount, String transactionDate, String transactionTime, String operation) {
        DataSource.addTransaction(DataBaseConnection.getConnection(), new Transaction(accountNumber, amount, transactionTime, transactionDate, operation));
    }

    private void resetTransactionSection() {
        accountNumber.clear();
        amount.clear();
        otp.clear();
        doTransaction.setDisable(true);
    }

    private void showTransactionTable() {
        headerLabel.setText("Transaction History Section!");
        headerPane.setBackground(new Background(new BackgroundFill(Color.rgb(69, 116, 226), CornerRadii.EMPTY, Insets.EMPTY)));
        transactionHistoryPane.toFront();
    }

    private void searchTransactionRecordAndShow() throws SQLException {
        String accountNumber = accountNumberForTransaction.getText();
        ResultSet resultSet = DataSource.getTransactionHistory(DataBaseConnection.getConnection(), accountNumber);
        showTransactions(resultSet, accountNumber);
    }

    private void showTransactions(ResultSet result, String accountNumber) throws SQLException {
        ObservableList<Transaction> transactions = FXCollections.observableArrayList();
        if (result != null) {
            while (result.next()) {
                String account = result.getString(1);
                String amount = result.getString(2);
                String action = result.getString(3);
                String date = result.getString(4);
                String time = result.getString(5);
                int id = result.getInt(6);
                transactions.add(new Transaction(String.valueOf(id), account, Double.parseDouble(amount), time, date, action));
            }
            if (transactions.size() > 0) {
                transactionTable.setItems(transactions);
            } else {
                createAlert(Alert.AlertType.WARNING, "NO DATA", "Account Number: " + accountNumber, "" +
                        "No transaction history found for above mentioned account.");
            }
        }
    }

    private void resetTransactionHistoryPane() {
        transactionTable.getItems().clear();
        accountNumberForTransaction.clear();
    }

    private void showLoanPane() {
        headerLabel.setText("Enter Details To Apply For Loan!");
        headerPane.setBackground(new Background(new BackgroundFill(Color.rgb(130, 23, 224), CornerRadii.EMPTY, Insets.EMPTY)));
        newLoanPane.toFront();
    }

    private void issueLoanToCustomer() throws SQLException {
        String account = loanAccountField.getText();
        if (DataSource.isAccountExist(DataBaseConnection.getConnection(), account)) {
            if (!checkIfLoanAlreadyIssuedToAccountNumber(account)) {
                String loanAmountToIssue = loanAmount.getText();
                String loanTypeToIssue = loanType.getSelectionModel().getSelectedItem();
                String date = dueDate.getValue().format(DateTimeFormatter.ofPattern("dd, MMMM yyyy"));
                if (account.isEmpty() || account.trim().isEmpty() || loanAmountToIssue.isEmpty() ||
                        loanAmountToIssue.trim().isEmpty() || loanTypeToIssue.isEmpty() ||
                        loanTypeToIssue.trim().isEmpty() || loanTypeToIssue.isBlank() || date.isEmpty() ||
                        date.trim().isEmpty()) {
                    createAlert(Alert.AlertType.ERROR, "ERROR", "Empty Fields", "Kindly enter all the required fields.");
                } else {
                    DataSource.addLoan(DataBaseConnection.getConnection(), new Loan(account, Double.parseDouble(loanAmountToIssue), loanTypeToIssue, date));
                }
            } else {
                createAlert(Alert.AlertType.WARNING, "ERROR", "ACCOUNT NUMBER: " + account, "" +
                        "Above account had already issued a loan of amount Rs.: " + loanAmount.getText());
            }
            resetIssueLoanPane();
        } else {
            createAlert(Alert.AlertType.WARNING, "ERROR", "ACCOUNT NUMBER: " + account, "" +
                    "Mentioned account number doesn't exist.");
        }

    }

    private boolean checkIfLoanAlreadyIssuedToAccountNumber(String accountNumber) throws SQLException {
        ResultSet resultSet = DataSource.getLoanRecordByAccountNumber(DataBaseConnection.getConnection(), accountNumber);
        if (resultSet == null) {
            return true;
        } else {
            return resultSet.next();
        }
    }

    private void resetIssueLoanPane() {
        loanAccountField.clear();
        loanAmount.clear();
        dueDate.getEditor().clear();
        loanType.getEditor().clear();
    }

    private void showLoanRecordPane() throws SQLException {
        headerLabel.setText("Welcome to Loan Records Section!");
        headerPane.setBackground(new Background(new BackgroundFill(Color.rgb(9, 156, 236), CornerRadii.EMPTY, Insets.EMPTY)));
        loanRecordPaneWindow.toFront();
        setLoanTableContents();
    }

    private void setLoanTableContents() throws SQLException {
        ResultSet resultSet = DataSource.getLoanRecord(DataBaseConnection.getConnection());
        ObservableList<Loan> loanList = FXCollections.observableArrayList();
        if (resultSet != null) {
            while (resultSet.next()) {
                String account = resultSet.getString(1);
                double loanAmount = resultSet.getDouble(2);
                String type = resultSet.getString(3);
                String dueDate = resultSet.getString(4);
                loanList.add(new Loan(account, loanAmount, type, dueDate));
            }
            loanTableView.setItems(loanList);
        } else {
            createAlert(Alert.AlertType.WARNING, "LOAN RECORDS", "No Data", "No loan had been " +
                    "issued to any customer.");
        }
    }

    private void resetLoanRecordPane() {
        loanTableView.getItems().clear();
    }

    private void createAlert(Alert.AlertType type, String title, String headerText, String context) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(context);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            alert.close();
        }
    }

}

