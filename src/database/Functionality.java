package database;

import gui.FirstWindow;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 * Created By: Naman Agarwal
 * User ID: naman2807
 * Package Name: database
 * Project Name: BankingApplication
 * Date: 30-05-2021
 */

public final class Functionality {

    public static void login(TextField userID, TextField password){
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

    private static void startNewWindow(){

    }
}
