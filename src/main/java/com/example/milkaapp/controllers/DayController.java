package com.example.milkaapp.controllers;

import com.example.milkaapp.models.*;
import com.example.milkaapp.repositories.DayRepository;
import com.example.milkaapp.services.DayService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
public class DayController {

    private DayRepository dayRepository;
    private DayService dayService;

    public DayController(DayRepository dayRepository, DayService dayService) {
        this.dayRepository = dayRepository;
        this.dayService = dayService;
    }

    @PostMapping("/day/new/save")
    public ResponseEntity<Day> saveDay(@RequestBody DayDto dayDto) {
        Optional<Day> dayOptional = dayRepository.findByHourStartDay(dayDto.getHourStartDay());
        Day day = dayService.convert(dayDto);
        if (!dayOptional.isPresent()) {
            dayRepository.save(day);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(day);
    }

    @GetMapping("/days")
    public List<Day> getDays() {
        return (List<Day>) dayRepository.findAll();
    }

    @PutMapping("/day/{id}")
    public Day dayHourSet(@PathVariable Long id, @RequestBody DayDto dayDto){
        return dayService.updateDayHourSet(dayDto);
    }
}
