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
    private final SimpleDoubleProperty amount = new SimpleDoubleProperty();
    private final SimpleStringProperty transactionTime = new SimpleStringProperty("");

    public Transaction(double amount, String transactionTime) {
        this.amount.set(amount);
        this.transactionTime.set(transactionTime);
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(amount, that.amount) && Objects.equals(transactionTime, that.transactionTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, transactionTime);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "amount=" + amount +
                ", transactionTime=" + transactionTime +
                '}';
    }
}
