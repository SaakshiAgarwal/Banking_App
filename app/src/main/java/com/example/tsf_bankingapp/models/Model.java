package com.example.tsf_bankingapp.models;

public class Model {
    String name, email;
    int balance;
    String accountnumber;

    public Model() {
    }

    public String getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(String accountnumber) {
        this.accountnumber = accountnumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Model(String name, String email, int balance, String accountnumber) {
        this.name = name;
        this.email = email;
        this.balance = balance;
        this.accountnumber = accountnumber;
    }
}
