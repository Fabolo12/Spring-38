package com.example.demo.repositories;

import com.example.demo.models.User;

import java.util.List;

public interface CriteriaApiUserRepository {
    List<User> getAllUserWithNameLengthGreaterThan(int length);
}
