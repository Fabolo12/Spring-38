package com.example.demo.repositories;

import com.example.demo.models.User;

import java.util.List;
import java.util.UUID;

public interface AbstractRepository {
    User getById(UUID userId);

    List<User> getAll();

    boolean updateAllEmails(String email);

    void delete(UUID userId);

    boolean create(User user);
}
