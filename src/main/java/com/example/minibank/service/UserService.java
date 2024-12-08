package com.example.minibank.service;

import com.example.minibank.model.User;
import com.example.minibank.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UserService {
    private Long currentId = 1L;

    // Retrieve all active users
    public List<User> getAllActiveUsers() {
        List<User> activeUsers = new ArrayList<>();
        // Access shared user data from UserRepository
        for (User user : UserRepository.getAllUsers().values()) {
            if (user.isActive()) {
                activeUsers.add(user);
            }
        }
        return activeUsers;
    }

    // Retrieve all users, including inactive ones
    public List<User> getAllUsers() {
        return new ArrayList<>(UserRepository.getAllUsers().values());
    }

    // Add a new user
    public User saveUser(User user) {
        user.setId(currentId++);
        user.setActive(true);
        UserRepository.addUser(user);
        return user;
    }

    // Get a user by ID
    public User getUserById(Long id) {
        return UserRepository.getUser(id);
    }

    // Delete a user
    public boolean deleteUser(Long id) {
        User user = UserRepository.getUser(id);
        if (user != null) {
            user.setActive(false);
            return true;
        }
        return false;
    }

    // Activate a user
    public boolean activateUser(Long id) {
        User user = UserRepository.getUser(id);
        if (user != null) {
            user.setActive(true);
            return true;
        }
        return false;
    }
}