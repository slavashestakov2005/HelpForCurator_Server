/**
 * Сервлет возвращает краткую информацию пользователя.
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

@WebServlet("/get_user_info")
public class GetUserInfoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /** Получение данных. **/
        String id = request.getParameter("id");
        /** Запросы и ответ. **/
        User user = UsersTable.selectOne(Integer.parseInt(id));
        response.setContentType(Helper.ANSWER_HTML_TEXT);
        PrintWriter pw = response.getWriter();
        pw.println(user.getName() + " | " + user.getSurname());
    }
}
