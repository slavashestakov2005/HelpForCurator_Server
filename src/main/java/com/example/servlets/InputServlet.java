/**
 * Сервлет возвращает полную информацию пользователя.
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

@WebServlet("/input")
public class InputServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /** Получение данных. **/
        String login = request.getParameter("login");
        /** Запросы и ответ. **/
        User user = UsersTable.select(login);
        response.setContentType(Helper.ANSWER_HTML_TEXT);
        PrintWriter writer = response.getWriter();
        writer.println(user.toString());
    }
}