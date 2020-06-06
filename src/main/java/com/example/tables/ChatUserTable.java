package com.example.tables;

import com.example.Helper;
import com.example.tables.rows.Chat;
import com.example.tables.rows.ChatUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ChatUserTable {
    private static String table = "chat_user";

    public static int insert(ChatUser chatUser) {
        try{
            Class.forName(Helper.SQL_DRIVER).getDeclaredConstructor().newInstance();
            try (Connection conn = Helper.getConnection()){
                String sql = "INSERT INTO " + table + " (id, id_user, time) Values (?, ?, ?)";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setInt(1, chatUser.getId());
                    preparedStatement.setInt(2, chatUser.getId_user());
                    preparedStatement.setString(3, chatUser.getTime());
                    return preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return 0;
    }

    public static ArrayList<Chat> selectChatsForUser(int user_id, String _time){
        ArrayList<Chat> chats = new ArrayList<Chat>();
        try{
            Class.forName(Helper.SQL_DRIVER).getDeclaredConstructor().newInstance();
            try (Connection conn = Helper.getConnection()){

                String sql = "SELECT id FROM " + table + " WHERE id_user = ? and time > ?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setInt(1, user_id);
                    preparedStatement.setString(2, _time);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()){
                        /** get Chat's id where is was User **/
                        int resId = resultSet.getInt(1);
                        chats.add(ChatTable.select(resId));
                    }
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return chats;
    }

    public static int delete(ChatUser chatUser) {
        try{
            Class.forName(Helper.SQL_DRIVER).getDeclaredConstructor().newInstance();
            try (Connection conn = Helper.getConnection()){

                String sql = "DELETE FROM " + table + " WHERE id = ? and id_user = ?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setInt(1, chatUser.getId());
                    preparedStatement.setInt(2, chatUser.getId_user());
                    return preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return 0;
    }
}
