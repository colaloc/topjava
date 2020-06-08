package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.DAOMeal;
import ru.javawebinar.topjava.dao.DAOMemoryMeal;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private final DAOMeal daoMemoryMeal = new DAOMemoryMeal();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("edit") != null) {
            request.setAttribute("edit", request.getParameter("edit"));
            log.debug("set attribute edit");
        }
        if (request.getParameter("create") != null) {
            request.setAttribute("create", request.getParameter("create"));
            log.debug("set attribute create");
        }

        request.setAttribute("meals", MealsUtil.filteredByStreams(daoMemoryMeal.getAllMeals(), LocalTime.MIN, LocalTime.MAX, 2000));
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
        log.debug("redirect to meals");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");
        if (request.getParameter("delete") != null) {
            daoMemoryMeal.deleteMeal(Integer.parseInt(request.getParameter("delete")));
            log.debug("delete meal");
        }
        if (request.getParameter("save") != null) {
            daoMemoryMeal.updateMeal(Integer.parseInt(request.getParameter("save")),
                    request.getParameter("dateTime"),
                    request.getParameter("description"),
                    Integer.parseInt(request.getParameter("calories")));
            log.debug("update meal");
        }
        if (request.getParameter("add") != null) {
            daoMemoryMeal.addMeal(request.getParameter("dateTime"),
                    request.getParameter("description"),
                    Integer.parseInt(request.getParameter("calories")));
            log.debug("add meal");
        }

        request.setAttribute("meals", MealsUtil.filteredByStreams(daoMemoryMeal.getAllMeals(), LocalTime.MIN, LocalTime.MAX, 2000));
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
        log.debug("redirect to meals");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
