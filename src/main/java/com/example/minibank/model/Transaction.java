package com.example.minibank.model;

import java.time.LocalDateTime;

public class Transaction {

    private String nameFrom;
    private String nameTo;
    private double amount;
    private final LocalDateTime timestamp;

    public Transaction(String nameFrom, String nameTo, double amount) {
        this.nameFrom = nameFrom;
        this.nameTo = nameTo;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();  // Capture the current time when the transaction occurs
    }

    public String getNameFrom() {
        return nameFrom;
    }

    public void setNameFrom(String nameFrom) {
        this.nameFrom = nameFrom;
    }

    public String getNameTo() {
        return nameTo;
    }

    public void setNameTo(String nameTo) {
        this.nameTo = nameTo;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
