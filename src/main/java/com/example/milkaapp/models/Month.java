package com.example.milkaapp.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import javax.persistence.*;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(of = {"id"})
@Entity
public class Month {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private GregorianCalendar monthAndYear;
    private int month;
    private int year;
    private boolean done;
    private int countOfDays;

    public Month() {
    }

    public Month(int month, int year, boolean done, int countOfDays, Set<Day> days) {
        this.month = month;
        this.year = year;
        this.done = done;
        this.countOfDays = countOfDays;
        this.days = days;
    }

    @OneToMany(mappedBy = "month")
    private Set<Day> days = new HashSet<>();

    public void addDay (Day day){
        days.add(day);
        day.setMonth(this);
    }

}
