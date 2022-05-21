package com.example.milkaapp.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.GregorianCalendar;

@Getter
@Setter
@NoArgsConstructor
public class MonthDto {
    private Long id;
    private GregorianCalendar monthAndYear;
    private int month;
    private boolean done;
    private int countOfDays;
}
