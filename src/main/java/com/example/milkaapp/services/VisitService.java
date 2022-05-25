package com.example.milkaapp.services;

import com.example.milkaapp.models.Day;
import com.example.milkaapp.models.HairDres;
import com.example.milkaapp.models.Visit;
import com.example.milkaapp.models.VisitDto;
import com.example.milkaapp.repositories.DayRepository;
import lombok.SneakyThrows;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.function.Predicate;

import static java.time.temporal.ChronoUnit.HOURS;


@Component
public class VisitService implements Converter <VisitDto, Visit> {

    private DayRepository dayRepository;

    public VisitService(DayRepository dayRepository) {
        this.dayRepository = dayRepository;
    }

    private LocalTime dateFinish (VisitDto visitDto) {
        HairDres hairDres = HairDres.valueOf(visitDto.getHairDresEnum());
        LocalTime timeEnd = visitDto.getHourStartVisit();
        int hours = timeEnd.getHour() + hairDres.getHour();
        int minutes = timeEnd.getMinute() + hairDres.getMinutes();
        if(minutes > 60){
            int min = minutes-60;
            hours = hours+1;
            LocalTime localTime = LocalTime.of(hours, min);
            return localTime;
        }
            LocalTime localTime = LocalTime.of(hours, minutes);
        return localTime;
    }

    @Override
    public Visit convert(VisitDto source) {
        Visit visit = new Visit();
        visit.setStatus(source.getStatus());
        visit.setHairDresEnum(source.getHairDresEnum());
        visit.setHourStartVisit(source.getHourStartVisit());
        visit.setHourEndVisit(dateFinish(source));
        visit.setNoteVisit(source.getNoteVisit());
        visit.setDay(dayRepository.findDayByDate(source.getDate()));

        Day dayOfVisit = dayRepository.findDayByDate(source.getDate());
        List<LocalTime> freeHoursInDay = new ArrayList<>(dayOfVisit.getHoursSet());

        Predicate<LocalTime> isInRange = localTime ->
                   localTime.isAfter(visit.getHourStartVisit())
                && localTime.isBefore(visit.getHourEndVisit())
                || localTime.equals(visit.getHourStartVisit());

        freeHoursInDay.removeIf(isInRange);

        dayOfVisit.setHoursSet(/*new TreeSet<>(freeHoursInDay)*/freeHoursInDay);
        return visit;
    }
}
