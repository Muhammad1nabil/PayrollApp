package com.example.muhammad.elitepayrollapp;

/**
 * Created by Muhammad on 15/06/2019.
 */

public class Employee {

    private int ID;
    private String name;
    private int salary;
    private int balance;
    private String NID;
    private int PaymentperDay;
    private String Address;
    private String Phone1;
    private String Phone2;
    private String Notes;

    public Employee(String name, int salary, int balance, String NID, int paymentperDay, String address, String phone1, String phone2) {
        this.name = name;
        this.salary = salary;
        this.balance = balance;
        this.NID = NID;
        this.PaymentperDay = paymentperDay;
        this.Address = address;
        this.Phone1 = phone1;
        this.Phone2 = phone2;
        this.Notes = "";
    }
    public Employee(String name, int salary, int balance, String NID, int paymentperDay, String address, String phone1, String phone2,
                    String Notes) {
        this.name = name;
        this.salary = salary;
        this.balance = balance;
        this.NID = NID;
        this.PaymentperDay = paymentperDay;
        this.Address = address;
        this.Phone1 = phone1;
        this.Phone2 = phone2;
        this.Notes = Notes;
    }

    public Employee(int id, String name, int salary, int balance, String NID, int paymentperDay, String address, String phone1, String phone2,
                    String Notes) {
        this.ID = id;
        this.name = name;
        this.salary = salary;
        this.balance = balance;
        this.NID = NID;
        this.PaymentperDay = paymentperDay;
        this.Address = address;
        this.Phone1 = phone1;
        this.Phone2 = phone2;
        this.Notes = Notes;
    }

    public String getNID() {
        return NID;
    }

    public void setNID(String NID) {
        this.NID = NID;
    }

    public int getPaymentperDay() {
        return PaymentperDay;
    }

    public void setPaymentperDay(int paymentperDay) {
        PaymentperDay = paymentperDay;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhone1() {
        return Phone1;
    }

    public void setPhone1(String phone1) {
        Phone1 = phone1;
    }

    public String getPhone2() {
        return Phone2;
    }

    public void setPhone2(String phone2) {
        Phone2 = phone2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", balance=" + balance +
                ", NID=" + NID +
                ", PaymentperDay=" + PaymentperDay +
                ", Address='" + Address + '\'' +
                ", Phone1='" + Phone1 + '\'' +
                ", Phone2='" + Phone2 + '\'' +
                ", Notes='" + Notes + '\'' +
                '}';
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }
}
