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
public class Get_chatsServlet extends HttpServlet implements LongPull {
    String id, time, queryTime;
    ArrayList<Chat> chats;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        new LongPullServlet(this).execute(request, response);
    }

    @Override
    public void init(HttpServletRequest request) {
        id = request.getParameter("id");
        time = request.getParameter("time");
        queryTime = null;
        chats = null;
    }

    @Override
    public void pullBody() {
        queryTime = Helper.getCurrentTimeAsMicroseconds();
        chats = ChatUserTable.selectChatsForUser(Integer.parseInt(id), time);
    }

    @Override
    public boolean endLongPull() {
        return chats != null && chats.size() > 0;
    }

    @Override
    public void answer(HttpServletResponse response) throws IOException {
        response.setContentType(Helper.ANSWER_HTML_TEXT);
        PrintWriter pw = response.getWriter();
        pw.println(queryTime + " | ");
        if (chats == null) return;
        for(int i = 0; i < chats.size(); ++i){
            pw.println(chats.get(i).toString() + " | ");
        }
    }
}
