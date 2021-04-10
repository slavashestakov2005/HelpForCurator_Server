package com.example.tables;

import com.example.help.Helper;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.Stack;

public class DataBaseHelper {
    private static Connection connection;
    private static Stack<Statement> statements;

    static {
        try {
            connection = null;
            Class.forName(Helper.SQL_DRIVER).getDeclaredConstructor().newInstance();
            connection = Helper.getConnection();
            statements = new Stack<>();
            statements.push(connection.createStatement());
        } catch (SQLException | ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void execute(String sql){
        try {
            synchronized (statements){
                statements.peek().execute(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet executeQuery(String sql){
        System.out.println("EXQ : " + sql);
        try {
            synchronized (statements) {
                return statements.peek().executeQuery(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void push(){
        try {
            synchronized (statements) {
                statements.push(connection.createStatement());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void pop(){
        synchronized (statements) {
            statements.pop();
        }
    }

    public static String toSQL(String sql){
        return sql.replaceAll("'", "''");
    }
}
