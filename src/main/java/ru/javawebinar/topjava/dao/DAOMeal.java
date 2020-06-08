package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface DAOMeal {
    void addMeal(String dateTime, String description, int calories);

    void deleteMeal(int mealId);

    void updateMeal(int mealId, String dateTime, String description, int calories);

    List<Meal> getAllMeals();

    Meal getMealById(int mealId);
}
