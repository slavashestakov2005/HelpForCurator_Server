package com.example.tables;

import com.example.Helper;
import com.example.tables.rows.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class UsersTable {
    private static String table = "users";
    public static ArrayList<User> select() {
        ArrayList<User> products = new ArrayList<User>();
        try{
            Class.forName(Helper.SQL_DRIVER).getDeclaredConstructor().newInstance();
            try (Connection conn = Helper.getConnection()){

                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM " + table);
                while(resultSet.next()){

                    int id = resultSet.getInt(1);
                    String login = resultSet.getString(2);
                    String password = resultSet.getString(3);
                    String phone = resultSet.getString(4);
                    String email = resultSet.getString(5);
                    String name = resultSet.getString(6);
                    String surname = resultSet.getString(7);
                    String middlename = resultSet.getString(8);
                    User user = new User(id, login, password, phone, email, name, surname, middlename);
                    products.add(user);
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

                String sql = "SELECT * FROM " + table + " WHERE id=?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setInt(1, id);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if(resultSet.next()){
                        int resId = resultSet.getInt(1);
                        String login = resultSet.getString(2);
                        String password = resultSet.getString(3);
                        String phone = resultSet.getString(4);
                        String email = resultSet.getString(5);
                        String name = resultSet.getString(6);
                        String surname = resultSet.getString(7);
                        String middlename = resultSet.getString(8);
                        user = new User(resId, login, password, phone, email, name, surname, middlename);
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

                String sql = "SELECT * FROM " + table + " WHERE login = ?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setString(1, login);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if(resultSet.next()){
                        int resId = resultSet.getInt(1);
                        String password = resultSet.getString(3);
                        String phone = resultSet.getString(4);
                        String email = resultSet.getString(5);
                        String name = resultSet.getString(6);
                        String surname = resultSet.getString(7);
                        String middlename = resultSet.getString(8);
                        user = new User(resId, login, password, phone, email, name, surname, middlename);
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
                String sql = "INSERT INTO " + table + " (Login, Password, Phone, Email, _Name, _Surname, _Middlename) Values (?, ?, ?, ?, ?, ?, ?)";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setString(1, user.getLogin());
                    preparedStatement.setString(2, user.getPassword());
                    preparedStatement.setString(3, user.getPhone());
                    preparedStatement.setString(4, user.getEmail());
                    preparedStatement.setString(5, user.getName());
                    preparedStatement.setString(6, user.getSurname());
                    preparedStatement.setString(7, user.getMiddlename());
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

                String sql = "UPDATE " + table + " SET Login = ?, Password = ?, Phone = ?, Email = ?, _Name = ?,  _Surname = ?, _Middlename = ? WHERE id = ?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setString(1, user.getLogin());
                    preparedStatement.setString(2, user.getPassword());
                    preparedStatement.setString(3, user.getPhone());
                    preparedStatement.setString(4, user.getEmail());
                    preparedStatement.setString(5, user.getName());
                    preparedStatement.setString(6, user.getSurname());
                    preparedStatement.setString(7, user.getMiddlename());
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

                String sql = "DELETE FROM " + table + " WHERE id = ?";
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

                String sql = "SELECT Password FROM " + table + " WHERE login = ?";
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

    public static void updateName(int id, String name, String surname, String middlename) {
        try{
            Class.forName(Helper.SQL_DRIVER).getDeclaredConstructor().newInstance();
            try (Connection conn = Helper.getConnection()){

                String sql = "UPDATE " + table + " SET _Name = ?, _Surname = ?, _Middlename = ? WHERE id = ?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setString(1, name);
                    preparedStatement.setString(2, surname);
                    preparedStatement.setString(3, middlename);
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

                String sql = "UPDATE " + table + " SET Phone = ?, Email = ? WHERE id = ?";
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

                String sql = "UPDATE " + table + " SET Password = ? WHERE id = ?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    System.out.println("All Ok");
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