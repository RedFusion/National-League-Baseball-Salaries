package ru.menkin.utils;

import java.io.*;
import java.util.*;

/**
 * @author Menkin
 * @since 10.10.2015
 */
public class Settings {
    private static final Settings INSTANCE = new Settings();
    private final Properties properties = new Properties();

    private Settings() {
        try {
            properties.load(new FileInputStream(this.getClass().getClassLoader().getResource("database.properties").getFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Settings getInstance() {
        return INSTANCE;
    }

    public String value(String key) {
        return this.properties.getProperty(key);
    }
}
