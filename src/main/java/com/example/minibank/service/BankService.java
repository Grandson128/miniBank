package com.example.minibank.service;

import com.example.minibank.model.User;
import com.example.minibank.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class BankService {

    // Deposit money into the user account
    public Optional<User> deposit(Long userId, double amount) {
        User user = UserRepository.getUser(userId);
        if (user != null) {
            user.deposit(amount);
            UserRepository.updateUser(user);  // Save updated user to shared storage
            return Optional.of(user);
        }
        return Optional.empty();
    }

    // Withdraw money from the user account
    public Optional<User> withdraw(Long userId, double amount) {
        User user = UserRepository.getUser(userId);
        if (user != null && user.withdraw(amount)) {
            UserRepository.updateUser(user);  // Save updated user to shared storage
            return Optional.of(user);
        }
        return Optional.empty();
    }
}