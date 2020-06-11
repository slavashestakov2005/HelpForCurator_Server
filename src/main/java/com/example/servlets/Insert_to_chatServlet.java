/**
 * Сервлет добавляет пользователя в чат.
 * **/

package com.example.servlets;

import com.example.help.Helper;
import com.example.tables.ChatUserTable;
import com.example.tables.rows.ChatUser;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/insert_to_chat")
public class Insert_to_chatServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /** Получение данных. **/
        int id_chat = Integer.parseInt(request.getParameter("id_chat"));
        int id_user = Integer.parseInt(request.getParameter("id_user"));
        String time = Helper.getCurrentTimeAsMicroseconds();
        /** Запросы и ответ. **/
        ChatUser chatUser = new ChatUser(id_chat, id_user, time);
        ChatUserTable.insert(chatUser);
    }
}
