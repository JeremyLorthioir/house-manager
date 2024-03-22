package com.house.housemanager.userTask;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.house.housemanager.task.Task;

// This will be AUTO IMPLEMENTED by Spring into a Bean called TaskRepository
// CRUD refers Create, Read, Update, Delete
public interface UserTaskRepository extends CrudRepository<UserTask, UUID> {
    UserTask findFirstByTaskOrderByCreatedAtDesc(Task task);
}
