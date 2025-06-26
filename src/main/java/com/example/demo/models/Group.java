package com.example.demo.models;

import com.example.demo.aspects.Loggable;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class Group {
    private String groupName;

    @SneakyThrows
    @Loggable(maxTime = 0)
    public String getGroupName() {
        TimeUnit.SECONDS.sleep(1);
        return groupName;
    }
}
