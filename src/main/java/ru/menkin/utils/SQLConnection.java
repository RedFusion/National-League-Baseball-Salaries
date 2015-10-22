package ru.menkin.utils;

import javax.naming.*;
import javax.sql.*;
import java.sql.*;

/**
 * @author Menkin
 * @since 22.10.2015
 */
public class SQLConnection {
    private InitialContext initialContext;
    private DataSource dataSource;

    public Connection getConnection() throws NamingException, SQLException {
        initialContext = new InitialContext();
        dataSource = (DataSource) initialContext.lookup("java:/comp/env/jdbc/dbconnect");
        return dataSource.getConnection();
    }
}