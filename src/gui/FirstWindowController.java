package gui;


import database.DataBaseConnection;
import database.DataSource;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.TouchPoint;
import javafx.stage.Stage;

import java.io.IOException;
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
    public void handleButtonClicked(ActionEvent event){
        if(event.getSource() == login){
            String user = userID.getText();
            String pass = password.getText();
            if(DataSource.findEmployee(DataBaseConnection.getConnection(), user, pass)){

            }
        }
    }

    private void createAlert(Alert.AlertType type, String title, String headerText, String context){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(context);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            alert.close();
        }
    }

    private void startNewWindow() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("firstwindow.fxml")));
    }

}
