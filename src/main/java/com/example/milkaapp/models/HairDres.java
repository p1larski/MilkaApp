package com.example.milkaapp.models;

import java.time.LocalTime;

public enum HairDres {

    CIĘCIE(0,40),
    KOLORYZACJA(2,30),
    PIELĘGNACJA(1,00),
    MODELOWANIE(1,30),
    PASEMKA(1,30),
    BALEJAŻ(1,30),
    ONDULACJA(3,00),
    PRZEDŁUŻANIE(2,00),;

    int hour;
    int minutes;


    HairDres(int hour, int minutes){
        this.hour = hour;
        this.minutes = minutes;
    }

    public int getMinutes (){
        return minutes;
    }

    public int getHour() {
        return hour;
    }
}
