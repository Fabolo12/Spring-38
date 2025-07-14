package com.example.todo.controller;

import com.example.todo.bean.TaskBean;
import com.example.todo.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService service;

    @Autowired
    public TaskController(final TaskService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public TaskBean getTask(@PathVariable Long id) {
        return service.findTask(id);
    }

    @GetMapping
    public List<TaskBean> getPageTask(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "1") int size
    ) {
        return service.findTasks(page, size);
    }

    @PostMapping
    public TaskBean saveTask(@Valid @RequestBody TaskBean taskBean) {
        return service.createTask(taskBean);
    }

    @PutMapping
    public boolean updateTask(@Valid @RequestBody TaskBean taskBean) {
        return service.updateTask(taskBean);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        service.deleteTask(id);
        return ResponseEntity.accepted().build();
    }
}
