package database;

import data.Customer;
import gui.FirstWindow;
import gui.FirstWindowController;
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

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

/**
 * Created By: Naman Agarwal
 * User ID: naman2807
 * Package Name: database
 * Project Name: BankingApplication
 * Date: 30-05-2021
 */

public final class Functionality {

    public static void login(TextField userID, TextField password) throws IOException {
        String user = userID.getText();
        String pass = password.getText();
        if (DataSource.findEmployee(DataBaseConnection.getConnection(), user, pass)) {
            FirstWindow.getStage().close();
            startNewWindow();
        } else {
            createAlert(Alert.AlertType.ERROR, "FAILED", "Login Failed", "Check your user id and " +
                    "password again.\nThank You!");
        }
    }

    private static void startNewWindow() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(FirstWindowController.class.getResource("firstwindow.fxml")));
        stage.setTitle("Bank Software");
        stage.setScene(new Scene(root, 1200, 800));
        stage.show();
    }

    public static void openNewCustomerWindow(Label headerLabel, Pane headerPane, Pane newCustomerPane){
        headerLabel.setText("Enter Details to Add New Customer!");
        headerPane.setBackground(new Background(new BackgroundFill(Color.rgb(63, 43, 99), CornerRadii.EMPTY, Insets.EMPTY)));
        newCustomerPane.toFront();
    }

    public static void addNewCustomer(TextField name, TextField age, TextField phoneNumber, TextField address, TextField parentName){
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
    }

    public static void openNewTransactionWindow(Label headerLabel, Pane headerPane, Pane newTransactionPane, Button doTransaction){
        headerLabel.setText("Do Your New Transaction By Entering Details!");
        headerPane.setBackground(new Background(new BackgroundFill(Color.rgb(113, 86, 221), CornerRadii.EMPTY, Insets.EMPTY)));
        newTransactionPane.toFront();
        doTransaction.setDisable(true);
    }

    public static void verifyOTP(TextField otp, Button doTransaction){
        String enteredOTP = otp.getText();
        if (OTP.verifyOTP(enteredOTP)) {
            doTransaction.setDisable(false);
            createAlert(Alert.AlertType.INFORMATION, "VERIFICATION SUCCESS", "Congratulations", "" +
                    "Your OTP has been verified successfully.");
        } else {
            createAlert(Alert.AlertType.WARNING, "ERROR", "Incorrect OTP", "Please enter correct OTP");
        }
    }

    private static void createAlert(Alert.AlertType type, String title, String headerText, String context) {
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
