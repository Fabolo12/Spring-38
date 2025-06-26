package com.example.demo.services;

import com.example.demo.repositories.AbstractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {

    private AbstractRepository repository;

    @Autowired
    public UserService(final AbstractRepository repository) {
        this.repository = repository;
    }

    public String getUserDetails(String userId) {
        System.out.println(repository.getUserDetails(userId));
        // Placeholder for actual user retrieval logic
        return "User details for userId: " + userId;
    }

    public void updateUserDetails(@NonNull String userId, @Nullable String newDetails) {
        // Placeholder for actual user update logic
        System.out.println("Updating user " + userId + " with details: " + newDetails);
    }

    @Async()
    public void cronMethod() {
        System.out.println("Executing cron method at scheduled intervals");
    }
}
