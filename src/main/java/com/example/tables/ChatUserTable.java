/**
 * Запросы к таблице chat_user из БД.
 * */

package com.example.tables;

import com.example.help.Helper;
import com.example.tables.rows.Chat;
import com.example.tables.rows.ChatUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ChatUserTable {
    private static String table = "chat_user";
    private static Columns columns;

    static{
        columns = new Columns();
        columns.add("ID_CHAT", 1, "id");        // int  PK
        columns.add("ID_USER", 2, "id_user");   // int  PK
        columns.add("TIME", 3, "time");         // varchar(16)
    }

    public static int insert(ChatUser chatUser) {
        try{
            Class.forName(Helper.SQL_DRIVER).getDeclaredConstructor().newInstance();
            try (Connection conn = Helper.getConnection()){
                String sql = SqlHelper.insertQuery(table, columns.getName("ID_CHAT"),
                        columns.getName("ID_USER"), columns.getName("TIME"));
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
                String sql = SqlHelper.selectQuery(table, columns.getName("ID_CHAT"),
                        columns.getName("ID_USER") + " = ? and " + columns.getName("TIME") + " > ?", true);
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setInt(1, user_id);
                    preparedStatement.setString(2, _time);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()){
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
                String sql = SqlHelper.deleteQuery(table, columns.getName("ID_CHAT"), columns.getName("ID_USER"));
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
