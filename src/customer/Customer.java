package customer;

/**
 * Created By: Naman Agarwal
 * User ID: naman2807
 * Package Name: customer
 * Project Name: BankingApplication
 * Date: 21-05-2021
 */

public class Customer {
    private String name;
    private int age;
    private String address;
    private String parentName;
    private String phoneNumber;

    public Customer(String name, int age, String address, String parentName, String phoneNumber) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.parentName = parentName;
        this.phoneNumber = phoneNumber;
    }

}
