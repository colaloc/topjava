package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class DaoMemoryMeal implements DaoMeal {

    private final Map<Integer, Meal> meals = new ConcurrentHashMap<>();
    private final AtomicInteger countId = new AtomicInteger(0);

    public DaoMemoryMeal() {
        add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
        add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
        add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
        add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
        add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
        add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
        add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
    }

    synchronized public Meal add(Meal meal) {
        meal.setId(countId.incrementAndGet());
        meals.put(meal.getId(), meal);
        return meal;
    }

    public void delete(int mealId) {
        meals.remove(mealId);
    }

    synchronized public Meal update(Meal newMeal) {
        Meal meal = getById(newMeal.getId());
        meal.setDateTime(newMeal.getDateTime());
        meal.setDescription(newMeal.getDescription());
        meal.setCalories(newMeal.getCalories());
        return meal;
    }

    public List<Meal> getAll() {
        return new ArrayList<>(meals.values());
    }

    public Meal getById(int mealId) {
        return meals.get(mealId);
    }
}
