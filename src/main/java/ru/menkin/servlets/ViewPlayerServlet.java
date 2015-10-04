package ru.menkin.servlets;

import ru.menkin.models.Player;
import ru.menkin.store.UserCache;
import ru.menkin.utils.SortCollection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Menkin
 */
public class ViewPlayerServlet extends HttpServlet
{
    private final UserCache USER_CACHE = UserCache.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        req.setAttribute("players", this.USER_CACHE.values());

        String key = req.getParameter("key");
        String typeSort = req.getParameter("sort");

        req.setAttribute("sort", typeSort == null ? "abc" : typeSort);
        req.setAttribute("key", key == null ? "team" : key);


        ArrayList<Player> list = new ArrayList<Player>();
        list.addAll(USER_CACHE.values());

        if (key != null && typeSort != null)
        {
            SortCollection sortClass = new SortCollection(list, key, typeSort);
            sortClass.sortCollection();
            req.setAttribute("players", list);
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/View.jsp");
        dispatcher.forward(req, resp);
    }
}
