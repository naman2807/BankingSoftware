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
    private ObservableList<Branch> branches;
    private SimpleStringProperty bankCode = new SimpleStringProperty("");

    public Bank(String name) {
        this.name.set(name);
        this.branches = FXCollections.observableArrayList();
        this.bankCode = bankCode;
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public ObservableList<Branch> getBranches() {
        return branches;
    }

    public void setBranches(ObservableList<Branch> branches) {
        this.branches = branches;
    }

    public String getBankCode() {
        return bankCode.get();
    }

    public SimpleStringProperty bankCodeProperty() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode.set(bankCode);
    }
}
