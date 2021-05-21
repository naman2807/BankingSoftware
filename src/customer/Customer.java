package customer;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

/**
 * Created By: Naman Agarwal
 * User ID: naman2807
 * Package Name: customer
 * Project Name: BankingApplication
 * Date: 21-05-2021
 */

public class Customer {
    private final SimpleStringProperty name = new SimpleStringProperty("");
    private final SimpleIntegerProperty age = new SimpleIntegerProperty();
    private final SimpleStringProperty address = new SimpleStringProperty("");
    private final SimpleStringProperty parentName = new SimpleStringProperty("");
    private final SimpleStringProperty phoneNumber = new SimpleStringProperty("");
    private final SimpleStringProperty accountNumber = new SimpleStringProperty("");
    private ObservableList<String> transaction;

    public Customer(String name, int age, String address, String parentName, String phoneNumber) {
        this.name.set(name);
        this.age.set(age);
        this.address.set(address);
        this.parentName.set(parentName);
        this.phoneNumber.set(phoneNumber);
        this.transaction = FXCollections.observableArrayList();
    }

    public Customer() {
        this.transaction = FXCollections.observableArrayList();
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

    public int getAge() {
        return age.get();
    }

    public SimpleIntegerProperty ageProperty() {
        return age;
    }

    public void setAge(int age) {
        this.age.set(age);
    }

    public String getAddress() {
        return address.get();
    }

    public SimpleStringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getParentName() {
        return parentName.get();
    }

    public SimpleStringProperty parentNameProperty() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName.set(parentName);
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public SimpleStringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    public ObservableList<String> getTransaction() {
        return transaction;
    }

    public void setTransaction(ObservableList<String> transaction) {
        this.transaction = transaction;
    }

    public void addTransaction(String amount){
        this.transaction.add(amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return age == customer.age && Objects.equals(name, customer.name) && Objects.equals(address, customer.address) && Objects.equals(parentName, customer.parentName) && Objects.equals(phoneNumber, customer.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, address, parentName, phoneNumber);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", parentName='" + parentName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    private String generateAccountNumber(){
        return getPhoneNumber() + new Random(9);
    }

}
