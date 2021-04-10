/**
 * Строка таблицы tables.UsersTable.
 * */

package com.example.tables.rows;

import com.example.help.JsonWritable;
import com.example.tables.DataBaseHelper;
import com.example.tables.SqlHelper;
import com.example.tables.UsersTable;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.example.tables.UsersTable.columns;
import static com.example.tables.SqlHelper.STRING;

public class User implements JsonWritable {
    private int id;
    private String login, password, phone, email, name, surname, middleName;

    private User(boolean type, int id, String login, String password, String phone, String email, String name, String surname, String middleName) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
    }

    public User(int id, String login, String password, String phone, String email, String name, String surname, String middleName) {
        this(login, password, phone, email, name, surname, middleName);
        this.id = id;
    }

    public User(String login, String password, String phone, String email, String name, String surname, String middleName) {
        this.login = DataBaseHelper.toSQL(login);
        this.password = DataBaseHelper.toSQL(password);
        this.phone = DataBaseHelper.toSQL(phone);
        this.email = DataBaseHelper.toSQL(email);
        this.name = DataBaseHelper.toSQL(name);
        this.surname = DataBaseHelper.toSQL(surname);
        this.middleName = DataBaseHelper.toSQL(middleName);
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getMiddleName() {
        return middleName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", middleName='" + middleName + '\'' +
                '}';
    }

    public static User parseSQL(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(columns.getIndex("ID"));
        String login = resultSet.getString(columns.getIndex("LOGIN"));
        String password = resultSet.getString(columns.getIndex("PASSWORD"));
        String phone = resultSet.getString(columns.getIndex("PHONE"));
        String email = resultSet.getString(columns.getIndex("EMAIL"));
        String name = resultSet.getString(columns.getIndex("NAME"));
        String surname = resultSet.getString(columns.getIndex("SURNAME"));
        String middleName = resultSet.getString(columns.getIndex("MIDDLENAME"));
        return new User(false, id, login, password, phone, email, name, surname, middleName);
    }

    @Override
    public String write() {
        start();
        addInt("id", id);
        addString("login", login);
        addString("password", password);
        addString("phone", phone);
        addString("email", email);
        addString("name", name);
        addString("surname", surname);
        addString("middleName", middleName, false);
        finish();
        return jsonStringBuilder.toString();
    }

    public String insertString(){
        return SqlHelper.insertQuery(UsersTable.table,
                new String[]{ columns.getName("LOGIN"), columns.getName("PASSWORD"),
                        columns.getName("PHONE"), columns.getName("EMAIL"), columns.getName("NAME"),
                        columns.getName("SURNAME"), columns.getName("MIDDLENAME") },
                new Object[]{ login, password, phone, email, name, surname, middleName },
                new boolean[]{ STRING, STRING, STRING, STRING, STRING, STRING, STRING });
    }

    public String updateNameString(){
        return SqlHelper.updateQuery(UsersTable.table,
                new String[]{ columns.getName("NAME"), columns.getName("SURNAME"), columns.getName("MIDDLENAME") },
                new Object[]{ name, surname, middleName },
                new boolean[]{ STRING, STRING, STRING },
                columns.getName("ID") + " = " + id);
    }

    public String updateContactsString(){
        return SqlHelper.updateQuery(UsersTable.table,
                new String[]{ columns.getName("PHONE"), columns.getName("EMAIL") },
                new Object[]{ phone, email },
                new boolean[]{ STRING, STRING },
                columns.getName("ID") + " = " + id);
    }

    public String updatePasswordString(){
        return SqlHelper.updateQuery(UsersTable.table,
                new String[]{ columns.getName("PASSWORD") },
                new Object[]{ password },
                new boolean[]{ STRING },
                columns.getName("ID") + " = " + id);
    }
}