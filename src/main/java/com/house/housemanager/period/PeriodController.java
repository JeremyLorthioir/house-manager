package com.house.housemanager.period;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.house.housemanager.enums.Period;

@RestController
public class PeriodController {

    @GetMapping("/periods")
    public Period[] getAllPeriods() {
        return Period.values();
    }
}
