package com.example.tables;

import com.example.Helper;
import com.example.tables.rows.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MessageTable {
    private static String table = "message";

    public static int insert(Message message){
        try{
            Class.forName(Helper.SQL_DRIVER).getDeclaredConstructor().newInstance();
            try (Connection conn = Helper.getConnection()){
                String sql = "INSERT INTO " + table + " (id_chat, id_user, text, time) Values (?, ?, ?, ?)";
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

                String sql = "SELECT time FROM " + table + " WHERE id_chat = ?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setInt(1, id_chat);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()){
                        /** get Chat's id where is was User **/
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

                String sql = "SELECT * FROM " + table + " WHERE id_chat = ? and time > ?";
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
