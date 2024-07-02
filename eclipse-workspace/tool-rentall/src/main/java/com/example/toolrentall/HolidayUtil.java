package com.example.toolrentall;

import java.time.LocalDate;

public class HolidayUtil {

    public static boolean isHoliday(LocalDate date) {
        int year = date.getYear();

       
        LocalDate independenceDay = LocalDate.of(year, 7, 4);
        if (independenceDay.getDayOfWeek().getValue() == 6) { 
            independenceDay = independenceDay.minusDays(1);
        } else if (independenceDay.getDayOfWeek().getValue() == 7) { 
            independenceDay = independenceDay.plusDays(1);
        }

       
        LocalDate laborDay = LocalDate.of(year, 9, 1);
        while (laborDay.getDayOfWeek().getValue() != 1) {
            laborDay = laborDay.plusDays(1);
        }

        return date.equals(independenceDay) || date.equals(laborDay);
    }
}
