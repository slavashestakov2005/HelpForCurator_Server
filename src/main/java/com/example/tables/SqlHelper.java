/**
 * Класс для составления SQL запросов.
 * */

package com.example.tables;

public class SqlHelper {
    public static String insertQuery(String table, String... columns){
        StringBuilder builder = new StringBuilder(), builder2 = new StringBuilder();
        builder.append("INSERT INTO ").append(table).append(" (");
        int len = columns.length;
        for(int i = 0; i < len; ++i){
            builder.append(columns[i]);
            builder2.append("?");
            if (i < len - 1){
                builder.append(", ");
                builder2.append(", ");
            }
        }
        builder.append(") Values (").append(builder2).append(")");
        String s = builder.toString();
        System.out.println(s);
        return s;
    }

    public static String deleteQuery(String table, String... columns){
        StringBuilder builder = new StringBuilder();
        builder.append("DELETE FROM ").append(table).append(" WHERE ");
        int len = columns.length;
        for(int i = 0; i < len; ++i){
            builder.append(columns[i]).append(" = ? ");
            if (i < len - 1){
                builder.append("and ");
            }
        }
        String s = builder.toString();
        System.out.println(s);
        return s;
    }

    public static String updateQuery(String table, String where, String... columns){
        StringBuilder builder = new StringBuilder();
        builder.append("UPDATE ").append(table).append(" SET ");
        int len = columns.length;
        for(int i = 0; i < len; ++i){
            builder.append(columns[i]).append(" = ?");
            if (i < len - 1) builder.append(", ");
        }
        builder.append(" WHERE ").append(where).append(" = ?");
        String s = builder.toString();
        System.out.println(s);
        return s;
    }

    public static String selectQuery(String table, String what, String where, boolean b){
        String s = "SELECT " + what + " FROM " + table + " WHERE " + where;
        System.out.println(s);
        return s;
    }

    public static String selectQuery(String table, String what, String where){
        String s = "SELECT " + what + " FROM " + table + " WHERE " + where + " = ?";
        System.out.println(s);
        return s;
    }

    public static String selectQuery(String table, String what){
        String s = "SELECT " + what + " FROM " + table;
        System.out.println(s);
        return s;
    }

    public static String selectQuery(String table){
        String s = "SELECT * FROM " + table;
        System.out.println(s);
        return s;
    }
}
