package com.house.housemanager.controller;

import java.util.UUID;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.house.housemanager.exceptions.NotFoundException;
import com.house.housemanager.model.Task;
import com.house.housemanager.repository.TaskRepository;

@RestController
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/tasks")
    public Iterable<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @GetMapping("/tasks/{id}")
    public Task getTask(@PathVariable UUID id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    @PostMapping(path = "/tasks")
    public Task addNewtask(@RequestBody Task newTask) {
        return taskRepository.save(newTask);
    }

}