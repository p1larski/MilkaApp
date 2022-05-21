package com.example.milkaapp.controllers;

import com.example.milkaapp.models.MonthDto;
import com.example.milkaapp.models.Month;
import com.example.milkaapp.services.MonthService;
import com.example.milkaapp.repositories.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MonthController {
    private HairdressingRepository hairdressingRepository;
    private VisitRepository visitRepository;
    private MonthService monthService;
    private MonthRepository monthRepository;
    private ClientRepository clientRepository;
    private DayRepository dayRepository;

    public MonthController(HairdressingRepository hairdressingRepository,
                           VisitRepository visitRepository, MonthRepository monthRepository,
                           ClientRepository clientRepository, DayRepository dayRepository,
                           MonthService monthService) {
        this.hairdressingRepository = hairdressingRepository;
        this.visitRepository = visitRepository;
        this.monthRepository = monthRepository;
        this.clientRepository = clientRepository;
        this.dayRepository = dayRepository;
        this.monthService = monthService;
    }

    @GetMapping("/calendar")
    public List<Month> getCalendars() {
        return (List<Month>) monthRepository.findAll();
    }

    @PostMapping("/month/new/save")
    public ResponseEntity<Month> saveMonth(@RequestBody MonthDto monthDto) {
        Optional<Month> calendarOptional = Optional.ofNullable(monthRepository.getCalendarById(monthDto.getId()));
        Month month = monthService.convert(monthDto);
        if (!calendarOptional.isPresent()) {
            monthRepository.save(month);
            System.out.println(month);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(month);
    }

    @DeleteMapping(value = {"/month/{id}"})
    public ResponseEntity<Month> deleteMonth(@PathVariable Long id) {
        if (monthRepository.getCalendarById(id) != null) {
            Month month = monthRepository.getCalendarById(id);
            monthRepository.delete(month);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

