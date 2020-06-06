package com.example.servlets;

import com.example.Helper;
import com.example.tables.ChatUserTable;
import com.example.tables.rows.Chat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/get_chats")
public class Get_chatsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String time = request.getParameter("time");
        String queryTime = Helper.currentTime();
        ArrayList<Chat> chats = ChatUserTable.selectChatsForUser(Integer.parseInt(id), time);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.println(queryTime + " | ");
        for(int i = 0; i < chats.size(); ++i){
            pw.println(chats.get(i).toString() + " | ");
        }
    }
}
