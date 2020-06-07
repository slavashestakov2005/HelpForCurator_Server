/**
 * Сервлет удаляет пользователя из чата.
 * **/

package com.example.servlets;

import com.example.tables.ChatUserTable;
import com.example.tables.rows.ChatUser;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete_user_from_chat")
public class DeleteUserFromChatServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /** Получение данных. **/
        int id_chat = Integer.parseInt(request.getParameter("id_chat"));
        int id_user = Integer.parseInt(request.getParameter("id_user"));
        /** Запросы и ответ. **/
        ChatUser chatUser = new ChatUser(id_chat, id_user, "");
        ChatUserTable.delete(chatUser);
    }
}