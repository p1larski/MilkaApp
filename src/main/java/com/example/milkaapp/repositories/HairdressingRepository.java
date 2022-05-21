package com.example.milkaapp.repositories;

import com.example.milkaapp.models.Hairdressing;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HairdressingRepository extends CrudRepository<Hairdressing, Long> {
}
