package com.example.milkaapp.services;

import com.example.milkaapp.models.MonthDto;
import com.example.milkaapp.models.Month;
import com.example.milkaapp.models.Day;
import com.example.milkaapp.repositories.MonthRepository;
import com.example.milkaapp.repositories.DayRepository;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.time.YearMonth;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

@Component
public class MonthService implements Converter<MonthDto, Month> {
    private DayRepository dayRepository;

    public MonthService(DayRepository dayRepository) {
        this.dayRepository = dayRepository;
    }

    @Synchronized
    @Nullable
    @Override
    public Month convert(MonthDto source) {

        final Month month = new Month();
        java.util.Calendar calendar1 = java.util.Calendar.getInstance();
        int year = calendar1.get(java.util.Calendar.YEAR);
        GregorianCalendar dateMonthAndYear = new GregorianCalendar(year,source.getMonth(),0);
        YearMonth yearMonth = YearMonth.of(1999, java.time.Month.FEBRUARY);
        yearMonth.getMonth().length(false);
        Set<Day> daySet = new HashSet<>();
        for (int i = 1; i <= source.getCountOfDays(); i++ ){
            Day day = new Day();
            dayRepository.save(day);
            daySet.add(day);
        }
        month.setDays(daySet);
        month.setMonth(source.getMonth());
        month.setYear(year);
        month.setCountOfDays(source.getCountOfDays());
        return month;
    }
}
