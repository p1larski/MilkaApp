package com.example.milkaapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Calendar.HOUR;
import static java.util.Calendar.MINUTE;

/*@SpringBootApplication*/
public class MilkaAppApplication {

   /* public int compareTo(String s1, String s2){
        int result = s1.compareTo(s2);
        return result;
    };

    public static int takeHour (String time) {
        String[] timme = time.split ( ":" );
        int hour = Integer.parseInt(timme[0].trim());
        return hour;
    };
    public static int takeMinutes(String time) {
        String[] timme = time.split ( ":" );
        int min = Integer.parseInt(timme[1].trim());
        return min;
    };*/



    public static void main(String[] args) {
        /*SpringApplication.run(MilkaAppApplication.class, args);*/
        YearMonth yearMonth = YearMonth.of(1999, java.time.Month.FEBRUARY);
        System.out.println(yearMonth.getMonth().length(true));
        System.out.println(yearMonth.getMonth().getValue());
        GregorianCalendar dateMonthAndYear = new GregorianCalendar(2000,Calendar.FEBRUARY,1);
        System.out.println(dateMonthAndYear.getActualMaximum(Calendar.DAY_OF_MONTH));
    }
}
