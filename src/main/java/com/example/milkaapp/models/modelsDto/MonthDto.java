package com.example.milkaapp.models.modelsDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.YearMonth;
import java.util.GregorianCalendar;

@Getter
@Setter
@NoArgsConstructor
public class MonthDto {
    private YearMonth date;
    private int year;
    private int month;
    private boolean done;
}
