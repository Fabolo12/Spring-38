package com.example.demo.models;

import com.example.demo.aspects.Loggable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Setter
@Getter
@ToString
@Component
public class User {

    private UUID id;

    private String name;

    private String email;

    public String getName() {
        return name;
    }

    @Loggable(maxTime = 1)
    public void setName(final String name) {
        System.out.println("[User]: Setting name to " + name);
        this.name = name;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public void exception() {
        throw new RuntimeException("This is a test exception");
    }
}
