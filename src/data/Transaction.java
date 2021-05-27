package data;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Created By: Naman Agarwal
 * User ID: naman2807
 * Package Name: data
 * Project Name: BankingApplication
 * Date: 22-05-2021
 */

public final class Transaction {
    private final SimpleStringProperty transactionID = new SimpleStringProperty("");
    private final SimpleStringProperty accountNumber = new SimpleStringProperty("");
    private final SimpleDoubleProperty amount = new SimpleDoubleProperty();
    private final SimpleStringProperty operation = new SimpleStringProperty("");
    private final SimpleStringProperty transactionTime = new SimpleStringProperty("");
    private final SimpleStringProperty transactionDate = new SimpleStringProperty("");

    public String getAccountNumber() {
        return accountNumber.get();
    }

    public SimpleStringProperty accountNumberProperty() {
        return accountNumber;
    }

    public Transaction(String transactionID, String accountNumber, double amount, String transactionTime, String transactionDate, String operation) {
        this.accountNumber.set(accountNumber);
        this.amount.set(amount);
        this.transactionTime.set(transactionTime);
        this.transactionDate.set(transactionDate);
        this.transactionID.set(transactionID);
        this.operation.set(operation);
    }

    public String getOperation() {
        return operation.get();
    }

    public SimpleStringProperty operationProperty() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation.set(operation);
    }

    public Transaction(String accountNumber, double amount, String transactionTime, String transactionDate, String operation) {
        this.accountNumber.set(accountNumber);
        this.amount.set(amount);
        this.transactionTime.set(transactionTime);
        this.transactionDate.set(transactionDate);
        this.operation.set(operation);
    }

    public String getTransactionDate() {
        return transactionDate.get();
    }

    public SimpleStringProperty transactionDateProperty() {
        return transactionDate;
    }

    public double getAmount() {
        return amount.get();
    }

    public SimpleDoubleProperty amountProperty() {
        return amount;
    }

    public String getTransactionTime() {
        return transactionTime.get();
    }

    public SimpleStringProperty transactionTimeProperty() {
        return transactionTime;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "accountNumber=" + accountNumber +
                ", amount=" + amount +
                ", transactionTime=" + transactionTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(accountNumber, that.accountNumber) && Objects.equals(amount, that.amount) && Objects.equals(transactionTime, that.transactionTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber, amount, transactionTime);
    }
}
