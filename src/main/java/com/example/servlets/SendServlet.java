/**
 * Сервлет добавляет сообщения в чат.
 * **/

package com.example.servlets;

import com.example.help.ShortPull;
import com.example.help.ShortPullServlet;
import com.example.tables.MessageTable;
import com.example.tables.rows.Message;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/send")
public class SendServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        new ShortPullServlet(new Query()).execute(request, response);
    }

    class Query implements ShortPull{
        /** parameters **/
        int param_id_chat, param_id_user;
        String param_text;

        @Override
        public void init(HttpServletRequest request) {
            param_id_chat = Integer.parseInt(request.getParameter("id_chat"));
            param_id_user = Integer.parseInt(request.getParameter("id_user"));
            param_text = request.getParameter("text");
        }

        @Override
        public void pullBody(String queryTime) {
            Message message = new Message(param_id_chat, param_id_user, param_text, queryTime);
            MessageTable.insert(message);
        }

        @Override
        public void answer(HttpServletResponse response, String queryTime) throws IOException {

        }
    }
}
