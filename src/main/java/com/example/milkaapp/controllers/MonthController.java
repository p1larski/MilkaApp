package com.example.milkaapp.controllers;

import com.example.milkaapp.models.modelsDto.MonthDto;
import com.example.milkaapp.models.Month;
import com.example.milkaapp.services.MonthService;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;
import java.util.*;

@RestController
public class MonthController {

    private MonthService monthService;

    public MonthController(MonthService monthService) {
        this.monthService = monthService;
    }

    @GetMapping("/calendar")
    public List<Month> getCalendars() {
        return monthService.getMonths();
    }

    @PostMapping("/month/new/save")
    public Month saveMonth(@RequestBody MonthDto monthDto) {
        return monthService.addMonth(monthDto);
    }

    @DeleteMapping(value = {"/month/{yearAndMonth}"})
    public String deleteMonth(@PathVariable YearMonth yearAndMonth) {
        return monthService.deleteMonth(yearAndMonth);
    }
}

