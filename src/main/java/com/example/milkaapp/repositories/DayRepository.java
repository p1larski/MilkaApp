package com.example.milkaapp.repositories;

import com.example.milkaapp.models.Day;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DayRepository extends CrudRepository<Day, Long> {
    Optional<Day> findByHourStart(int ainrt);
}
