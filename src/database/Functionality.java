package database;

import gui.FirstWindow;
import gui.FirstWindowController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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
