package com.example.minibank.model;

public class User {
    private Long id;
    private String name;
    private String email;
    private boolean active;
    private double balance;

    // Constructors
    public User() {
        this.active = true;
    }

    public User(Long id, String name, String email, double balance) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.active = true;
        this.balance = balance;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    // Deposit money to account
    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
        }
    }

    // Withdraw money from account
    public boolean withdraw(double amount) {
        if (amount > 0 && this.balance >= amount) {
            this.balance -= amount;
            return true;
        }
        return false;
    }

    public double getBalance() {
        return balance;
    }
}
