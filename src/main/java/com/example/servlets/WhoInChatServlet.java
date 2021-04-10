/**
 * Сервлет возвращает список всех участников чата.
 * **/

package com.example.servlets;

import com.example.help.Helper;
import com.example.help.JsonWriter;
import com.example.help.ShortPull;
import com.example.help.ShortPullServlet;
import com.example.tables.ChatUserTable;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/who_in_chat")
public class WhoInChatServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        new ShortPullServlet(new Query()).execute(request, response);
    }

    class Query implements ShortPull{
        /** parameters **/
        int param_id_chat;
        /** result */
        ArrayList<Integer> users;

        @Override
        public void init(HttpServletRequest request) {
            param_id_chat = Integer.parseInt(request.getParameter("id_chat"));
        }

        @Override
        public void pullBody(String queryTime) {
            users = ChatUserTable.selectUserForChat(param_id_chat);
        }

        @Override
        public void answer(HttpServletResponse response, String queryTime) throws IOException {
            response.setContentType(Helper.ANSWER_JSON_TEXT);
            response.setCharacterEncoding(Helper.CHARSET);
            PrintWriter pw = response.getWriter();
            pw.println(JsonWriter.writeIntegers("users", users));
        }
    }
}
