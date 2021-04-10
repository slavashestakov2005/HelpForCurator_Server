/**
 * Запросы к таблице users из БД.
 * */

package com.example.tables;

import com.example.tables.rows.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsersTable {
    public static String table = "users";
    public static Columns columns;

    static{
        columns = new Columns();
        columns.add("ID", 1, "id");                     // int  AI  PK
        columns.add("LOGIN", 2, "login");               // varchar(45)
        columns.add("PASSWORD", 3, "password");         // varchar(45)
        columns.add("PHONE", 4, "phone");               // varchar(45)
        columns.add("EMAIL", 5, "email");               // varchar(45)
        columns.add("NAME", 6, "_name");                // varchar(45)
        columns.add("SURNAME",7, "_surname");           // varchar(45)
        columns.add("MIDDLENAME", 8, "_middlename");    // varchar(45)
    }

    public static ArrayList<User> selectAll() {
        ArrayList<User> users = new ArrayList<>();
        try {
            ResultSet resultSet = DataBaseHelper.executeQuery("SELECT * FROM " + table);
            if (resultSet != null) {
                while (resultSet.next()) {
                    users.add(User.parseSQL(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static User selectById(int id) {
        try{
            String sql = "SELECT * FROM " + table + " WHERE " + columns.getName("ID") + " = " + id;
            ResultSet resultSet = DataBaseHelper.executeQuery(sql);
            if (resultSet != null && resultSet.next()) return User.parseSQL(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static User selectByLogin(String login){
        try{
            String sql = "SELECT * FROM " + table + " WHERE " + columns.getName("LOGIN") + " = '" + login + "'";
            ResultSet resultSet = DataBaseHelper.executeQuery(sql);
            if (resultSet != null && resultSet.next()) return User.parseSQL(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void insert(User user) {
        DataBaseHelper.execute(user.insertString());
    }

    public static void delete(int id) {
        DataBaseHelper.execute("DELETE FROM " + table + " WHERE " + columns.getName("ID") + " = " + id);
    }

    public static String passwordFromLogin(String login){
        User user = selectByLogin(login);
        return user == null ? "" : user.getPassword();
    }

    public static void updateName(int id, String name, String surname, String middleName) {
        DataBaseHelper.execute(new User(id, "", "", "", "", name, surname, middleName).updateNameString());
    }

    public static void updateContacts(int id, String phone, String email) {
        DataBaseHelper.execute(new User(id, "", "", phone, email, "", "", "").updateContactsString());
    }

    public static void updatePassword(int id, String _password) {
        DataBaseHelper.execute(new User(id, "", _password, "", "", "", "", "").updatePasswordString());
    }
}