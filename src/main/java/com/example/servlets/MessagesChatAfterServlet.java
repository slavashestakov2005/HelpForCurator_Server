/**
 * Сервлет возвращает сообщения чата.
 * **/

package com.example.servlets;

import com.example.Helper;
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
        /** Получение данных. **/
        int id_chat = Integer.parseInt(request.getParameter("id_chat"));
        String time = request.getParameter("time");
        String queryTime = Helper.getCurrentTimeAsMicroseconds();
        /** Запросы и ответ. **/
        ArrayList<Message> messages = MessageTable.getLastMessages(id_chat, time);
        response.setContentType(Helper.ANSWER_HTML_TEXT);
        PrintWriter pw = response.getWriter();
        pw.println(queryTime + " | ");
        for(int i = 0; i < messages.size(); ++i){
            pw.println(messages.get(i).getText().length() + " | " + messages.get(i).toString() + " | ");
        }
    }
}
