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

    @ToString.Exclude
    @OneToMany(mappedBy = "day",cascade = CascadeType.ALL)
    private Set<Visit> visitSet;

    public Day() {
    }

    public Day(Long id, Month month, LocalDate date, int hourStartDay, int hourEndDay, String note, Set<Visit> visitSet) {
        this.id = id;
        this.date = date;
        this.month = month;
        this.hourStartDay = hourStartDay;
        this.hourEndDay = hourEndDay;
        this.note = note;
        this.visitSet = visitSet;
    }
    public void addVisit (Visit visit){
        visitSet.add(visit);
    }
}
