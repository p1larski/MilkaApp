package com.example.milkaapp.models.modelsDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DayDto {
    private LocalDate date;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime hourStartDay;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime hourEndDay;
    private String note;
    private List<LocalTime> hoursSet;
}
