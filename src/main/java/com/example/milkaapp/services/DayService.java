package com.example.milkaapp.services;

import com.example.milkaapp.models.Day;
import com.example.milkaapp.models.DayDto;
import com.example.milkaapp.models.Month;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;


@Component
public class DayService implements Converter<DayDto, Day> {

    public Set<LocalTime> hoursMaker (int a, int b) {
        Set<LocalTime> times = new HashSet<>();
        for (int i = a; i < b; i++) {
            for (int j = 0; j < 60; j = j + 15) {
                LocalTime localTime = LocalTime.of(i, j);
                times.add(localTime);
            }
        }
        return times;
    }

    @Override
    public Day convert(DayDto source) {
        if (source == null) {
            return null;
        }
        final Day day = new Day();
        day.setHourStart(source.getHourStart());
        day.setHourEnd(source.getHourEnd());
        day.setNote(source.getNote());
        day.setHoursSet(hoursMaker(source.getHourStart(), source.getHourEnd()));
        return day;
    }
}
