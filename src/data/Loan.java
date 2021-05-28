package data;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

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
}
