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

@WebServlet("/create")
public class CreateServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String middlename = request.getParameter("middlename");

        User user = new User(login, password, phone, email, name, surname, middlename);

        String passwordFromDataBase = UsersTable.passwordFromLogin(login);
        if (!passwordFromDataBase.equals("")){
            response.setContentType("text/html;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.println("error");
        }
        else{
            UsersTable.insert(user);
            int id = UsersTable.select(login).getId();
            response.setContentType("text/html;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.println(id);
        }
        //getServletContext().getRequestDispatcher("/create.jsp").forward(request, response);
    }
}
