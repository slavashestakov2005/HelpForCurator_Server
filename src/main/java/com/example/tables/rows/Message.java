/**
 * Строка таблицы tables.MessageTable.
 * */

package com.example.tables.rows;

import com.example.help.JsonWritable;
import com.example.tables.DataBaseHelper;
import com.example.tables.MessageTable;
import com.example.tables.SqlHelper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.example.tables.MessageTable.columns;
import static com.example.tables.SqlHelper.INT;
import static com.example.tables.SqlHelper.STRING;

public class Message implements JsonWritable {
    private int id_chat, id_author;
    private String text, time;

    public Message(boolean type, int id_chat, int id_author, String text, String time) {
        this.id_chat = id_chat;
        this.id_author = id_author;
        this.text = text;
        this.time = time;
    }

    public Message(int id_chat, int id_author, String text, String time) {
        this.id_chat = id_chat;
        this.id_author = id_author;
        this.text = DataBaseHelper.toSQL(text);
        this.time = DataBaseHelper.toSQL(time);
    }

    public int getId_chat() {
        return id_chat;
    }

    public int getId_author() {
        return id_author;
    }

    public String getText() {
        return text;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id_chat=" + id_chat +
                ", id_author=" + id_author +
                ", text='" + text + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public static Message parseSQL(ResultSet resultSet) throws SQLException {
        int id_chat = resultSet.getInt(columns.getIndex("ID_CHAT"));
        int id_user = resultSet.getInt(columns.getIndex("ID_USER"));
        String text = resultSet.getString(columns.getIndex("TEXT"));
        String time = resultSet.getString(columns.getIndex("TIME"));
        return new Message(false, id_chat, id_user, text, time);
    }

    @Override
    public String write() {
        start();
        addInt("id_chat", id_chat);
        addInt("id_author", id_author);
        addString("text", text);
        addString("time", time, false);
        finish();
        return jsonStringBuilder.toString();
    }

    public String insertString(){
        return SqlHelper.insertQuery(MessageTable.table,
                new String[]{ columns.getName("ID_CHAT"), columns.getName("ID_USER"),
                        columns.getName("TEXT"), columns.getName("TIME") },
                new Object[]{ id_chat, id_author, text, time },
                new boolean[]{ INT, INT, STRING, STRING });
    }
}
