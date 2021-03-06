package data;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
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

public class Customer {
    private final SimpleStringProperty name = new SimpleStringProperty("");
    private final SimpleIntegerProperty age = new SimpleIntegerProperty();
    private final SimpleStringProperty address = new SimpleStringProperty("");
    private final SimpleStringProperty parentName = new SimpleStringProperty("");
    private final SimpleStringProperty phoneNumber = new SimpleStringProperty("");
    private final SimpleStringProperty accountNumber = new SimpleStringProperty("");
    private final SimpleDoubleProperty amount = new SimpleDoubleProperty(500.0);
    private ObservableList<Transaction> transactionData;

    public Customer(String name, int age, String address, String parentName, String phoneNumber) {
        this.name.set(name);
        this.age.set(age);
        this.address.set(address);
        this.parentName.set(parentName);
        this.phoneNumber.set(phoneNumber);
        this.transactionData = FXCollections.observableArrayList();
        this.accountNumber.set(generateAccountNumber());
    }

    public Customer(String name, int age, String address, String parentName, String phoneNumber, String accountNumber, double amount) {
        this.name.set(name);
        this.age.set(age);
        this.address.set(address);
        this.parentName.set(parentName);
        this.phoneNumber.set(phoneNumber);
        this.transactionData = FXCollections.observableArrayList();
        this.accountNumber.set(accountNumber);
        this.amount.set(amount);
    }


    public Customer() {
        this.transactionData = FXCollections.observableArrayList();
        this.accountNumber.set(generateAccountNumber());
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

    public String getAccountNumber() {
        return accountNumber.get();
    }

    public SimpleStringProperty accountNumberProperty() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber.set(accountNumber);
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

    public ObservableList<Transaction> getTransaction() {
        return transactionData;
    }

    public void setTransaction(ObservableList<Transaction> transaction) {
        this.transactionData = transaction;
    }

    public double getAmount() {
        return amount.get();
    }

    public SimpleDoubleProperty amountProperty() {
        return amount;
    }

    public void setAmount(double amount) {
            this.amount.set(amount);
    }

    public double addAmount(double amount){
        return this.amount.get() + amount;
    }

    public double deductAmount(double amount){
        if(checkMinimumAccountBalance(amount)){
            return this.amount.get() - amount;
        }else {
            return this.amount.get();

        }
    }

    private boolean checkMinimumAccountBalance(double amount){
        return amount < this.amount.get();
    }

    public void addTransaction(Transaction transaction){
        this.transactionData.add(transaction);
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
        return getPhoneNumber() + new Random().nextInt(100);
    }

}
