package com.house.housemanager.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.house.housemanager.model.Recurrence;

// This will be AUTO IMPLEMENTED by Spring into a Bean called RecurrenceRepository
// CRUD refers Create, Read, Update, Delete
public interface RecurrenceRepository extends CrudRepository<Recurrence, UUID> {
}
