/**
 * Сервлет возвращает полную информацию пользователя.
 * **/

package com.example.servlets;

import com.example.help.Helper;
import com.example.help.ShortPull;
import com.example.help.ShortPullServlet;
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
        new ShortPullServlet(new Query()).execute(request, response);
    }

    class Query implements ShortPull{
        /** parameters */
        String param_login;
        /** result **/
        User user;

        @Override
        public void init(HttpServletRequest request) {
            param_login = request.getParameter("login");
        }

        @Override
        public void pullBody(String queryTime) {
            user = UsersTable.select(param_login);
        }

        @Override
        public void answer(HttpServletResponse response, String queryTime) throws IOException {
            response.setContentType(Helper.ANSWER_HTML_TEXT);
            PrintWriter writer = response.getWriter();
            writer.println(user.toString());
        }
    }
}