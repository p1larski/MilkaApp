package com.example.milkaapp.models;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Data
@EqualsAndHashCode(of = "id")
@Entity
public class Day {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private int hourStartDay;
    private int hourEndDay;
    private String note;
    @ElementCollection
    private Set<LocalTime> hoursSet;

    @JsonBackReference
    @ToString.Exclude
    @ManyToOne
    private Month month;

    @OneToMany
    private Set<Visit> visit;

    public Day() {
    }

    public Day(Long id, Month month, LocalDate date, int hourStartDay, int hourEndDay, String note, Set<Visit> visit) {
        this.id = id;
        this.date = date;
        this.month = month;
        this.hourStartDay = hourStartDay;
        this.hourEndDay = hourEndDay;
        this.note = note;
        this.visit = visit;
    }
}
