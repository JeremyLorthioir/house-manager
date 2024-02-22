package com.house.housemanager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.house.housemanager.enums.TaskType;

@RestController
public class TaskTypeController {

    @GetMapping("/task_types")
    public TaskType[] getAllTaskTypess() {
        return TaskType.values();
    }
}
