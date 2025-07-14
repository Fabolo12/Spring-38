package com.example.todo.service;

import com.example.todo.bean.TaskBean;
import com.example.todo.model.Task;
import com.example.todo.repository.TaskRepository;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repository;

    private final ModelMapper modelMapper;

    @Autowired
    public TaskService(final TaskRepository repository, final ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public TaskBean findTask(final Long id) {
        return repository.findById(id)
                .map(task -> modelMapper.map(task, TaskBean.class))
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
    }

    public List<TaskBean> findTasks(final int page, final int size) {
        final Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id"));
        return repository.findAll(pageable).stream()
                .map(task -> modelMapper.map(task, TaskBean.class))
                .toList();
    }

    @Transactional
    public TaskBean createTask(final TaskBean taskBean) {
        Task task = modelMapper.map(taskBean, Task.class);
        final Task save = repository.save(task);
        return modelMapper.map(save, TaskBean.class);
    }

    @Transactional
    public boolean updateTask(final TaskBean taskBean) {
        final Task task = repository.findById(taskBean.getId())
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + taskBean.getId()));
        if (taskBean.getDescription() != null && !taskBean.getDescription().isEmpty()) {
            task.setDescription(taskBean.getDescription());
        }
        if (taskBean.getStatus() != null) {
            task.setStatus(taskBean.getStatus());
        }
        repository.save(task);
        return true;
    }

    @Transactional
    public void deleteTask(final Long id) {
        repository.deleteById(id);
    }
}
