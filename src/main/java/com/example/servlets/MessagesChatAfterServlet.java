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
public class MessagesChatAfterServlet extends HttpServlet implements LongPull {
    int id_chat;
    String time;
    String queryTime;
    ArrayList<Message> messages;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        new LongPullServlet(this).execute(request, response);
    }

    @Override
    public void init(HttpServletRequest request) {
        id_chat = Integer.parseInt(request.getParameter("id_chat"));
        time = request.getParameter("time");
        queryTime = null;
        messages = null;
    }

    @Override
    public void pullBody() {
        queryTime = Helper.getCurrentTimeAsMicroseconds();
        messages = MessageTable.getLastMessages(id_chat, time);
    }

    @Override
    public boolean endLongPull() {
        return messages != null && messages.size() > 0;
    }

    @Override
    public void answer(HttpServletResponse response) throws IOException {
        response.setContentType(Helper.ANSWER_HTML_TEXT);
        PrintWriter pw = response.getWriter();
        pw.println(queryTime + " | ");
        if (messages == null) return;
        for(int i = 0; i < messages.size(); ++i){
            pw.println(messages.get(i).getText().length() + " | " + messages.get(i).toString() + " | ");
        }
    }
}
