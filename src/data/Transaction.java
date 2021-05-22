package data;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDateTime;

/**
 * Created By: Naman Agarwal
 * User ID: naman2807
 * Package Name: data
 * Project Name: BankingApplication
 * Date: 22-05-2021
 */

public class Transaction {
    private SimpleDoubleProperty amount = new SimpleDoubleProperty();
    private SimpleStringProperty transactionTime = new SimpleStringProperty("");

    public Transaction(double amount, String transactionTime) {
        this.amount.set(amount);
        this.transactionTime.set(transactionTime);
    }
}
