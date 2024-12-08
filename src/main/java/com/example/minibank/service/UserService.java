package com.example.minibank.service;

import com.example.minibank.model.User;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UserService {
    private final Map<Long, User> userStorage = new HashMap<>();
    private Long currentId = 1L;

    // Retrieve all active users
    public List<User> getAllActiveUsers() {
        List<User> activeUsers = new ArrayList<>();
        for (User user : userStorage.values()) {
            if (user.isActive()) {
                activeUsers.add(user);
            }
        }
        return activeUsers;
    }

    // Retrieve all users, including inactive ones
    public List<User> getAllUsers() {
        return new ArrayList<>(userStorage.values());
    }

    // Add a new user
    public User saveUser(User user) {
        user.setId(currentId++);
        user.setActive(true);
        userStorage.put(user.getId(), user);
        return user;
    }

    // Get a user by ID
    public User getUserById(Long id) {
        return userStorage.get(id);
    }

    // Delete a user
    public boolean deleteUser(Long id) {
        User user = userStorage.get(id);
        if (user != null) {
            user.setActive(false);
            return true;
        }
        return false;
    }

    // Activate a user
    public boolean activateUser(Long id) {
        User user = userStorage.get(id);
        if (user != null) {
            user.setActive(true);
            return true;
        }
        return false;
    }
}