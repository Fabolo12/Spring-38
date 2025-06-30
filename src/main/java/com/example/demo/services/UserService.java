package com.example.demo.services;

import com.example.demo.models.User;
import com.example.demo.repositories.AbstractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private AbstractRepository repository;

    @Autowired
    public UserService(final AbstractRepository repository) {
        this.repository = repository;
    }

    public String getUserDetails(String userId) {
        // Placeholder for actual user retrieval logic
        return "User details for userId: " + userId;
    }

    public void updateUserDetails(@NonNull String userId, @Nullable String newDetails) {
        // Placeholder for actual user update logic
        System.out.println("Updating user " + userId + " with details: " + newDetails);
    }

    public User findUserById(UUID userId) {
        return repository.getById(userId);
    }

    public List<User> findAllUsers() {
        return repository.getAll();
    }

    public boolean updateAllUserEmails(String email) {
        return repository.updateAllEmails(email);
    }

    public boolean createUser(User user) {
        return repository.create(user);
    }
}
