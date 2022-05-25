package com.example.milkaapp.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;
import java.time.YearMonth;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Data
@EqualsAndHashCode(of = {"id"})
@Entity
public class Month {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private YearMonth date;
    private boolean done;

    public Month() {
    }

    public Month( boolean done, Set<Day> days) {
        this.done = done;
        this.days = days;
    }

    @OneToMany(mappedBy = "month", cascade = CascadeType.ALL)
    private Set<Day> days = new TreeSet<>();

    public void addDay (Day day){
        days.add(day);
        day.setMonth(this);
    }
}
