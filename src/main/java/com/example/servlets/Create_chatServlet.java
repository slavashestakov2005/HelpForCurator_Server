package com.example.servlets;

import com.example.tables.ChatTable;
import com.example.tables.rows.Chat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/create_chat")
public class Create_chatServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");

        String idFromDataBase = ChatTable.idFromName(name);
        if (!idFromDataBase.equals("")){
            response.setContentType("text/html;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.println("error");
        }
        else{
            Chat chat = new Chat(name);
            ChatTable.insert(chat);
            int id = ChatTable.select(name).getId();
            response.setContentType("text/html;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.println(id);
        }
    }
}
