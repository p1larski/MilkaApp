package com.example.milkaapp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;


@Data
@EqualsAndHashCode(of = {"id"})
@Entity
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;
    private String noteVisit;
    private String hairDresEnum;
    private LocalTime hourStartVisit;
    private LocalTime hourEndVisit;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "day_id")
    private Day day;

    public Visit() {
    }

    public Visit(String status, String noteVisit) {
        this.status = status;
        this.noteVisit = noteVisit;
    }
}
