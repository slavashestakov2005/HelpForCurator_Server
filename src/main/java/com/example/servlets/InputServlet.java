package com.example.servlets;

import com.example.tables.UsersTable;
import com.example.tables.rows.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/input")
public class InputServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        String login = request.getParameter("login");
        User user = UsersTable.select(login);
        writer.println(user.toString());
    }
}