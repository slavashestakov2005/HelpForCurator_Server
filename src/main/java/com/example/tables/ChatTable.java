/**
 * Запросы к таблице chat из БД.
 * */

package com.example.tables;

import com.example.tables.rows.Chat;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ChatTable {
    public static String table = "chat";
    public static Columns columns;

    static{
        columns = new Columns();
        columns.add("ID", 1, "id");         // int  AI  PK
        columns.add("NAME", 2, "name");     // char(50)
    }

    public static Chat selectById(int id){
        try{
            String sql = "SELECT * FROM " + table + " WHERE " + columns.getName("ID") + " = " + id;
            ResultSet resultSet = DataBaseHelper.executeQuery(sql);
            if (resultSet != null && resultSet.next()) return Chat.parseSQL(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Chat selectByName(String name){
        try{
            String sql = "SELECT * FROM " + table + " WHERE " + columns.getName("NAME") + " = '" + name + "'";
            ResultSet resultSet = DataBaseHelper.executeQuery(sql);
            if (resultSet != null && resultSet.next()) return Chat.parseSQL(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void insert(Chat chat) {
        DataBaseHelper.execute(chat.insertString());
    }

    public static int idFromName(String name){
        Chat chat  = selectByName(name);
        return chat == null ? 0 : chat.getId();
    }
}
