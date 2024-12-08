package com.example.minibank.controller;

import com.example.minibank.model.User;
import com.example.minibank.service.BankService;
import com.example.minibank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final BankService bankService;

    @Autowired
    public UserController(UserService userService, BankService bankService) {
        this.userService = userService;
        this.bankService = bankService;
    }

    // Get all active users
    @GetMapping
    public List<User> getAllActiveUsers() {
        return userService.getAllActiveUsers();
    }

    // Get all users
    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Add a new user
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    // Get a user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    // Delete a user by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (userService.deleteUser(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Activate a user
    @PatchMapping("/{id}/activate")
    public ResponseEntity<Void> activateUser(@PathVariable Long id) {
        if (userService.activateUser(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint for depositing money
    @PatchMapping("/{id}/deposit")
    public String deposit(@PathVariable Long id, @RequestParam double amount) {
        Optional<User> user = bankService.deposit(id, amount);
        if (user.isPresent()) {
            return "Deposited " + amount + " to user  " + id + ":" + user.get().getName() + ". New balance: " + user.get().getBalance();
        }
        return "User not found!";
    }

    // Endpoint for withdrawing money
    @PatchMapping("/{id}/withdraw")
    public String withdraw(@PathVariable Long id, @RequestParam double amount) {
        Optional<User> user = bankService.withdraw(id, amount);
        if (user.isPresent()) {
            return "Withdrew " + amount + " from user " + id + ":" + user.get().getName() + ". New balance: " + user.get().getBalance();
        }
        return "Insufficient funds or user not found!";
    }

}
