/**
 * Сервлет возвращает список всех пользователей.
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
import java.util.ArrayList;

@WebServlet("/all")
public class AllServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        new ShortPullServlet(new Query()).execute(request, response);
    }

    class Query implements ShortPull{
        /** result **/
        ArrayList<User> users;

        @Override
        public void init(HttpServletRequest request) {
            users = null;
        }

        @Override
        public void pullBody(String queryTime) {
            users = UsersTable.select();
        }

        @Override
        public void answer(HttpServletResponse response, String queryTime) throws IOException {
            response.setContentType(Helper.ANSWER_HTML_TEXT);
            PrintWriter pw = response.getWriter();
            pw.println(users.size() + " | ");
            for(int i = 0; i < users.size(); ++i){
                pw.println(users.get(i).toString() + " | ");
            }
        }
    }
}