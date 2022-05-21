package com.example.milkaapp.repositories;

import com.example.milkaapp.models.Month;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MonthRepository extends CrudRepository<Month, Long> {
    Optional<Month> getCalendarByMonth(String month);
    Month getCalendarById(Long id);
}
