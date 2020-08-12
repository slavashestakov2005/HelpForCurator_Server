/**
 * Сервлет считает количество сообщений чата.
 * **/

package com.example.servlets;

import com.example.help.Helper;
import com.example.help.ShortPull;
import com.example.help.ShortPullServlet;
import com.example.tables.MessageTable;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/count_message_in_chat")
public class CountMessageInChatServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        new ShortPullServlet(new Query()).execute(request, response);
    }

    class Query implements ShortPull{
        /** parameters **/
        int param_id_chat;
        /** result **/
        int count;

        @Override
        public void init(HttpServletRequest request) {
            param_id_chat = Integer.parseInt(request.getParameter("id_chat"));
        }

        @Override
        public void pullBody(String queryTime) {
            count = MessageTable.countOfMessagesInChat(param_id_chat);
        }

        @Override
        public void answer(HttpServletResponse response, String queryTime) throws IOException {
            response.setContentType(Helper.ANSWER_HTML_TEXT);
            PrintWriter pw = response.getWriter();
            pw.println("" + count);
        }
    }
}
