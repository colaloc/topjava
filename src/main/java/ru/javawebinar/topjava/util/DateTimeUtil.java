package ru.javawebinar.topjava.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public static <T extends Comparable<T>> boolean isBetweenInclusive(T lt, T start, T end) {
        return lt.compareTo(start) >= 0 && lt.compareTo(end) <= 0;
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }

    public static LocalDate toLocalDate(String date, boolean start) {
        return start ?
                date.equals("") ? LocalDate.MIN : LocalDate.parse(date, DATE_FORMATTER) :
                date.equals("") ? LocalDate.MAX : LocalDate.parse(date, DATE_FORMATTER);
    }

    public static LocalTime toLocalTime(String time, boolean start) {
        return start ?
                time.equals("") ? LocalTime.MIN : LocalTime.parse(time, TIME_FORMATTER) :
                time.equals("") ? LocalTime.MAX : LocalTime.parse(time, TIME_FORMATTER);
    }
}

