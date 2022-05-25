package com.example.milkaapp.services;

import com.example.milkaapp.models.MonthDto;
import com.example.milkaapp.models.Month;
import com.example.milkaapp.models.Day;
import com.example.milkaapp.repositories.DayRepository;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class MonthService implements Converter<MonthDto, Month> {
    private DayRepository dayRepository;

    public MonthService(DayRepository dayRepository) {
        this.dayRepository = dayRepository;
    }
    public List<Month> monthsWithSortedDays(List<Month> months){
        List<Month> monthList = new ArrayList<>(months);
        List<Day> daysInMonth = new ArrayList<>();
        List<Month> monthsSortedDays = new ArrayList<>();
        monthList.stream().forEach(month -> {
            daysInMonth.clear();
            daysInMonth.addAll(month.getDays());
            Collections.sort(daysInMonth);
            Set<Day> daysSorted = new TreeSet<>();
            daysSorted.addAll(daysInMonth);
            month.setDays(daysSorted);
            monthsSortedDays.add(month);
        });
        return monthsSortedDays;
    }

    @Synchronized
    @Nullable
    @Override
    public Month convert(MonthDto source) {

        final Month month = new Month();

        YearMonth yearMonth = YearMonth.of(source.getYear(), source.getMonth());
        List<Day> daysList = new ArrayList<>();
        for (int i = 1; i <= yearMonth.getMonth().length(yearMonth.isLeapYear()); i++ ){
            Day day = new Day();
            day.setDate(LocalDate.of(yearMonth.getYear(),yearMonth.getMonth(),i));
            dayRepository.save(day);
            daysList.add(day);
        }
        TreeSet<Day> sortedSetOfDays = new TreeSet<>(daysList);
        sortedSetOfDays.stream().forEach(day -> month.addDay(day));
        month.setDate(yearMonth);
        month.setDays(sortedSetOfDays);
        return month;
    }
}
