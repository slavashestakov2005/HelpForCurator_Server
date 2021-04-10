/**
 * Запросы к таблице message из БД.
 * */

package com.example.tables;

import com.example.tables.rows.Message;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MessageTable {
    public static String table = "message";
    public static Columns columns;

    static{
        columns = new Columns();
        columns.add("ID_CHAT", 1, "id_chat");       // int
        columns.add("ID_USER", 2, "id_user");       // int
        columns.add("TEXT", 3, "text");             // char(200)
        columns.add("TIME", 4, "time");             // char(16)
    }

    public static void insert(Message message){
        DataBaseHelper.execute(message.insertString());
    }

    public static ArrayList<Message> getLastMessages(int id_chat, String _time){
        ArrayList<Message> messages = new ArrayList<>();
        try {
            ResultSet resultSet = DataBaseHelper.executeQuery("SELECT * FROM " + table + " WHERE " +
                    columns.getName("ID_CHAT") + " = " + id_chat + " and " + columns.getName("TIME") + " > '" + _time + "'");
            if (resultSet != null) {
                while (resultSet.next()) {
                    messages.add(Message.parseSQL(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }
}
