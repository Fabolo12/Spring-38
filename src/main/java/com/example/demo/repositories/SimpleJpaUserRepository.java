package com.example.demo.repositories;

import com.example.demo.models.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SimpleJpaUserRepository extends CrudRepository<User, UUID>, CriteriaApiUserRepository {

    @Modifying
    @Query("UPDATE User SET email = :email WHERE email IS NULL")
    int updateAllEmails(String email);

    List<User> findAllByEmailOrderByNameDesc(String email);
}
