package com.example.milkaapp.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Data
@EqualsAndHashCode(of = {"id"})
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastname;
    private String adress;
    private Long phoneNumber;
    @OneToMany
    private Set<Visit> visit;
    /*private Visit visit;*/

    public Client(){
    }

    public Client(Long id, String name, String lastname, String adress, Long phoneNumber) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.adress = adress;
        this.phoneNumber = phoneNumber;
    }
}
