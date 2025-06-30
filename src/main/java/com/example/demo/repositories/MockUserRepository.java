package com.example.demo.repositories;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Profile("!test")
@Repository
public class MockUserRepository {

    public String getUserDetails(String userId) {
        // Placeholder for actual PostgreSQL database retrieval logic
        return "Mock User details for userId: " + userId;
    }
}
