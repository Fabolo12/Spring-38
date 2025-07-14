package com.example.todo.bean;

import com.example.todo.model.Status;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TaskBean {

    private Long id;

    @Size(min = 1, max = 100)
    private String description;

    private Status status;

    private LocalDateTime createdAt;
}
