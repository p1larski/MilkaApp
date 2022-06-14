package com.example.milkaapp.services;

import com.example.milkaapp.models.Day;
import com.example.milkaapp.models.modelsDto.DayDto;
import com.example.milkaapp.models.Visit;
import com.example.milkaapp.repositories.DayRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.*;
import java.util.function.Predicate;


@Service
public class DayService implements Converter<DayDto, Day> {
    private DayRepository dayRepository;

    public DayService(DayRepository dayRepository) {
        this.dayRepository = dayRepository;
    }

    public List<LocalTime> hourSetMaker(float hourStart, float hourEnd) {
        List<LocalTime> times = new ArrayList<>();
            for (int i = (int) hourStart; i < hourEnd; i++) {
                for (int j = 0; j < 60; j = j + 30) {
                    LocalTime localTime = LocalTime.of(i, j);
                    times.add(localTime);
                }
            }
            if (hourStart % 1 != 0){
                times.remove(0);
            }
            if (hourEnd % 1 != 0){
                times.remove(times.size()-1);
            }

        return times;
    }

    public List<LocalTime> hoursReadyToBook (List<LocalTime> listOfEmptyHours, Visit visit){
        Predicate<LocalTime> isInRange = localTime ->
                localTime.isAfter(visit.getHourStartVisit())
                        && localTime.isBefore(visit.getHourEndVisit())
                        || localTime.equals(visit.getHourStartVisit());

        listOfEmptyHours.removeIf(isInRange);
        return listOfEmptyHours;
    }

    public Day updateDayHourSet(Long id, float hourStart, float hourEnd){
        Day day = dayRepository.findDayById(id);
        day.setHourStartDay(hourStart);
        day.setHourEndDay(hourEnd);
        day.setHoursSet(hourSetMaker(day.getHourStartDay(),day.getHourEndDay()));
        day.getVisitSet().stream()
                .forEach(visit -> day.setHoursSet(hoursReadyToBook(day.getHoursSet(), visit)));
        return dayRepository.save(day);
    }

    @Override
    public Day convert(DayDto source) {
        if (source == null) {
            return null;
        }
        final Day day = new Day();
        day.setDate(source.getDate());
        day.setHourStartDay(source.getHourStartDay());
        day.setHourEndDay(source.getHourEndDay());
        day.setNote(source.getNote());
        day.setHoursSet(hourSetMaker(source.getHourStartDay(), source.getHourEndDay()));
        return day;
    }

    public Day addDay(DayDto dayDto){
        Optional<Day> dayOptional = dayRepository.findByHourStartDay(dayDto.getHourStartDay());
        Day day = convert(dayDto);
        if (!dayOptional.isPresent()) {
            dayRepository.save(day);
        }
        return day;
    }

    public List<Day> getAllDays(){
        return (List<Day>) dayRepository.findAll();
    }
    public Day getDayById(Long id){
        return dayRepository.findDayById(id);
    }
    public String deleteDayById(Long id){
        dayRepository.delete(dayRepository.findDayById(id));
        return "Day deleted successfully";
    }
}
