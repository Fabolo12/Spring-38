package com.example.demo.models;

import com.example.demo.aspects.Loggable;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class User {
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
