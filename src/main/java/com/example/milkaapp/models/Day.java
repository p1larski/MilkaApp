package com.example.milkaapp.models;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.*;

@Data
@EqualsAndHashCode(of = "id")
@Entity
public class Day {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private int hourStart;
    private int hourEnd;
    private String note;
    @ElementCollection
    private Set<LocalTime> hoursSet;

    @ToString.Exclude
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "month_id")
    private Month month;

    @OneToMany
    private Set<Visit> visit;

    public Day() {
    }

    public Day(Long id,Month month,Date date, int hourStart, int hourEnd, String note, Set<Visit> visit) {
        this.id = id;
        this.date = date;
        this.month = month;
        this.hourStart = hourStart;
        this.hourEnd = hourEnd;
        this.note = note;
        this.visit = visit;
    }
}
