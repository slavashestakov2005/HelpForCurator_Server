/**
 * Запросы к таблице chat из БД.
 * */

package com.example.tables;

import com.example.Helper;
import com.example.tables.rows.Chat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ChatTable {
    private static String table = "chat";
    private static Columns columns;

    static{
        columns = new Columns();
        columns.add("ID", 1, "id");
        columns.add("NAME", 2, "name");
    }

    public static Chat select(int id){
        Chat chat = null;
        try{
            Class.forName(Helper.SQL_DRIVER).getDeclaredConstructor().newInstance();
            try (Connection conn = Helper.getConnection()){
                String sql = SqlHelper.selectQuery(table, "*", columns.getName("ID"));
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setInt(1, id);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if(resultSet.next()){
                        int resId = resultSet.getInt(columns.getIndex("ID"));
                        String name = resultSet.getString(columns.getIndex("NAME"));
                        chat = new Chat(resId, name);
                    }
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return chat;
    }

    public static Chat select(String name){
        Chat chat = null;
        try{
            Class.forName(Helper.SQL_DRIVER).getDeclaredConstructor().newInstance();
            try (Connection conn = Helper.getConnection()){
                String sql = SqlHelper.selectQuery(table, "*", columns.getName("NAME"));
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setString(1, name);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if(resultSet.next()){
                        int resId = resultSet.getInt(columns.getIndex("ID"));
                        String resName = resultSet.getString(columns.getIndex("NAME"));
                        chat = new Chat(resId, resName);
                    }
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return chat;
    }

    public static int insert(Chat chat) {
        try{
            Class.forName(Helper.SQL_DRIVER).getDeclaredConstructor().newInstance();
            try (Connection conn = Helper.getConnection()){
                String sql = SqlHelper.insertQuery(table, columns.getName("NAME"));
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setString(1, chat.getName());
                    return preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return 0;
    }

    public static String idFromName(String name){
        try{
            Class.forName(Helper.SQL_DRIVER).getDeclaredConstructor().newInstance();
            try (Connection conn = Helper.getConnection()){
                String sql = SqlHelper.selectQuery(table, columns.getName("ID"), columns.getName("NAME"));
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setString(1, name);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if(resultSet.next()){
                        return resultSet.getString(1);
                    }
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return "";
    }
}
