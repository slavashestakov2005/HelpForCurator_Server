/**
 * Строка таблицы tables.MessageTable.
 * */

package com.example.tables.rows;

import java.io.Serializable;

public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id_chat, id_author;
    private String text, time;

    public Message() {}

    public Message(int id_chat, int id_author, String text, String time) {
        this.id_chat = id_chat;
        this.id_author = id_author;
        this.text = text;
        this.time = time;
    }

    public int getId_chat() {
        return id_chat;
    }

    public void setId_chat(int id_chat) {
        this.id_chat = id_chat;
    }

    public int getId_author() {
        return id_author;
    }

    public void setId_author(int id_author) {
        this.id_author = id_author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String toString(){
        return "" + id_chat + " | " + id_author + " | " + time + " | " + text;
    }
}
