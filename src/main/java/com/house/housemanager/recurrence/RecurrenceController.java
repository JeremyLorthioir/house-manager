package com.house.housemanager.recurrence;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
public class RecurrenceController {
    @Autowired
    private RecurrenceRepository recurrenceRepository;

    @Autowired
    private RecurrenceService recurrenceService;

    @GetMapping("/recurrences")
    public Iterable<Recurrence> getAllRecurrences() {
        return recurrenceService.getAllRecurrences();
    }

    @GetMapping("/recurrences/{id}")
    public Recurrence getRecurrence(@PathVariable UUID id) {
        return recurrenceService.getRecurrenceById(id);
    }

    @PostMapping(path = "/recurrences")
    public Recurrence addNewRecurrence(@RequestBody Recurrence newRecurrence) {
        return recurrenceRepository.save(newRecurrence);
    }

}
