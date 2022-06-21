package com.example.milkaapp.models.modelsDto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private String status;
    private String noteVisit;
    private String hairDresEnum;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime hourStartVisit;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime hourEndVisit;

}
