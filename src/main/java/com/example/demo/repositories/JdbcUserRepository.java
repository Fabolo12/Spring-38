package com.example.demo.repositories;

import com.example.demo.models.User;
import com.example.demo.repositories.mappers.UserMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class JdbcUserRepository implements AbstractRepository {

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final UserMapper userMapper;

    private final SimpleJdbcInsert simpleJdbcInsert;

    public JdbcUserRepository(
            final JdbcTemplate jdbcTemplate,
            final NamedParameterJdbcTemplate namedParameterJdbcTemplate,
            final UserMapper userMapper,
            final DataSource dataSource
    ) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.userMapper = userMapper;
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("clients")
                .usingColumns("id", "name", "email");
    }

    @Override
    public User getById(final UUID userId) {
        return jdbcTemplate.queryForObject("SELECT * FROM clients WHERE id = ?",
                new Object[]{userId}, (rs, rowNum) -> {
                    User user = new User();
                    user.setId(UUID.fromString(rs.getString("id")));
                    user.setName(rs.getString("name"));
                    user.setEmail(rs.getString("email"));
                    return user;
                });
    }

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query("SELECT * FROM clients", userMapper);
    }

    @Override
    public boolean updateAllEmails(final String email) {
        final SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("email", email);
        return namedParameterJdbcTemplate.update(
                "UPDATE clients SET email = :email WHERE email IS NULL",
                namedParameters
        ) > 0;
    }

    @Override
    public void delete(final UUID userId) {
        // TODO: Implement delete logic
    }

    @Override
    public boolean create(final User user) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", user.getId());
        parameters.put("name", user.getName());
        parameters.put("email", user.getEmail());

        return simpleJdbcInsert.execute(parameters) > 0;
    }
}
