package com.house.housemanager.userTask;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.house.housemanager.exceptions.NotFoundException;
import com.house.housemanager.task.Task;
import com.house.housemanager.task.TaskService;
import com.house.housemanager.user.User;
import com.house.housemanager.user.UserService;

@RestController
public class UserTaskController {
    @Autowired
    private UserTaskRepository userTaskRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

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
    public UserTask addNewUserTask(@RequestBody UserTaskDTO newUserTaskDTO) {
        User user = userService.getUserById(newUserTaskDTO.getUserId());
        Task task = taskService.getTaskById(newUserTaskDTO.getTaskId());

        UserTask newUserTask = new UserTask();
        newUserTask.setUser(user);
        newUserTask.setTask(task);

        return userTaskRepository.save(newUserTask);
    }
}
