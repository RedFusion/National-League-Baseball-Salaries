package ru.menkin.servlets;

import ru.menkin.store.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

/**
 * @author Menkin
 * @since 14.10.2015
 */
public class PlayerDeleteServlet extends HttpServlet {
    private final PlayerCache cache = PlayerCache.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        cache.delete(Integer.valueOf(req.getParameter("id")));
        resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/view"));
    }
}
