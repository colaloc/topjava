package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

@Controller
public class MealRestController {
    private static final Logger log = LoggerFactory.getLogger(MealRestController.class);

    private final MealService service;

    public MealRestController(MealService service) {
        this.service = service;
    }

    public List<MealTo> getAll() {
        log.info("getAll");
        return MealsUtil.getTos(getAllMeals(), SecurityUtil.authUserCaloriesPerDay());
    }

    private List<Meal> getAllMeals() {
        log.info("getAll");
        return service.getAll(SecurityUtil.getAuthUserId());
    }

    public List<MealTo> getFiltered(String startDate, String endDate, String startTime, String endTime) {
        log.info("getFiltered");
        List<Meal> meals = service.getFilteredByDate(SecurityUtil.getAuthUserId(),
                DateTimeUtil.toLocalDate(startDate, true),
                DateTimeUtil.toLocalDate(endDate, false));
        return MealsUtil.getFilteredByTimeTos(meals,
                SecurityUtil.authUserCaloriesPerDay(),
                DateTimeUtil.toLocalTime(startTime, true),
                DateTimeUtil.toLocalTime(endTime, false));
    }

    public Meal get(int id) {
        log.info("get {}", id);
        return service.get(id, SecurityUtil.getAuthUserId());
    }

    public Meal create(Meal meal) {
        log.info("create {}", meal);
        checkNew(meal);
        return service.create(meal, SecurityUtil.getAuthUserId());
    }

    public Meal update(Meal meal, int id) {
        log.info("update {} with id={}", meal, id);
        assureIdConsistent(meal, id);
        return service.update(meal, SecurityUtil.getAuthUserId());
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id, SecurityUtil.getAuthUserId());
    }
}