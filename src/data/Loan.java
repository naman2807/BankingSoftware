package data;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.Date;

/**
 * Created By: Naman Agarwal
 * User ID: naman2807
 * Package Name: data
 * Project Name: BankingApplication
 * Date: 28-05-2021
 */

public class Loan {
    private final SimpleStringProperty accountNumber = new SimpleStringProperty("");
    private final SimpleDoubleProperty loanAmount = new SimpleDoubleProperty(0.0);
    private final SimpleStringProperty loanType = new SimpleStringProperty("");
    private final SimpleObjectProperty<Date> dueDate = new SimpleObjectProperty<>();

    public Loan() {
    }

    public Loan(String accountNumber, double loanAmount, String loanType, Date dueDate){
        this.accountNumber.set(accountNumber);
        this.loanAmount.set(loanAmount);
        this.loanType.set(loanType);
        this.dueDate.set(dueDate);
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

    public double getLoanAmount() {
        return loanAmount.get();
    }

    public SimpleDoubleProperty loanAmountProperty() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount.set(loanAmount);
    }

    public String getLoanType() {
        return loanType.get();
    }

    public SimpleStringProperty loanTypeProperty() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType.set(loanType);
    }

    public Date getDueDate() {
        return dueDate.get();
    }

    public SimpleObjectProperty<Date> dueDateProperty() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate.set(dueDate);
    }
}
