/**
 * Запросы к таблице users из БД.
 * */

package com.example.tables;

import com.example.help.Helper;
import com.example.tables.rows.User;

import java.sql.*;
import java.util.ArrayList;

public class UsersTable {
    private static String table = "users";
    private static Columns columns;

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

    private static User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(columns.getIndex("ID"));
        String login = resultSet.getString(columns.getIndex("LOGIN"));
        String password = resultSet.getString(columns.getIndex("PASSWORD"));
        String phone = resultSet.getString(columns.getIndex("PHONE"));
        String email = resultSet.getString(columns.getIndex("EMAIL"));
        String name = resultSet.getString(columns.getIndex("NAME"));
        String surname = resultSet.getString(columns.getIndex("SURNAME"));
        String middleName = resultSet.getString(columns.getIndex("MIDDLENAME"));
        return new User(id, login, password, phone, email, name, surname, middleName);

    }

    public static ArrayList<User> select() {
        ArrayList<User> products = new ArrayList<User>();
        try{
            Class.forName(Helper.SQL_DRIVER).getDeclaredConstructor().newInstance();
            try (Connection conn = Helper.getConnection()){
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(SqlHelper.selectQuery(table));
                while(resultSet.next()){
                    products.add(getUserFromResultSet(resultSet));
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return products;
    }

    public static User selectOne(int id) {
        User user = null;
        try{
            Class.forName(Helper.SQL_DRIVER).getDeclaredConstructor().newInstance();
            try (Connection conn = Helper.getConnection()){
                String sql = SqlHelper.selectQuery(table, "*", columns.getName("ID"));
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setInt(1, id);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if(resultSet.next()){
                        user = getUserFromResultSet(resultSet);
                    }
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return user;
    }

    public static User select(String login){
        User user = new User(-1, "", "", "", "", "", "", "");
        try{
            Class.forName(Helper.SQL_DRIVER).getDeclaredConstructor().newInstance();
            try (Connection conn = Helper.getConnection()){
                String sql = SqlHelper.selectQuery(table, "*", columns.getName("LOGIN"));
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setString(1, login);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if(resultSet.next()){
                        user = getUserFromResultSet(resultSet);
                    }
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return user;
    }

    public static int insert(User user) {
        try{
            Class.forName(Helper.SQL_DRIVER).getDeclaredConstructor().newInstance();
            try (Connection conn = Helper.getConnection()){
                String sql = SqlHelper.insertQuery(table,
                        columns.getName("LOGIN"), columns.getName("PASSWORD"), columns.getName("PHONE"), columns.getName("EMAIL"),
                        columns.getName("NAME"), columns.getName("SURNAME"), columns.getName("MIDDLENAME"));
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setString(1, user.getLogin());
                    preparedStatement.setString(2, user.getPassword());
                    preparedStatement.setString(3, user.getPhone());
                    preparedStatement.setString(4, user.getEmail());
                    preparedStatement.setString(5, user.getName());
                    preparedStatement.setString(6, user.getSurname());
                    preparedStatement.setString(7, user.getMiddleName());
                    return preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return 0;
    }

    public static int update(User user) {
        try{
            Class.forName(Helper.SQL_DRIVER).getDeclaredConstructor().newInstance();
            try (Connection conn = Helper.getConnection()){
                String sql = SqlHelper.updateQuery(table, columns.getName("ID"), columns.getName("LOGIN"),
                        columns.getName("PASSWORD"), columns.getName("PHONE"), columns.getName("EMAIL"),
                        columns.getName("NAME"), columns.getName("SURNAME"), columns.getName("MIDDLENAME"));
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setString(1, user.getLogin());
                    preparedStatement.setString(2, user.getPassword());
                    preparedStatement.setString(3, user.getPhone());
                    preparedStatement.setString(4, user.getEmail());
                    preparedStatement.setString(5, user.getName());
                    preparedStatement.setString(6, user.getSurname());
                    preparedStatement.setString(7, user.getMiddleName());
                    preparedStatement.setInt(8, user.getId());
                    return preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return 0;
    }

    public static int delete(int id) {
        try{
            Class.forName(Helper.SQL_DRIVER).getDeclaredConstructor().newInstance();
            try (Connection conn = Helper.getConnection()){
                String sql = SqlHelper.deleteQuery(table, columns.getName("ID"));
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setInt(1, id);
                    return  preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return 0;
    }

    public static String passwordFromLogin(String login){
        try{
            Class.forName(Helper.SQL_DRIVER).getDeclaredConstructor().newInstance();
            try (Connection conn = Helper.getConnection()){
                String sql = SqlHelper.selectQuery(table, columns.getName("PASSWORD"), columns.getName("LOGIN"));
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setString(1, login);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if(resultSet.next()){
                        return resultSet.getString(1);
                    }
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return "";
    }

    public static void updateName(int id, String name, String surname, String middleName) {
        try{
            Class.forName(Helper.SQL_DRIVER).getDeclaredConstructor().newInstance();
            try (Connection conn = Helper.getConnection()){
                String sql = SqlHelper.updateQuery(table, columns.getName("ID"),
                        columns.getName("NAME"), columns.getName("SURNAME"), columns.getName("MIDDLENAME"));
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setString(1, name);
                    preparedStatement.setString(2, surname);
                    preparedStatement.setString(3, middleName);
                    preparedStatement.setInt(4, id);
                    preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }

    public static void updateContacts(int id, String phone, String email) {
        try{
            Class.forName(Helper.SQL_DRIVER).getDeclaredConstructor().newInstance();
            try (Connection conn = Helper.getConnection()){
                String sql = SqlHelper.updateQuery(table, columns.getName("ID"),
                        columns.getName("PHONE"), columns.getName("EMAIL"));
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setString(1, phone);
                    preparedStatement.setString(2, email);
                    preparedStatement.setInt(3, id);
                    preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }

    public static void updatePassword(int id, String _password) {
        try{
            Class.forName(Helper.SQL_DRIVER).getDeclaredConstructor().newInstance();
            try (Connection conn = Helper.getConnection()){
                String sql = SqlHelper.updateQuery(table, columns.getName("ID"), columns.getName("PASSWORD"));
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setString(1, _password);
                    preparedStatement.setInt(2, id);
                    preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }
}