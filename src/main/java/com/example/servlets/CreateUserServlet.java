/**
 * Сервлет создаёт пользователя.
 * Может возвращать "error".
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

@WebServlet("/create_user")
public class CreateUserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        new ShortPullServlet(new Query()).execute(request, response);
    }

    class Query implements ShortPull{
        /** parameters **/
        String param_login, param_password, param_phone, param_email, param_name, param_surname, param_middle_name;
        /** result **/
        String passwordFromDataBase;
        int id;

        @Override
        public void init(HttpServletRequest request) {
            param_login = request.getParameter("login");
            param_password = request.getParameter("password");
            param_phone = request.getParameter("phone");
            param_email = request.getParameter("email");
            param_name = request.getParameter("name");
            param_surname = request.getParameter("surname");
            param_middle_name = request.getParameter("middle_name");
        }

        @Override
        public void pullBody(String queryTime) {
            User user = new User(param_login, param_password, param_phone, param_email, param_name, param_surname, param_middle_name);
            passwordFromDataBase = UsersTable.passwordFromLogin(param_login);
            if (passwordFromDataBase.equals("")){
                UsersTable.insert(user);
                id = UsersTable.select(param_login).getId();
            }
        }

        @Override
        public void answer(HttpServletResponse response, String queryTime) throws IOException {
            response.setContentType(Helper.ANSWER_HTML_TEXT);
            PrintWriter writer = response.getWriter();
            if (passwordFromDataBase.equals("")) writer.println(id);
            else writer.println(Helper.ANSWER_ERROR);
        }
    }
}
