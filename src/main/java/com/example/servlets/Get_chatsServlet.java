/**
 * Сервлет возвращает чаты пользователя.
 * **/

package com.example.servlets;

import com.example.help.Helper;
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
public class Get_chatsServlet extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        new LongPullServlet(new Query()).execute(request, response);
    }

    class Query implements LongPull{
        /** parameters **/
        String param_time;
        int param_id;
        /** result **/
        ArrayList<Chat> chats;

        public void init(HttpServletRequest request) {
            param_id = Integer.parseInt(request.getParameter("id"));
            param_time = request.getParameter("time");
            chats = null;
        }

        public void pullBody() {
            chats = ChatUserTable.selectChatsForUser(param_id, param_time);
        }

        public boolean endLongPull() {
            return chats != null && chats.size() > 0;
        }

        public void answer(HttpServletResponse response, String queryTime) throws IOException {
            response.setContentType(Helper.ANSWER_HTML_TEXT);
            PrintWriter pw = response.getWriter();
            pw.println(queryTime + " | ");
            if (chats == null) return;
            for(int i = 0; i < chats.size(); ++i){
                pw.println(chats.get(i).toString() + " | ");
            }
        }
    }
}
