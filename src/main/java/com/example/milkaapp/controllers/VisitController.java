package com.example.milkaapp.controllers;

import com.example.milkaapp.models.Visit;
import com.example.milkaapp.models.modelsDto.VisitDto;
import com.example.milkaapp.services.VisitService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class VisitController {
    private VisitService visitService;

    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @PostMapping("/visit/new/save")
    public Visit saveVisit(@RequestBody VisitDto visitDto) {
       return visitService.addVisit(visitDto);
    }
    @DeleteMapping("/visit/{date}/{hourStart}")
    public String deleteVisit(@PathVariable("date")
                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,@PathVariable LocalTime hourStart){
        return visitService.deleteVisit(date, hourStart);
    }
}
