package com.example.milkaapp.repositories;

import com.example.milkaapp.models.Month;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.YearMonth;
import java.util.Optional;

@Repository
public interface MonthRepository extends CrudRepository<Month, Long> {
    Optional<Month> getCalendarByDate(YearMonth monthAndYear);
}
