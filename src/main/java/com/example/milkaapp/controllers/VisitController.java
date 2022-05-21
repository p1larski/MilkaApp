package com.example.milkaapp.controllers;

import com.example.milkaapp.models.Visit;
import com.example.milkaapp.models.VisitDto;
import com.example.milkaapp.repositories.*;
import com.example.milkaapp.services.VisitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Optional;

@RestController
public class VisitController {
    private VisitRepository visitRepository;
    private VisitService visitService;

    public VisitController(VisitRepository visitRepository,
                           VisitService visitService) {
        this.visitRepository = visitRepository;
        this.visitService = visitService;
    }

    @PostMapping("/visit/new/save")
    public ResponseEntity<Visit> saveVisit(@RequestBody VisitDto visitDto) throws ParseException {

        Visit visit = visitService.convert(visitDto);
        Optional<Visit> visitOptional = visitRepository.getVisitByTimeStart(visitDto.getTimeStart());
        if (!visitOptional.isPresent()) {
            visitRepository.save(visit);
            return ResponseEntity.status(HttpStatus.CREATED).body(visit);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
