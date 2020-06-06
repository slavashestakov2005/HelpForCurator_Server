package com.example.servlets;

import com.example.tables.UsersTable;
import com.example.tables.rows.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/all")
public class AllServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<User> users = UsersTable.select();
        //request.setAttribute("products", products);
        //getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.println(users.size() + " | ");
        for(int i = 0; i < users.size(); ++i){
            pw.println(users.get(i).toString() + " | ");
        }
    }
}