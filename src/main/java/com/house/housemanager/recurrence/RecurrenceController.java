package com.house.housemanager.recurrence;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.house.housemanager.exceptions.NotFoundException;

@RestController
public class RecurrenceController {
    @Autowired
    private RecurrenceRepository recurrenceRepository;

    @GetMapping("/recurrences")
    public Iterable<Recurrence> getAllRecurrences() {
        return recurrenceRepository.findAll();
    }

    @GetMapping("/recurrences/{id}")
    public Recurrence getRecurrence(@PathVariable UUID id) {
        return recurrenceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    @PostMapping(path = "/recurrences")
    public Recurrence addNewRecurrence(@RequestBody Recurrence newRecurrence) {
        return recurrenceRepository.save(newRecurrence);
    }

}
