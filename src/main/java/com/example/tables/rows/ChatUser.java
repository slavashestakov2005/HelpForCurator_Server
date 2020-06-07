/**
 * Строка таблицы tables.ChatUserTable.
 * */

package com.example.tables.rows;

import java.io.Serializable;

public class ChatUser implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id, id_user;
    private String time;

    public ChatUser() {}

    public ChatUser(int id, int id_user, String time) {
        this.id = id;
        this.id_user = id_user;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String toString(){
        return "" + id + " | " + id_user;
    }
}
