/**
 * Сервлет возвращает чаты пользователя.
 * **/

package com.example.servlets;

import com.example.help.Helper;
import com.example.help.JsonWriter;
import com.example.help.LongPull;
import com.example.help.LongPullServlet;
import com.example.tables.ChatUserTable;
import com.example.tables.rows.Chat;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/get_chats")
public class GetChatsServlet extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        new LongPullServlet(new Query()).execute(request, response);
    }

    class Query implements LongPull{
        /** parameters **/
        String param_time;
        int param_id_user;
        /** result **/
        ArrayList<Chat> chats;

        public void init(HttpServletRequest request) {
            param_id_user = Integer.parseInt(request.getParameter("id_user"));
            param_time = request.getParameter("time");
            chats = null;
        }

        public void pullBody() {
            chats = ChatUserTable.selectChatsForUser(param_id_user, param_time);
        }

        public boolean endLongPull() {
            return chats != null && chats.size() > 0;
        }

        public void answer(HttpServletResponse response, String queryTime) throws IOException {
            response.setContentType(Helper.ANSWER_JSON_TEXT);
            response.setCharacterEncoding(Helper.CHARSET);
            PrintWriter pw = response.getWriter();
            pw.println("{\n\"time\" : " + queryTime + ",\n");
            pw.println(JsonWriter.writeArray("chats", chats));
            pw.println("\n}");
        }
    }
}
