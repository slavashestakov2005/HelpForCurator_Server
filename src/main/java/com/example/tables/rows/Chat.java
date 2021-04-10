/**
 * Строка таблицы tables.ChatTable.
 * */

package com.example.tables.rows;

import com.example.help.JsonWritable;
import com.example.tables.ChatTable;
import com.example.tables.DataBaseHelper;
import com.example.tables.SqlHelper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.example.tables.ChatTable.columns;
import static com.example.tables.SqlHelper.STRING;

public class Chat implements JsonWritable {
    private int id;
    private String name;

    private Chat(boolean type, int id, String name){
        this.id = id;
        this.name = name;
    }

    public Chat(int id, String name) {
        this(name);
        this.id = id;
    }

    public Chat(String name) {
        this.name = DataBaseHelper.toSQL(name);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public static Chat parseSQL(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(columns.getIndex("ID"));
        String name = resultSet.getString(columns.getIndex("NAME"));
        return new Chat(false, id, name);
    }

    @Override
    public String write() {
        start();
        addInt("id", id);
        addString("name", name, false);
        finish();
        return jsonStringBuilder.toString();
    }

    public String insertString() {
        return SqlHelper.insertQuery(ChatTable.table,
                new String[]{ columns.getName("NAME") },
                new Object[]{ name },
                new boolean[]{ STRING });
    }
}
