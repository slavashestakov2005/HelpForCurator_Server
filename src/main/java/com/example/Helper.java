package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Calendar;

public class Helper {
    static private String DATABASE = "HelpForCurator";
    static public String URL = "jdbc:mysql://localhost/" + DATABASE + "?serverTimezone=Europe/Moscow&useSSL=false";
    static public String USER = "root", PASSWORD = "postgres";
    static public String SQL_DRIVER = "com.mysql.cj.jdbc.Driver";

    static public String getTime() {
        Calendar calendar = Calendar.getInstance();
        StringBuilder builder = new StringBuilder();
        int time = calendar.get(calendar.DAY_OF_MONTH);
        if (time < 10) builder.append("0");
        builder.append("" + time + ".");
        time = calendar.get(calendar.MONTH) + 1;
        if (time < 10) builder.append("0");
        builder.append("" + time + ".");
        time = calendar.get(calendar.YEAR);
        builder.append("" + time + " ");
        time = calendar.get(calendar.AM_PM) * 12 + calendar.get(calendar.HOUR);
        if (time < 10) builder.append("0");
        builder.append("" + time + ".");
        time = calendar.get(calendar.MINUTE);
        if (time < 10) builder.append("0");
        builder.append("" + time);
        return builder.toString();
    }

    static public String currentTime(){
        long l = System.currentTimeMillis();
        System.out.println("Current time : + " + l);
        return "" + l;
    }

    static public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(Helper.URL, Helper.USER, Helper.PASSWORD);
    }
}
