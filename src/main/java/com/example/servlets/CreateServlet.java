/**
 * Сервлет создаёт пользователя.
 * Может возвращать "error".
 * **/

package com.example.servlets;

import com.example.Helper;
import com.example.tables.UsersTable;
import com.example.tables.rows.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/create")
public class CreateServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /** Получение данных. **/
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String middleName = request.getParameter("middlename");
        /** Запросы и ответ. **/
        User user = new User(login, password, phone, email, name, surname, middleName);
        String passwordFromDataBase = UsersTable.passwordFromLogin(login);
        if (!passwordFromDataBase.equals("")){
            response.setContentType(Helper.ANSWER_HTML_TEXT);
            PrintWriter writer = response.getWriter();
            writer.println(Helper.ANSWER_ERROR);
        }
        else{
            UsersTable.insert(user);
            int id = UsersTable.select(login).getId();
            response.setContentType(Helper.ANSWER_HTML_TEXT);
            PrintWriter writer = response.getWriter();
            writer.println(id);
        }
    }
}
