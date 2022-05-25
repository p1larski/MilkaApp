package com.example.milkaapp.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class DayDto {
    private LocalDate date;
    private int hourStartDay;
    private int hourEndDay;
    private String note;
    private List<LocalTime> hoursSet;
}
