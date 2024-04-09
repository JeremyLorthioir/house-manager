package com.house.housemanager.task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.house.housemanager.userTask.UserTask;
import com.house.housemanager.userTask.UserTaskRepository;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserTaskRepository userTaskRepository;

    private String getStatus(Task task) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        if (fmt.format(this.getDueDate(task)).equals(fmt.format(new Date()))) {
            return "pending";
        }

        return this.getDueDate(task).after(new Date()) ? "valid" : "expired";
    }

    private Date getDueDate(Task task) {
        UserTask userTask = userTaskRepository.findFirstByTaskOrderByCreatedAtDesc(task);

        Calendar c = Calendar.getInstance();
        c.setTime(userTask == null ? new Date() : userTask.getCreatedAt());
        c.add(Calendar.DATE, task.getRecurrence().getDaysBetweenFrequencies());

        return c.getTime();
    }

    public Iterable<Task> getAllTasks() {
        Iterable<Task> tasks = taskRepository.findAll();
        for (Task task : tasks) {
            task.setStatus(this.getStatus(task));
            task.setDueDate(this.getDueDate(task));
        }

        return tasks;
    }

    public Task getTaskById(UUID taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));
    }
}
