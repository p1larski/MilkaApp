package com.example.milkaapp.controllers;

import com.example.milkaapp.models.*;
import com.example.milkaapp.models.modelsDto.DayDto;
import com.example.milkaapp.repositories.DayRepository;
import com.example.milkaapp.services.DayService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class DayController {

    private DayService dayService;

    public DayController(DayService dayService) {
        this.dayService = dayService;
    }

    @PostMapping("/day/new/save")
    public Day saveDay(@RequestBody DayDto dayDto) {
       return dayService.addDay(dayDto);
    }

    @GetMapping("/days")
    public List<Day> getDays() {
        return dayService.getAllDays();
    }

    @PutMapping("/day/{id}")
    public Day updateDayHourSet(@PathVariable Long id, @RequestBody DayDto dayDto){
        return dayService.updateDayHourSet(id, dayDto.getHourStartDay(), dayDto.getHourEndDay());
    }

    @GetMapping("/day/{id}")
    public Day getDay(@PathVariable Long id){
        return dayService.getDayById(id);
    }
    @DeleteMapping("/day/{id}")
    public String deleteDayById(@PathVariable Long id){
        return dayService.deleteDayById(id);
    }
}
