/**
 * Запросы к таблице chat_user из БД.
 * */

package com.example.tables;

import com.example.tables.rows.Chat;
import com.example.tables.rows.ChatUser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ChatUserTable {
    public static String table = "chat_user";
    public static Columns columns;

    static{
        columns = new Columns();
        columns.add("ID_CHAT", 1, "id");        // int  PK
        columns.add("ID_USER", 2, "id_user");   // int  PK
        columns.add("TIME", 3, "time");         // varchar(16)
    }

    public static void insert(ChatUser chatUser) {
        DataBaseHelper.execute(chatUser.insertString());
    }

    public static ArrayList<Chat> selectChatsForUser(int user_id, String _time){
        ArrayList<Chat> chats = new ArrayList<>();
        try {
            ResultSet resultSet = DataBaseHelper.executeQuery("SELECT " + columns.getName("ID_CHAT") + " FROM " + table + " WHERE " +
                    columns.getName("ID_USER") + " = " + user_id + " and " + columns.getName("TIME") + " > '" + _time + "'");
            if (resultSet != null) {
                while (resultSet.next()) {
                    int resId = resultSet.getInt(1);
                    DataBaseHelper.push();
                    chats.add(ChatTable.selectById(resId));
                    DataBaseHelper.pop();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chats;
    }

    public static ArrayList<Integer> selectUserForChat(int chat_id){
        ArrayList<Integer> users = new ArrayList<>();
        try {
            ResultSet resultSet = DataBaseHelper.executeQuery("SELECT " + columns.getName("ID_USER") + " FROM " + table + " WHERE " +
                    columns.getName("ID_CHAT") + " = " + chat_id);
            if (resultSet != null) {
                while (resultSet.next()) {
                    int userId = resultSet.getInt(1);
                    users.add(userId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static void delete(ChatUser chatUser) {
        DataBaseHelper.execute("DELETE FROM " + table + " WHERE " + columns.getName("ID_CHAT") + " = " + chatUser.getId() + " and " +
                columns.getName("ID_USER") + " = " + chatUser.getId_user());
    }
}
