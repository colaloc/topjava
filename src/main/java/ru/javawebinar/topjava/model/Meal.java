package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Meal {
    private LocalDateTime dateTime;

    private String description;

    private int calories;

    private final int id;

    public Meal(int id, LocalDateTime dateTime, String description, int calories) {
        this.id = id;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public Meal(int id, String dateTime, String description, int calories) {
        this.id = id;
        int day = Integer.parseInt(dateTime.split(" ")[0].split("-")[0]);
        int month = Integer.parseInt(dateTime.split(" ")[0].split("-")[1]);
        int year = Integer.parseInt(dateTime.split(" ")[0].split("-")[2]);
        int hours = Integer.parseInt(dateTime.split(" ")[1].split(":")[0]);
        int minutes = Integer.parseInt(dateTime.split(" ")[1].split(":")[1]);
        this.dateTime = LocalDateTime.of(year, month, day, hours, minutes);
        this.description = description;
        this.calories = calories;
    }

    public int getId() {
        return id;
    }

    public void setMeal(String dateTime, String description, int calories) {
        int day = Integer.parseInt(dateTime.split(" ")[0].split("-")[0]);
        int month = Integer.parseInt(dateTime.split(" ")[0].split("-")[1]);
        int year = Integer.parseInt(dateTime.split(" ")[0].split("-")[2]);
        int hours = Integer.parseInt(dateTime.split(" ")[1].split(":")[0]);
        int minutes = Integer.parseInt(dateTime.split(" ")[1].split(":")[1]);
        this.dateTime = LocalDateTime.of(year, month, day, hours, minutes);
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }
}
