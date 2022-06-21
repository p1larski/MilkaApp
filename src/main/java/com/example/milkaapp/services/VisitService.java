package com.example.milkaapp.services;

import com.example.milkaapp.models.Day;
import com.example.milkaapp.models.HairDres;
import com.example.milkaapp.models.Visit;
import com.example.milkaapp.models.modelsDto.VisitDto;
import com.example.milkaapp.repositories.DayRepository;
import com.example.milkaapp.repositories.VisitRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;


@Component
public class VisitService implements Converter <VisitDto, Visit> {

    private DayRepository dayRepository;
    private DayService dayService;
    private VisitRepository visitRepository;

    public VisitService(DayRepository dayRepository, DayService dayService,
                        VisitRepository visitRepository) {
        this.dayRepository = dayRepository;
        this.dayService = dayService;
        this.visitRepository = visitRepository;
    }

    private LocalTime dateFinish (VisitDto visitDto) {
        HairDres hairDres = HairDres.valueOf(visitDto.getHairDresEnum());
        LocalTime timeEnd = visitDto.getHourStartVisit();
        int hours = timeEnd.getHour() + hairDres.getHour();
        int minutes = timeEnd.getMinute() + hairDres.getMinutes();
        if (minutes >= 60){
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
        dayOfVisit.setHoursSet(dayService.hoursReadyToBook(dayOfVisit.getHoursSet(), visit));
        return visit;
    }
    public Visit addVisit(VisitDto visitDto){
        Visit visit = convert(visitDto);
        Optional<Visit> visitOptional = Optional.ofNullable(visitRepository.getVisitByHourStartVisitAndDay(visitDto.getHourStartVisit(), dayRepository.findDayByDate(visitDto.getDate())));
        if (!visitOptional.isPresent()) {
            visitRepository.save(visit);
        }
        return visit;
    }
    public String deleteVisit(LocalDate date, LocalTime hourStart){
      visitRepository.delete(visitRepository.getVisitByHourStartVisitAndDay(hourStart, dayRepository.findDayByDate(date)));
        return "Pomyślnie usunięto zaplanowaną wizytę";
    }
}
