package com.example.milkaapp.models;

import java.time.LocalTime;

public enum HairDres {

    CIĘCIE(1,0), KOLORYZACJA(0,30), PIELĘGNACJA(1,30);

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
