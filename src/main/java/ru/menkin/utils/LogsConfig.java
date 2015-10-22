package ru.menkin.utils;

import org.apache.log4j.*;

import java.io.*;
import java.util.*;

/**
 * @author Menkin
 * @since 15.10.2015
 */
public class LogsConfig {
    private static Properties logProperties = new Properties();
    private static String logFile;//path

    @SuppressWarnings("static-access")
    public LogsConfig(String logFile){
        this.logFile = logFile;
    }

    public void init() throws IOException {
        logProperties.load(new FileInputStream(logFile));
        PropertyConfigurator.configure(logProperties);
    }
}
