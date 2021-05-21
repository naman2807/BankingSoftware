package branch;

import customer.Customer;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Objects;

/**
 * Created By: Naman Agarwal
 * User ID: naman2807
 * Package Name: branch
 * Project Name: BankingApplication
 * Date: 21-05-2021
 */

public class Branch {
    private final SimpleStringProperty name = new SimpleStringProperty("");
    private final SimpleStringProperty branchCode = new SimpleStringProperty();
    private ObservableList<Customer> customers;

    public Branch(String name, String branchCode) {
        this.name.set(name);
        this.customers = FXCollections.observableArrayList();
        this.branchCode.set(branchCode);
    }

    public Branch() {
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
}
