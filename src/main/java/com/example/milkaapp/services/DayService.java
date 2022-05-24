package com.example.milkaapp.services;

import com.example.milkaapp.models.Day;
import com.example.milkaapp.models.DayDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;


@Component
public class DayService implements Converter<DayDto, Day> {

    public TreeSet<LocalTime> hoursMaker (int a, int b) {
        TreeSet<LocalTime> times = new TreeSet<>();
        for (int i = a; i < b; i++) {
            for (int j = 0; j < 60; j = j + 30) {
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
        day.setDate(source.getDate());
        day.setHourStartDay(source.getHourStartDay());
        day.setHourEndDay(source.getHourEndDay());
        day.setNote(source.getNote());
        day.setHoursSet(hoursMaker(source.getHourStartDay(), source.getHourEndDay()));
        return day;
    }
}
