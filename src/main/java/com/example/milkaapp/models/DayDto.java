package com.example.milkaapp.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.GregorianCalendar;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class DayDto {
    private GregorianCalendar date;
    private int hourStartDay;
    private int hourEndDay;
    private String note;
    private Set<LocalTime> hoursSet;
}
