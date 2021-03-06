package data;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Objects;

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
    private SimpleStringProperty bankCode = new SimpleStringProperty();

    public Bank(String name, String bankCode, ObservableList<Branch> branches) {
        this.name.set(name);
        this.branches = FXCollections.observableArrayList(branches);
        this.bankCode.set(bankCode);
    }

    public Bank(String name) {
        this.name.set(name);
    }

    public Bank(String name, String bankCode){
        this.name.set(name);
        this.bankCode.set(bankCode);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return Objects.equals(name, bank.name) && Objects.equals(branches, bank.branches) && Objects.equals(bankCode, bank.bankCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, branches, bankCode);
    }

    @Override
    public String toString() {
        return "Bank{" +
                "name=" + name +
                ", branches=" + branches +
                ", bankCode=" + bankCode +
                '}';
    }
}
