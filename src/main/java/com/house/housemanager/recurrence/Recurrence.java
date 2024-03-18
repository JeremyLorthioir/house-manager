package com.house.housemanager.recurrence;

import com.house.housemanager.enums.Period;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Recurrence {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Period period;

    @Column(nullable = false)
    private Integer frequency = 1;

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Period getPeriod() {
        return this.period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public Integer getFrequency() {
        return this.frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }
}