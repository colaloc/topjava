package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface DaoMeal {
    Meal add(Meal meal);

    void delete(int mealId);

    Meal update(int mealId, Meal meal);

    List<Meal> getAll();

    Meal getById(int mealId);
}
