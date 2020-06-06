package com.example.servlets;

import com.example.Helper;
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
        int id_chat = Integer.parseInt(request.getParameter("id_chat"));
        int id_author = Integer.parseInt(request.getParameter("id_author"));
        String text = request.getParameter("text");
        String time = Helper.currentTime();
        Message message = new Message(id_chat, id_author, text, time);
        MessageTable.insert(message);
        /*response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.println(time);*/
    }
}
