package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.MealTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    @Autowired
    private MealService service;

    @Qualifier("jdbcMealRepository")
    @Autowired
    private MealRepository repository;

    @Test
    public void create() throws Exception {
        Meal newMeal = MEAL_NEW;
        Meal created = service.create(newMeal, USER_ID);
        Integer newId = created.getId();
        newMeal.setId(newId);
        assertMatch(created, newMeal);
        assertMatch(service.get(newId, USER_ID), newMeal);
    }

    @Test
    public void delete() throws Exception {
        service.delete(MEAL_FOR_DELETE.getId(), USER_ID);
        assertNull(repository.get(MEAL_FOR_DELETE.getId(), USER_ID));
    }

    @Test
    public void deletedNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND, USER_ID));
    }

    @Test
    public void get() throws Exception {
        Meal meal = service.get(MEAL_FOR_GET.getId(), USER_ID);
        assertMatch(meal, MEAL_FOR_GET);
    }

    @Test
    public void getNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND, USER_ID));
    }

    @Test
    public void update() throws Exception {
        service.update(MEAL_AFTER_UPDATE, USER_ID);
        assertMatch(repository.get(MEAL_AFTER_UPDATE.getId(), USER_ID), MEAL_AFTER_UPDATE);
    }

    @Test
    public void getAll() throws Exception {
        List<Meal> all = service.getAll(USER_ID);
        assertMatch(all, MEAL_NEW, MEAL_FOR_DELETE, MEAL_FOR_GET, MEAL_BEFORE_UPDATE);
    }

    @Test
    public void deleteAlien() throws Exception {
        assertMatch(repository.get(MEAL_FOR_DELETE.getId(), USER_ID), MEAL_FOR_DELETE);
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND, USER_ID));
    }

    @Test
    public void getAlien() throws Exception {
        assertMatch(repository.get(MEAL_FOR_GET.getId(), USER_ID), MEAL_FOR_GET);
        assertThrows(NotFoundException.class, () -> service.get(MEAL_FOR_GET.getId(), ADMIN_ID));
    }

    @Test
    public void updateAlien() throws Exception {
        assertMatch(repository.get(MEAL_BEFORE_UPDATE.getId(), USER_ID), MEAL_BEFORE_UPDATE);
        assertThrows(NotFoundException.class, () -> service.update(MEAL_AFTER_UPDATE, ADMIN_ID));
    }
}