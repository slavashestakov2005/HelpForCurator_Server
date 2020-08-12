/**
 * Сервлет возвращает краткую информацию пользователя.
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

@WebServlet("/get_user_info")
public class GetUserInfoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        new ShortPullServlet(new Query()).execute(request, response);
    }

    class Query implements ShortPull{
        /** parameters */
        int param_id_user;
        /** result **/
        User user;

        @Override
        public void init(HttpServletRequest request) {
            param_id_user = Integer.parseInt(request.getParameter("id_user"));
        }

        @Override
        public void pullBody(String queryTime) {
           user = UsersTable.selectOne(param_id_user);
        }

        @Override
        public void answer(HttpServletResponse response, String queryTime) throws IOException {
            response.setContentType(Helper.ANSWER_HTML_TEXT);
            PrintWriter pw = response.getWriter();
            pw.println(user.getName() + " | " + user.getSurname());
        }
    }
}
