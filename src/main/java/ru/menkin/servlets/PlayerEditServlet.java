package ru.menkin.servlets;

import ru.menkin.models.*;
import ru.menkin.store.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

/**
 * @author Menkin
 * @since 14.10.2015
 */
public class PlayerEditServlet extends HttpServlet{
    private final PlayerCache cache = PlayerCache.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("player", this.cache.get(Integer.valueOf(req.getParameter("id"))));
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/EditPlayer.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        cache.edit(new Player(Integer.valueOf(req.getParameter("id")), req.getParameter("team"), req.getParameter("name"),
                req.getParameter("salary"), req.getParameter("position")));
        resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/view"));
    }
}
