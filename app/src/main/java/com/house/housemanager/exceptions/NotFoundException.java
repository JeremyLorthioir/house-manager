package com.house.housemanager.exceptions;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotFoundException extends ResponseStatusException {
    public NotFoundException(Object id) {
        super(HttpStatus.NOT_FOUND, "Could not find record with id " + id);
    }
}
