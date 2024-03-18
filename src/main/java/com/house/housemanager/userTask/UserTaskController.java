package com.house.housemanager.userTask;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.house.housemanager.exceptions.NotFoundException;

@RestController
public class UserTaskController {
    @Autowired
    private UserTaskRepository userTaskRepository;

    @GetMapping("/user_tasks")
    public Iterable<UserTask> getAllUserTasks() {
        return userTaskRepository.findAll();
    }

    @GetMapping("/user_tasks/{id}")
    public UserTask getUserTask(@PathVariable UUID id) {
        return userTaskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    @PostMapping(path = "/user_tasks")
    public UserTask addNewUserTask(@RequestBody UserTask newUserTask) {
        return userTaskRepository.save(newUserTask);
    }
}
