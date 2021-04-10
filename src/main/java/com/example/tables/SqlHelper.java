/**
 * Класс для составления SQL запросов.
 * */

package com.example.tables;

public class SqlHelper {
    public static boolean STRING = true, INT = false;

    public static String insertQuery(String table, String[] columns, Object[] values, boolean[] types){
        StringBuilder builder = new StringBuilder(), builder2 = new StringBuilder();
        builder.append("INSERT INTO ").append(table).append(" (");
        int len = columns.length;
        for(int i = 0; i < len; ++i){
            builder.append(columns[i]);
            if (types[i] == INT) builder2.append(values[i]);
            else builder2.append("'").append(DataBaseHelper.toSQL((String) values[i])).append("'");
            if (i < len - 1){
                builder.append(", ");
                builder2.append(", ");
            }
        }
        builder.append(") Values (").append(builder2).append(")");
        return builder.toString();
    }

    public static String updateQuery(String table, String[] columns, Object[] values, boolean[] types, String where){
        StringBuilder builder = new StringBuilder();
        builder.append("UPDATE ").append(table).append(" SET ");
        int len = columns.length;
        for(int i = 0; i < len; ++i){
            builder.append(columns[i]).append(" = ");
            if (types[i] == INT) builder.append(values[i]);
            else builder.append("'").append(DataBaseHelper.toSQL((String) values[i])).append("'");
            if (i < len - 1) builder.append(", ");
        }
        builder.append(" WHERE ").append(where);
        return builder.toString();
    }

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
