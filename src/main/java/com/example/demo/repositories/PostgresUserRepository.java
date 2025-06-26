package com.example.demo.repositories;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Profile("!test")
@Repository
public class PostgresUserRepository implements AbstractRepository {
    @Override
    public String getUserDetails(String userId) {
        // Placeholder for actual PostgreSQL database retrieval logic
        return "Postgres User details for userId: " + userId;
    }
}
