package com.example.milkaapp.services;

import com.example.milkaapp.models.Day;
import com.example.milkaapp.models.DayDto;
import com.example.milkaapp.models.Visit;
import com.example.milkaapp.repositories.DayRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;


@Service
public class DayService implements Converter<DayDto, Day> {
    private DayRepository dayRepository;

    public DayService(DayRepository dayRepository) {
        this.dayRepository = dayRepository;
    }

    public List<LocalTime> hoursMaker (int a, int b) {
        List<LocalTime> times = new ArrayList<>();
        for (int i = a; i < b; i++) {
            for (int j = 0; j < 60; j = j + 30) {
                LocalTime localTime = LocalTime.of(i, j);
                times.add(localTime);
            }
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

    public Day updateDayHourSet(@NotNull DayDto dayDto){
        Day day = dayRepository.findDayByDate(dayDto.getDate());
        day.setHourStartDay(dayDto.getHourStartDay());
        day.setHourEndDay(dayDto.getHourEndDay());
        day.setHoursSet(hoursMaker(day.getHourStartDay(),day.getHourEndDay()));
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
        day.setHoursSet(hoursMaker(source.getHourStartDay(), source.getHourEndDay()));
        return day;
    }
}
