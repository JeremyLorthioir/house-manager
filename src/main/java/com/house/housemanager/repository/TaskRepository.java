package com.house.housemanager.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.house.housemanager.model.Task;

// This will be AUTO IMPLEMENTED by Spring into a Bean called TaskRepository
// CRUD refers Create, Read, Update, Delete
public interface TaskRepository extends CrudRepository<Task, UUID> {}
