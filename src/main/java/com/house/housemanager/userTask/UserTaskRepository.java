package com.house.housemanager.userTask;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.house.housemanager.task.Task;

// This will be AUTO IMPLEMENTED by Spring into a Bean called TaskRepository
// CRUD refers Create, Read, Update, Delete
@Repository
public interface UserTaskRepository extends CrudRepository<UserTask, UUID> {
    UserTask findFirstByTaskOrderByCreatedAtDesc(Task task);
}
