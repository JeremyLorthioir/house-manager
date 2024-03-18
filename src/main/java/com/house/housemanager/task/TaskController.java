package com.house.housemanager.task;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.house.housemanager.exceptions.NotFoundException;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/tasks")
    public Iterable<Task> getAllTasks() {
        Iterable<Task> tasks = taskRepository.findAll();

        return tasks;
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
