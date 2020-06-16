package ru.javawebinar.topjava.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static <T extends Comparable<T>> boolean isBetweenHalfOpen(T lt, T start, T end) {
        return (start == null || lt.compareTo(start) >= 0) && (end == null || lt.compareTo(end) < 0);
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }

    public static LocalDate toLocalDate(String date, boolean start) {
        return start ?
                date.isEmpty() ? LocalDate.MIN : LocalDate.parse(date) :
                date.isEmpty() ? LocalDate.MAX : LocalDate.parse(date).plusDays(1);
    }

    public static LocalTime toLocalTime(String time, boolean start) {
        return start ?
                time.isEmpty() ? LocalTime.MIN : LocalTime.parse(time) :
                time.isEmpty() ? LocalTime.MAX : LocalTime.parse(time);
    }
}

