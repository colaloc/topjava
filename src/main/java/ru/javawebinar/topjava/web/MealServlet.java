package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.DaoMeal;
import ru.javawebinar.topjava.dao.DaoMemoryMeal;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.TimeUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private DaoMeal daoMeal;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        daoMeal = new DaoMemoryMeal();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("action") != null) {
            switch (request.getParameter("action")) {
                case "edit":
                    log.debug("set attribute edit");
                    request.setAttribute("edit", request.getParameter("id"));
                    break;
                case "create":
                    log.debug("set attribute create");
                    request.setAttribute("create", 0);
                    break;
            }
        }

        log.debug("forward to meals");
        request.setAttribute("meals", MealsUtil.filteredByStreams(daoMeal.getAll(), LocalTime.MIN, LocalTime.MAX, 2000));
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        for (int i = 0; i < request.getParameterValues("action").length; i++) {
            switch (request.getParameterValues("action")[i]) {
                case "delete":
                    log.debug("delete meal");
                    daoMeal.delete(Integer.parseInt(request.getParameter("id")));
                    break;
                case "save":
                    log.debug("update meal");
                    Meal updateMeal = new Meal(TimeUtil.convertStringToLocalDateTime(request.getParameter("dateTime")),
                            request.getParameter("description"),
                            Integer.parseInt(request.getParameter("calories")));
                    updateMeal.setId(Integer.parseInt(request.getParameter("id")));
                    daoMeal.update(updateMeal);
                    break;
                case "create":
                    log.debug("add meal");
                    Meal newMeal = new Meal(TimeUtil.convertStringToLocalDateTime(request.getParameter("dateTime")),
                            request.getParameter("description"),
                            Integer.parseInt(request.getParameter("calories")));
                    daoMeal.add(newMeal);
                    break;
            }
        }

        log.debug("redirect to meals");
        response.sendRedirect("meals");
    }
}