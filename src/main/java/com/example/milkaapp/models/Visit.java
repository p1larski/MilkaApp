package com.example.milkaapp.models;

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
    private LocalTime timeStart;
    private LocalTime timeEnd;

    /*@ManyToOne
    private Client client;*/
    
    @ManyToOne
    private Day day;

    public Visit() {
    }

    public Visit(String status, String noteVisit/* Date date, Date dateTime*/) {
        this.status = status;
        this.noteVisit = noteVisit;
        /*this.date = date;
        this.dateTime = dateTime;*/
    }
}
