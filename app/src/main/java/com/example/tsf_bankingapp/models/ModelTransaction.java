package com.example.tsf_bankingapp.models;

public class ModelTransaction {
    String from, to;
    int amount;

    public ModelTransaction() {
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public ModelTransaction(String from, String to, int amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }
}
