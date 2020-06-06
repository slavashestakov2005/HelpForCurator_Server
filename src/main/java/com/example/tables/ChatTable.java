package com.example.tables;

import com.example.Helper;
import com.example.tables.rows.Chat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ChatTable {
    private static String table = "chat";

    public static Chat select(int id){
        Chat chat = null;
        try{
            Class.forName(Helper.SQL_DRIVER).getDeclaredConstructor().newInstance();
            try (Connection conn = Helper.getConnection()){

                String sql = "SELECT * FROM " + table + " WHERE id=?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setInt(1, id);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if(resultSet.next()){
                        int resId = resultSet.getInt(1);
                        String name = resultSet.getString(2);
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

                String sql = "SELECT * FROM " + table + " WHERE name=?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setString(1, name);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if(resultSet.next()){
                        int resId = resultSet.getInt(1);
                        String resName = resultSet.getString(2);
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
                String sql = "INSERT INTO " + table + " (name) Values (?)";
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

                String sql = "SELECT id FROM " + table + " WHERE name = ?";
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
