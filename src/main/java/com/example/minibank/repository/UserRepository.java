package com.example.minibank.repository;

import com.example.minibank.model.User;
import java.util.HashMap;
import java.util.Map;

public class UserRepository {

    private static final Map<Long, User> users = new HashMap<>();

    // Add a user to the repository
    public synchronized static void addUser(User user) {
        users.put(user.getId(), user);
    }

    // Get a user by ID
    public synchronized static User getUser(Long id) {
        return users.get(id);
    }

    // Get all users
    public synchronized static Map<Long, User> getAllUsers() {
        return users;
    }

    // Remove a user
    public synchronized static void removeUser(Long id) {
        users.remove(id);
    }

    // Update user balance
    public synchronized static void updateUser(User user) {
        users.put(user.getId(), user);
    }
}