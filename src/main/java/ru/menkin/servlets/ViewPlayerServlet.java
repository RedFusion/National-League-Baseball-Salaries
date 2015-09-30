package ru.menkin.servlets;

import ru.menkin.models.Player;
import ru.menkin.store.UserCache;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
            sortCollection(list, key, typeSort);
            req.setAttribute("players", list);
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/View.jsp");
        dispatcher.forward(req, resp);
    }

    public static void sortCollection(ArrayList<Player> list, final String key, final String typeSort)
    {
        Collections.sort(list, new Comparator<Player>()
        {
            public int compare(Player o1, Player o2)
            {
                if (key.equals("team"))
                {
                    if (typeSort.equals("abc"))
                    {
                        return o1.getTeam().compareTo(o2.getTeam());
                    } else
                    {
                        return o2.getTeam().compareTo(o1.getTeam());
                    }
                }
                if (key.equals("name"))
                {
                    if (typeSort.equals("abc"))
                    {
                        return o1.getName().compareTo(o2.getName());
                    } else
                    {
                        return o2.getName().compareTo(o1.getName());
                    }
                }
                if (key.equals("salary"))
                {
                    double s1 = Double.parseDouble(o1.getSalary());
                    double s2 = Double.parseDouble(o2.getSalary());
                    if (typeSort.equals("abc"))
                    {
                        return Double.compare(s1, s2);
                    } else
                    {
                        return Double.compare(s2, s1);
                    }
                }
                if (key.equals("position"))
                {
                    if (typeSort.equals("abc"))
                    {
                        return o1.getPosition().compareTo(o2.getPosition());
                    } else
                    {
                        return o2.getPosition().compareTo(o1.getPosition());
                    }
                }
                return 0;
            }
        });
    }
}
