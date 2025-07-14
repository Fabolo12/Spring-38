package com.example.demo.services;

import com.example.demo.models.User;
import com.example.demo.repositories.JpaUserRepository;
import com.example.demo.repositories.SimpleJpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private JpaUserRepository repository;

    @Autowired
    public UserService(final JpaUserRepository repository) {
        this.repository = repository;
    }

    public String getUserDetails(final String userId) {
        // Placeholder for actual user retrieval logic
        return "User details for userId: " + userId;
    }

    public void updateUserDetails(final @NonNull String userId, final @Nullable String newDetails) {
        // Placeholder for actual user update logic
        System.out.println("Updating user " + userId + " with details: " + newDetails);
    }

    public User findUserById(final UUID userId) {
        return repository.findById(userId).orElseThrow();
    }

    public Iterable<User> findAllUsers() {
        return repository.findAll();
    }

    @Transactional
    public boolean updateAllUserEmails(final String email) {
        return repository.updateAllEmails(email) != 0;
    }

    @Transactional
    public User createUser(final User user) {
        return repository.save(user);
    }

    public List<User> findAllByEmail(final String email) {
        return repository.findAllByEmailOrderByNameDesc(email);
    }

    public List<User> getByNameGreaterThan(final int length) {
        return repository.getAllUserWithNameLengthGreaterThan(length);
    }

    public Page<User> getWithSortAndPage(
            final int page,
            final int size,
            final String sortBy,
            final String sortDirection
    ) {
        final Pageable pageable;
        if ("desc".equalsIgnoreCase(sortDirection)) {
            pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());
        } else {
            pageable = PageRequest.of(page, size, Sort.by(sortBy));
        }

        return repository.findAll(pageable);
    }
}
