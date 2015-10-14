package ru.menkin.servlets;

import org.junit.*;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;

import static org.mockito.Mockito.*;

/**
 * @author Menkin
 * @since 14.10.2015
 */
public class PlayerEditServletTest {
    @Test
    public void test() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("team")).thenReturn("test");
        when(request.getParameter("name")).thenReturn("test");
        when(request.getParameter("salary")).thenReturn("test");
        when(request.getParameter("position")).thenReturn("test");

        new PlayerEditServlet().doPost(request, response);

        verify(request, atLeast(1)).getParameter("id");
        verify(request, atLeast(1)).getParameter("team");
        verify(request, atLeast(1)).getParameter("name");
        verify(request, atLeast(1)).getParameter("salary");
        verify(request, atLeast(1)).getParameter("position");

        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("/EditPlayer.jsp")).thenReturn(dispatcher);
        //without above block throw NullPointerException
        new PlayerEditServlet().doGet(request, response);
    }
}