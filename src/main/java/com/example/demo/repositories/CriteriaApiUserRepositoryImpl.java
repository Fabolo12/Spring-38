package com.example.demo.repositories;

import com.example.demo.models.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CriteriaApiUserRepositoryImpl implements  CriteriaApiUserRepository {

    private final EntityManager entityManager;

    @Autowired
    public CriteriaApiUserRepositoryImpl(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<User> getAllUserWithNameLengthGreaterThan(final int length) {
        /*return entityManager.createQuery("SELECT u FROM User u WHERE LENGTH(u.name) > :length", User.class)
                .setParameter("length", length)
                .getResultList();*/

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root).where(cb.gt(cb.length(root.get("name")), length));

        return entityManager.createQuery(query).getResultList();
    }
}
