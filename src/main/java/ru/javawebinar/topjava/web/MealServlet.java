package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.DaoMeal;
import ru.javawebinar.topjava.dao.DaoMemoryMeal;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Enumeration;

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

        if (request.getParameter("edit") != null) {
            log.debug("set attribute edit");
            request.setAttribute("edit", request.getParameter("edit"));
        }
        if (request.getParameter("create") != null) {
            log.debug("set attribute create");
            request.setAttribute("create", request.getParameter("create"));
        }

        log.debug("forward to meals");
        request.setAttribute("meals", MealsUtil.filteredByStreams(daoMeal.getAll(), LocalTime.MIN, LocalTime.MAX, 2000));
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Enumeration<String> params = request.getParameterNames();
        while (params.hasMoreElements()) {
            String param = params.nextElement();
            switch (param) {
                case "delete":
                    log.debug("delete meal");
                    daoMeal.delete(Integer.parseInt(request.getParameter("delete")));
                    break;
                case "save":
                    log.debug("update meal");
                    daoMeal.update(Integer.parseInt(request.getParameter("save")), new Meal(
                            request.getParameter("dateTime"),
                            request.getParameter("description"),
                            request.getParameter("calories")));
                    break;
                case "add":
                    log.debug("add meal");
                    daoMeal.add(new Meal(request.getParameter("dateTime"),
                            request.getParameter("description"),
                            request.getParameter("calories")));
                    break;
            }
        }

        log.debug("redirect to meals");
        response.sendRedirect("meals");
    }
}
