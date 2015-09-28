package ru.menkin.servlets;

import ru.menkin.store.UserCache;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ViewPlayerServlet extends HttpServlet
{
    private  final UserCache USER_CACHE = UserCache.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        req.setAttribute("players", this.USER_CACHE.values());
        String key = req.getParameter("id");
        System.out.println(key);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/View.jsp");
        dispatcher.forward(req, resp);
    }
}
