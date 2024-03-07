package com.house.housemanager.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.house.housemanager.model.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called TaskRepository
// CRUD refers Create, Read, Update, Delete
public interface UserRepository extends CrudRepository<User, UUID> {
}
