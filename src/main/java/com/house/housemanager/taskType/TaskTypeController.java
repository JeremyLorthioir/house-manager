package com.house.housemanager.taskType;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.house.housemanager.enums.TaskType;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
public class TaskTypeController {

    @GetMapping("/task_types")
    public TaskType[] getAllTaskTypess() {
        return TaskType.values();
    }
}
