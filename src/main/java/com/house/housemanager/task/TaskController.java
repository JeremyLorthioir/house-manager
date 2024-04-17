package com.house.housemanager.task;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.house.housemanager.enums.TaskType;
import com.house.housemanager.exceptions.NotFoundException;
import com.house.housemanager.recurrence.Recurrence;
import com.house.housemanager.recurrence.RecurrenceService;
import com.house.housemanager.user.User;
import com.house.housemanager.userTask.UserTask;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
public class TaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private RecurrenceService recurrenceService;

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/tasks")
    public Iterable<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/tasks/{id}")
    public Task getTask(@PathVariable UUID id) {
        return taskService.getTaskById(id);
    }

    @PostMapping(path = "/tasks")
    public Task addNewtask(@RequestBody TaskDTO newTaskDTO) {
        Recurrence recurrence = recurrenceService.getRecurrenceById(newTaskDTO.getRecurrenceId());
        
        Task newTask = new Task();
        newTask.setName(newTaskDTO.getName());
        newTask.setType(TaskType.valueOf(newTaskDTO.getType()));
        newTask.setRecurrence(recurrence);
        return taskRepository.save(newTask);
    }
}
