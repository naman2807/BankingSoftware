package data;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Objects;
import java.util.Random;

/**
 * Created By: Naman Agarwal
 * User ID: naman2807
 * Package Name: data
 * Project Name: BankingApplication
 * Date: 21-05-2021
 */

public class Branch {
    private final SimpleStringProperty name = new SimpleStringProperty("");
    private final SimpleStringProperty branchCode = new SimpleStringProperty();
    private ObservableList<Customer> customers;

    public Branch(String name) {
        this.name.set(name);
        this.customers = FXCollections.observableArrayList();
        this.branchCode.set(generateBranchCode());
    }

    public Branch() {
        this.name.set("");
        this.customers = FXCollections.observableArrayList();
        this.branchCode.set(generateBranchCode());
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

    public String getBranchCode() {
        return branchCode.get();
    }

    public SimpleStringProperty branchCodeProperty() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode.set(branchCode);
    }

    public ObservableList<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(ObservableList<Customer> customers) {
        this.customers = customers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Branch branch = (Branch) o;
        return Objects.equals(name, branch.name) && Objects.equals(branchCode, branch.branchCode) && Objects.equals(customers, branch.customers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, branchCode, customers);
    }

    @Override
    public String toString() {
        return "Branch{" +
                "name=" + name +
                ", branchCode=" + branchCode +
                ", customers=" + customers +
                '}';
    }

    private String generateBranchCode(){
        return String.valueOf(new Random(100000));
    }

    public void addCustomerToBranch(Customer customer){
        customers.add(customer);
    }
}
