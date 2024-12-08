package com.example.minibank.service;

import com.example.minibank.model.Transaction;
import com.example.minibank.model.User;
import com.example.minibank.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;
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

    /**
     * Transfers money from one user to another.
     *
     * @param fromUserId The ID of the user initiating the transfer (from whom the money will be withdrawn).
     * @param toUserId The ID of the user receiving the money.
     * @param amount The amount of money to be transferred. Must be a positive value.
     * @return true  - if the transfer is successful
     *         false - if the transfer doesn't happen
     */
    public boolean transfer(Long fromUserId, Long toUserId, double amount){
        User fromUser = UserRepository.getUser(fromUserId);
        User toUser = UserRepository.getUser(toUserId);

        if(fromUser == null || toUser == null) return false;

        //Check if transfer is possible
        if(fromUser.withdraw(amount))
        {
            //Handle transaction registry
            if(!Objects.equals(fromUserId, toUserId)) {
                fromUser.addTransaction(new Transaction(fromUser.getName(), toUser.getName(), amount));
            }

            //Handle deposit in the lucky user
            deposit(toUserId, amount);
            toUser.addTransaction(new Transaction(fromUser.getName(), toUser.getName(), amount));

            // Save updated users to shared storage
            UserRepository.updateUser(fromUser);
            UserRepository.updateUser(toUser);

            return true;
        }

        return false;
    }

}