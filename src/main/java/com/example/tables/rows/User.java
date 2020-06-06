package com.example.tables.rows;

import java.io.Serializable;
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String login, password, phone, email, name, surname, middlename;

    public User() { }

    public User(String login, String password, String phone, String email, String name, String surname, String middlename) {
        this.login = login;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.middlename = middlename;
    }

    public User(int id, String login, String password, String phone, String email, String name, String surname, String middlename) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.middlename = middlename;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String toString(){
        return "" + id + " | " + login + " | " + password + " | " + phone + " | " + email + " | " + name + " | " + surname + " | " + middlename;
    }
}