package com.example.servlets;

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
        int id_chat = Integer.parseInt(request.getParameter("id_chat"));
        int count = MessageTable.countOfMessagesInChat(id_chat);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.println("" + count);
    }
}
