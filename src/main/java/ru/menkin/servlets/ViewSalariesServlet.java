package ru.menkin.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ViewSalariesServlet extends HttpServlet
{
    volatile String name;
    volatile String login;
    volatile String email;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        req.setAttribute("login", login);
        req.setAttribute("email", email);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/ViewSalaries.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        name = req.getParameter("name");
        login = req.getParameter("login");
        email = req.getParameter("email");
        doGet(req, resp);
    }
}
