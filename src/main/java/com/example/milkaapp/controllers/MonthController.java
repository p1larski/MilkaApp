package com.example.milkaapp.controllers;

import com.example.milkaapp.models.MonthDto;
import com.example.milkaapp.models.Month;
import com.example.milkaapp.services.MonthService;
import com.example.milkaapp.repositories.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;
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
        Optional<Month> calendarOptional = monthRepository.getCalendarByDate(monthDto.getDate());
        Month month = monthService.convert(monthDto);
        if (!calendarOptional.isPresent()) {
            monthRepository.save(month);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(month);
    }

    @DeleteMapping(value = {"/month/{yearAndMonth}"})
    public ResponseEntity<Month> deleteMonth(@PathVariable YearMonth yearAndMonth) {
        Optional<Month> monthOptional = monthRepository.getCalendarByDate(yearAndMonth);
        if (monthOptional != null) {
            monthRepository.delete(monthOptional.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

