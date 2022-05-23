package com.example.milkaapp.repositories;

import com.example.milkaapp.models.Visit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.Date;
import java.util.Optional;

@Repository
public interface VisitRepository extends CrudRepository<Visit, Long> {
    Optional<Visit> getVisitByNoteVisit(String noteVisit);
}
