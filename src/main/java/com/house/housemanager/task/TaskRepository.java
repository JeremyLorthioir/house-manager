package com.house.housemanager.task;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called TaskRepository
// CRUD refers Create, Read, Update, Delete
public interface TaskRepository extends CrudRepository<Task, UUID> {
    List<Task> findByArchived(Boolean archived);
}
