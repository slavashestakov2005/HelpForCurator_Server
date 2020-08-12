/**
 * Сервлет возвращает сообщения чата.
 * **/

package com.example.servlets;

import com.example.help.Helper;
import com.example.help.LongPull;
import com.example.help.LongPullServlet;
import com.example.tables.MessageTable;
import com.example.tables.rows.Message;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/messages_chat_after")
public class MessagesChatAfterServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        new LongPullServlet(new Query()).execute(request, response);
    }

    class Query implements LongPull{
        /** parameters **/
        int param_id_chat;
        String param_time;
        /** answer */
        ArrayList<Message> messages;

        public void init(HttpServletRequest request) {
            param_id_chat = Integer.parseInt(request.getParameter("id_chat"));
            param_time = request.getParameter("time");
            messages = null;
        }

        public void pullBody() {
            messages = MessageTable.getLastMessages(param_id_chat, param_time);
        }

        public boolean endLongPull() {
            return messages != null && messages.size() > 0;
        }

        public void answer(HttpServletResponse response, String queryTime) throws IOException {
            response.setContentType(Helper.ANSWER_HTML_TEXT);
            PrintWriter pw = response.getWriter();
            pw.println(queryTime + " | ");
            if (messages == null) return;
            for(int i = 0; i < messages.size(); ++i){
                pw.println(messages.get(i).getText().length() + " | " + messages.get(i).toString() + " | ");
            }
        }
    }
}
