package com.example.milkaapp.services;

import com.example.milkaapp.models.HairDres;
import com.example.milkaapp.models.Visit;
import com.example.milkaapp.models.VisitDto;
import lombok.SneakyThrows;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import java.time.LocalTime;



@Component
public class VisitService implements Converter <VisitDto, Visit> {


    private LocalTime dateFinish (VisitDto visitDto) {
        HairDres hairDres = HairDres.valueOf(visitDto.getHairDresEnum());
        LocalTime timeEnd = visitDto.getTimeStart();
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
    @SneakyThrows
    @Override
    public Visit convert(VisitDto source) {
        Visit visit = new Visit();

        /*visit.setHairdressing(source.getHairdressing());*/
        visit.setStatus(source.getStatus());
        visit.setHairDresEnum(source.getHairDresEnum());
        visit.setTimeStart(source.getTimeStart());
        visit.setTimeEnd(dateFinish(source));
        visit.setNoteVisit(source.getNoteVisit());
        return visit;
    }

}
