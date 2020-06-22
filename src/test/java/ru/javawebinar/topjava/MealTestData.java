package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int NOT_FOUND = 10;
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;

    public static final Meal MEAL_NEW = new Meal(100004, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "новая еда", 500);
    public static final Meal MEAL_FOR_DELETE = new Meal(2, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "еда для удаления", 1000);
    public static final Meal MEAL_BEFORE_UPDATE = new Meal(3, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "еда до обновления", 500);
    public static final Meal MEAL_AFTER_UPDATE = new Meal(3, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "еда после обновления", 88);
    public static final Meal MEAL_FOR_GET = new Meal(4, LocalDateTime.of(2020, Month.JANUARY, 30, 15, 15), "еда для получения", 666);

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        Arrays.sort(expected, Comparator.comparing(Meal::getDateTime).reversed());
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingFieldByFieldElementComparator().isEqualTo(expected);
    }
}
