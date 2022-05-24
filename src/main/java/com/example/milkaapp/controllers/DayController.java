package com.example.milkaapp.controllers;

import com.example.milkaapp.models.Day;
import com.example.milkaapp.models.DayDto;
import com.example.milkaapp.models.Month;
import com.example.milkaapp.models.MonthDto;
import com.example.milkaapp.repositories.DayRepository;
import com.example.milkaapp.services.DayService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
