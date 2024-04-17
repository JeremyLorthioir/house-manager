package com.house.housemanager.recurrence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RecurrenceService {

    @Autowired
    private RecurrenceRepository recurrenceRepository;

    public Iterable<Recurrence> getAllRecurrences() {
        return recurrenceRepository.findAll();
    }

    public Recurrence getRecurrenceById(UUID recurrenceId) {
        return recurrenceRepository.findById(recurrenceId)
                .orElseThrow(() -> new IllegalArgumentException("Recurrence not found"));
    }
}
