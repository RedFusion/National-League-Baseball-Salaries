package ru.menkin.servlets;

import ru.menkin.models.Player;
import ru.menkin.store.PlayerCache;
import ru.menkin.utils.*;

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
public class PlayerViewServlet extends HttpServlet
{
    private final PlayerCache PLAYER_CACHE = PlayerCache.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        req.setAttribute("players", this.PLAYER_CACHE.values());

        String key = req.getParameter("key");
        String typeSort = req.getParameter("sort");

        req.setAttribute("sort", typeSort == null ? "abs" : typeSort);
        req.setAttribute("key", key == null ? "team" : key);

        ArrayList<Player> list = new ArrayList<Player>();
        list.addAll(PLAYER_CACHE.values());

        if (key != null && typeSort != null)
        {
            SortCollection sortClass = new SortCollection(list, key, typeSort);
            sortClass.sortCollection();
            req.setAttribute("players", list);
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/View.jsp");
        dispatcher.forward(req, resp);
    }
}