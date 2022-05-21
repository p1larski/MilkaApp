package com.example.milkaapp.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class DayDto {
    private Date date;
    private int hourStart;
    private int hourEnd;
    private String note;
    private Set<LocalTime> hoursSet;
}
