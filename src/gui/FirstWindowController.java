package gui;


import data.Customer;
import database.DataBaseConnection;
import database.DataSource;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import operations.OTP;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
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
    private TextField age, otp, accountNumber, amount;
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
    private ToggleGroup transactionToggleGroup;

    @FXML
    public void handleButtonClicked(ActionEvent event) throws IOException {
        if (event.getSource() == login) {
            String user = userID.getText();
            String pass = password.getText();
            if (DataSource.findEmployee(DataBaseConnection.getConnection(), user, pass)) {
                FirstWindow.getStage().close();
                startNewWindow();
            } else {
                createAlert(Alert.AlertType.ERROR, "FAILED", "Login Failed", "Check your user id and " +
                        "password again.\nThank You!");
            }
        } else if (event.getSource() == addCustomer) {
            String cusName = name.getText();
            String cusAge = age.getText();
            String cusAddress = address.getText();
            String cusPhone = phoneNumber.getText();
            String parent = parentName.getText();
            if (cusName.isEmpty() || cusName.trim().isEmpty() || cusAge.isEmpty() || cusAge.trim().isEmpty() || cusAddress.isEmpty() || cusAddress.trim().isEmpty()
                    || cusPhone.isEmpty() || cusPhone.trim().isEmpty() || parent.isEmpty() || parent.trim().isEmpty()) {
                createAlert(Alert.AlertType.ERROR, "ERROR", "Empty Fields", "Kindly enter all the required fields.");
                name.clear();
                age.clear();
                address.clear();
                phoneNumber.clear();
                parentName.clear();
            } else {
                DataSource.addCustomer(DataBaseConnection.getConnection(), new Customer(cusName, Integer.parseInt(cusAge), cusAddress, parent, cusPhone));
                name.clear();
                age.clear();
                address.clear();
                phoneNumber.clear();
                parentName.clear();
            }
        } else if (event.getSource() == newCustomer) {
            addNewCustomer();
        } else if (event.getSource() == newTransaction) {
            headerLabel.setText("Do Your New Transaction By Entering Details!");
            headerPane.setBackground(new Background(new BackgroundFill(Color.rgb(113, 86, 221), CornerRadii.EMPTY, Insets.EMPTY)));
            newTransactionPane.toFront();
            doTransaction.setDisable(true);
            String selectedToggle = getSelectedToggleButton();

        } else if (event.getSource() == generateOTP) {
            long generatedOTP = OTP.generateOTP();
            System.out.println(generatedOTP);
            if (OTP.verifyOTP(generatedOTP)) {
                doTransaction.setDisable(false);
            } else {
                System.err.println("Enter correct otp");
            }

        }
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

    private void startNewWindow() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("firstwindow.fxml")));
        stage.setTitle("Bank Software");
        stage.setScene(new Scene(root, 1200, 800));
        stage.show();
    }

    private void addNewCustomer() {
        headerLabel.setText("Enter Details To Add New Customer!");
        headerPane.setBackground(new Background(new BackgroundFill(Color.rgb(63, 43, 99), CornerRadii.EMPTY, Insets.EMPTY)));
        newCustomerPane.toFront();
    }

    private String getSelectedToggleButton() {
        ToggleButton selectedButton = (ToggleButton) transactionToggleGroup.getSelectedToggle();
        return selectedButton.getText();
    }

    private void doTransaction(String selectedToggle) throws SQLException {
        String account = accountNumber.getText();
        String amount1 = amount.getText();
        if (selectedToggle.equals("Deposit")) {
            Customer customer = getCustomer(account);
            double newAmount = customer.addAmount(Double.parseDouble(amount1));
            DataSource.updateBalance(DataBaseConnection.getConnection(), newAmount, account);
        }else {
            Customer customer = getCustomer(account);
            double newAmount = customer.deductAmount(Double.parseDouble(amount1));
            DataSource.updateBalance(DataBaseConnection.getConnection(), newAmount, account);
        }

    }


    private Customer getCustomer(String account) throws SQLException {
        ResultSet resultSet = DataSource.getCustomerByAccountNumber(DataBaseConnection.getConnection(), account);
        if (resultSet != null) {
            String name = resultSet.getString(1);
            int age = resultSet.getInt(2);
            String address = resultSet.getString(3);
            String parent = resultSet.getString(4);
            String phone = resultSet.getString(5);
            double amount = resultSet.getDouble(6);
            String accountNumber = resultSet.getString(7);
            return new Customer(name, age, address, parent, phone, accountNumber, amount);
        }else {
            createAlert(Alert.AlertType.WARNING,"WARNING","Cannot do transaction","Either there " +
                    "is insufficient balance or kindly check your account number.");
        }
        return null;
    }
}

