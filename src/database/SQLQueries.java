package database;

/**
 * Created By: Naman Agarwal
 * User ID: naman2807
 * Package Name: database
 * Project Name: BankingApplication
 * Date: 22-05-2021
 */

public class SQLQueries {
    private static final String BANK_TABLE = "bank_table";
    private static final String BRANCH_TABLE = "branch_table";
    private static final String CUSTOMER_TABLE = "customer_table";
    private static final String TRANSACTIONS_TABLE = "transaction_table";
    private static final String BANK_CODE = "bank_code";
    private static final String BANK_NAME = "bank_name";
    private static final String BRANCH_CODE = "branch_code";
    private static final String BRANCH_NAME = "branch_name";
    private static final String ACCOUNT_NUMBER = "account_number";
    private static final String AMOUNT = "amount";
    private static final String TRANSACTION_DATE = "transaction_date";
    private static final String TRANSACTIONS_ID = "transactionID";
    private static final String TRANSACTION_TIME = "transaction_time";
    private static final String EMPLOYEE_TABLE = "employee_table";
    private static final String EMPLOYEE_ID = "userid";
    private static final String CUSTOMER_NAME = "name";
    private static final String CUSTOMER_AGE = "age";
    private static final String CUSTOMER_ADDRESS = "address";
    private static final String CUSTOMER_PARENT_NAME = "parentname";
    private static final String CUSTOMER_PHONE_NUMBER = "phone_number";
    private static final String CUSTOMER_ACCOUNT_NUMBER = "account_number";

    public static String addBankQuery(){
        return "INSERT INTO " + BANK_TABLE + " (" + BANK_NAME + ") VALUES(?)";
    }

    public static String addTransactionQuery(){
        return "INSERT INTO " + TRANSACTIONS_TABLE + " (" + ACCOUNT_NUMBER + ", " +
                AMOUNT + ", " + TRANSACTION_DATE + ", " + TRANSACTION_TIME + ") VALUES(?, ?, ?, ?)";
    }

    public static String addBranchQuery(){
        return "INSERT INTO " + BRANCH_TABLE + " VALUES(?, ?)";
    }

    public static String addCustomerQuery(){
        return "INSERT INTO " + CUSTOMER_TABLE + " VALUES(?, ?, ?, ?, ?, ?, ?)";
    }

    public static String findEmployeeQuery(){
        return "SELECT * FROM " + EMPLOYEE_TABLE;
    }

    public static String updateBalanceQuery(){
        return "UPDATE TABLE " + CUSTOMER_TABLE + " SET " + AMOUNT + " = ? WHERE " + ACCOUNT_NUMBER + " = ?";
    }
}
