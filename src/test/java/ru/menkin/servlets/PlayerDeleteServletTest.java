package ru.menkin.servlets;

import org.junit.*;
import ru.menkin.store.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

import static org.mockito.Mockito.*;

/**
 * @author Menkin
 * @since 14.10.2015
 */
public class PlayerDeleteServletTest {
    private final PlayerCache cache = PlayerCache.getInstance();

    @Test
    public void test() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("id")).thenReturn("1");

        new PlayerDeleteServlet().doGet(request, response);

        verify(request, atLeast(1)).getParameter("id");
    }
}