package com.example.milkaapp.models;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(of = {"id"})
@Entity
public class Hairdressing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int minutes;
    @ManyToOne
    private Visit visit;

}
