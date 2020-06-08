package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class DAOMemoryMeal implements DAOMeal {

    private final static List<Meal> meals = new CopyOnWriteArrayList<>();
    private final static AtomicInteger count = new AtomicInteger(0);

    public DAOMemoryMeal() {
        meals.add(new Meal(count.incrementAndGet(), LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
        meals.add(new Meal(count.incrementAndGet(), LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
        meals.add(new Meal(count.incrementAndGet(), LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
        meals.add(new Meal(count.incrementAndGet(), LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
        meals.add(new Meal(count.incrementAndGet(), LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
        meals.add(new Meal(count.incrementAndGet(), LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
        meals.add(new Meal(count.incrementAndGet(), LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
    }

    public void addMeal(String dateTime, String description, int calories) {
        meals.add(new Meal(count.incrementAndGet(), dateTime, description, calories));
    }

    public void deleteMeal(int mealId) {
        meals.remove(getMealById(mealId));
    }

    public void updateMeal(int mealId, String dateTime, String description, int calories) {
        getMealById(mealId).setMeal(dateTime, description, calories);
    }

    public List<Meal> getAllMeals() {
        return meals;
    }

    public Meal getMealById(int mealId) {
        for (Meal meal : meals)
            if (meal.getId() == mealId)
                return meal;
        return null;
    }
}
