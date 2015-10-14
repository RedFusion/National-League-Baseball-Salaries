package ru.menkin.servlets;

import org.junit.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PlayerViewServletTest {
    @Test
    public void test() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("key")).thenReturn("test");
        when(request.getParameter("sort")).thenReturn("test");

        assertNotNull("key is null", request.getParameter("key"));
        assertNotNull("sort is null", request.getParameter("sort"));

        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("/View.jsp")).thenReturn(dispatcher);

        new PlayerViewServlet().doGet(request, response);

        verify(request, atLeast(1)).getParameter("key");
        verify(request, atLeast(1)).getParameter("sort");
    }
}