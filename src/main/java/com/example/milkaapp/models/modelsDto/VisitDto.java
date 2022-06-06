package com.example.milkaapp.models.modelsDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class VisitDto {
    private LocalDate date;
    private String status;
    private String noteVisit;
    private String hairDresEnum;
    private LocalTime hourStartVisit;
    private LocalTime hourEndVisit;

}
