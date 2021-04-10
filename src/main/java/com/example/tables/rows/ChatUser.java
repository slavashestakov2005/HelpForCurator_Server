/**
 * Строка таблицы tables.ChatUserTable.
 * */

package com.example.tables.rows;

import com.example.help.JsonWritable;
import com.example.tables.ChatUserTable;
import com.example.tables.DataBaseHelper;
import com.example.tables.SqlHelper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.example.tables.ChatUserTable.columns;
import static com.example.tables.SqlHelper.INT;
import static com.example.tables.SqlHelper.STRING;

public class ChatUser implements JsonWritable {
    private int id, id_user;
    private String time;

    public ChatUser(boolean tyoe, int id, int id_user, String time) {
        this.id = id;
        this.id_user = id_user;
        this.time = time;
    }

    public ChatUser(int id, int id_user, String time) {
        this.id = id;
        this.id_user = id_user;
        this.time = DataBaseHelper.toSQL(time);
    }

    public int getId() {
        return id;
    }

    public int getId_user() {
        return id_user;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "ChatUser{" +
                "id=" + id +
                ", id_user=" + id_user +
                ", time='" + time + '\'' +
                '}';
    }

    public static ChatUser parseSQL(ResultSet resultSet) throws SQLException {
        int chat = resultSet.getInt(columns.getIndex("ID_CHAT"));
        int user = resultSet.getInt(columns.getIndex("id_USER"));
        String  time = resultSet.getString(columns.getIndex("TIME"));
        return new ChatUser(false, chat, user, time);
    }

    @Override
    public String write() {
        start();
        addInt("id_chat", id);
        addInt("id_user", id_user);
        addString("time", time, false);
        finish();
        return jsonStringBuilder.toString();
    }


    public String insertString() {
        return SqlHelper.insertQuery(ChatUserTable.table,
                new String[]{ columns.getName("ID_CHAT"), columns.getName("ID_USER"), columns.getName("TIME") },
                new Object[]{ id, id_user, time },
                new boolean[]{ INT, INT, STRING });
    }
}
