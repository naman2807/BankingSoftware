package customer;

import java.util.ArrayList;
import java.util.Objects;

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
    private ArrayList<Double> transaction;

    public Customer(String name, int age, String address, String parentName, String phoneNumber) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.parentName = parentName;
        this.phoneNumber = phoneNumber;
        this.transaction = new ArrayList<>();
    }

    public Customer() {
        this.transaction = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
}
