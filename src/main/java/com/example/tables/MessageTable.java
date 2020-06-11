/**
 * Запросы к таблице message из БД.
 * */

package com.example.tables;

import com.example.help.Helper;
import com.example.tables.rows.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MessageTable {
    private static String table = "message";
    private static Columns columns;

    static{
        columns = new Columns();
        columns.add("ID_CHAT", 1, "id_chat");       // int
        columns.add("ID_USER", 2, "id_user");       // int
        columns.add("TEXT", 3, "text");             // char(200)
        columns.add("TIME", 4, "time");             // char(16)
    }

    public static int insert(Message message){
        try{
            Class.forName(Helper.SQL_DRIVER).getDeclaredConstructor().newInstance();
            try (Connection conn = Helper.getConnection()){
                String sql = SqlHelper.insertQuery(table, columns.getName("ID_CHAT"), columns.getName("ID_USER"),
                        columns.getName("TEXT"), columns.getName("TIME"));
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setInt(1, message.getId_chat());
                    preparedStatement.setInt(2, message.getId_author());
                    preparedStatement.setString(3, message.getText());
                    preparedStatement.setString(4, message.getTime());
                    return preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return 0;
    }

    public static int countOfMessagesInChat(int id_chat){
        int n = 0;
        try{
            Class.forName(Helper.SQL_DRIVER).getDeclaredConstructor().newInstance();
            try (Connection conn = Helper.getConnection()){
                String sql = SqlHelper.selectQuery(table, columns.getName("TIME"), columns.getName("ID_CHAT"));
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setInt(1, id_chat);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()){
                        ++n;
                    }
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return n;
    }

    public static ArrayList<Message> getLastMessages(int id_chat, String _time){
        ArrayList<Message> result = new ArrayList<>();
        try{
            Class.forName(Helper.SQL_DRIVER).getDeclaredConstructor().newInstance();
            try (Connection conn = Helper.getConnection()){
                String sql = SqlHelper.selectQuery(table, "*",
                        columns.getName("ID_CHAT") + " = ? and " + columns.getName("TIME") + " > ?", true);
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setInt(1, id_chat);
                    preparedStatement.setString(2, _time);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()){
                        int id_user = resultSet.getInt(2);
                        String text = resultSet.getString(3);
                        String time = resultSet.getString(4);
                        result.add(new Message(id_chat, id_user, text, time));
                    }
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return result;
    }
}
