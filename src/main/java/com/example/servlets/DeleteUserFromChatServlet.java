/**
 * Сервлет удаляет пользователя из чата.
 * **/

package com.example.servlets;

import com.example.help.Helper;
import com.example.help.ShortPull;
import com.example.help.ShortPullServlet;
import com.example.tables.ChatUserTable;
import com.example.tables.MessageTable;
import com.example.tables.UsersTable;
import com.example.tables.rows.ChatUser;
import com.example.tables.rows.Message;
import com.example.tables.rows.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete_user_from_chat")
public class DeleteUserFromChatServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        new ShortPullServlet(new Query()).execute(request, response);
    }

    class Query implements ShortPull{
        /** parameters **/
        int param_id_chat, param_id_user;

        @Override
        public void init(HttpServletRequest request) {
            param_id_chat = Integer.parseInt(request.getParameter("id_chat"));
            param_id_user = Integer.parseInt(request.getParameter("id_user"));
        }

        @Override
        public void pullBody(String queryTime) {
            ChatUser chatUser = new ChatUser(param_id_chat, param_id_user, "");
            ChatUserTable.delete(chatUser);
            User user = UsersTable.selectById(param_id_user);
            String text = "Пользователь " + user.getSurname() + " " + user.getName() + " удалился";
            Message message = new Message(param_id_chat, Helper.SYSTEM_ID, text, Helper.getCurrentTimeAsMicroseconds());
            MessageTable.insert(message);
        }

        @Override
        public void answer(HttpServletResponse response, String queryTime) throws IOException {}
    }
}