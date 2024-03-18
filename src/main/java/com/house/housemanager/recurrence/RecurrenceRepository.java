package com.house.housemanager.recurrence;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called RecurrenceRepository
// CRUD refers Create, Read, Update, Delete
public interface RecurrenceRepository extends CrudRepository<Recurrence, UUID> {
}
