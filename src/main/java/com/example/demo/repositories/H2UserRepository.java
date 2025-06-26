package com.example.demo.repositories;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Profile("test")
@Repository
public class H2UserRepository implements AbstractRepository {

    @Override
    public String getUserDetails(String userId) {
        // Placeholder for actual H2 database retrieval logic
        return "H2 User details for userId: " + userId;
    }
}
