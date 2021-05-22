package data;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created By: Naman Agarwal
 * User ID: naman2807
 * Package Name: data
 * Project Name: BankingApplication
 * Date: 22-05-2021
 */

public class Bank {
    private SimpleStringProperty name = new SimpleStringProperty("");
    private ObservableList<Branch> branches = FXCollections.observableArrayList();
    private SimpleStringProperty bankCode = new SimpleStringProperty("");
}
