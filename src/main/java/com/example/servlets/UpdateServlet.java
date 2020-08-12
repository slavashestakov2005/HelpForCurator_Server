/**
 * Сервлет обновляет данные пользователя.
 * **/

package com.example.servlets;

import com.example.help.ShortPull;
import com.example.help.ShortPullServlet;
import com.example.tables.UsersTable;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        new ShortPullServlet(new Query()).execute(request, response);
    }

    class Query implements ShortPull {
        /** parameters **/
        String param_type;
        String param_name, param_surname, param_middle_name, param_phone, param_email, param_password;
        int param_id_user;

        @Override
        public void init(HttpServletRequest request) {
            param_type = request.getParameter("type");
            param_id_user = Integer.parseInt(request.getParameter("id_user"));
            switch (param_type){
                case "name" :
                    param_name = request.getParameter("name");
                    param_surname = request.getParameter("surname");
                    param_middle_name = request.getParameter("middle_name");
                    break;
                case "contacts" :
                    param_phone = request.getParameter("phone");
                    param_email = request.getParameter("email");
                    break;
                case "password" :
                    param_password = request.getParameter("password");
                    break;
            }
        }

        @Override
        public void pullBody(String queryTime) {
            switch (param_type){
                case "name":
                    UsersTable.updateName(param_id_user, param_name, param_surname, param_middle_name);
                    break;
                case "contacts":
                    UsersTable.updateContacts(param_id_user, param_phone, param_email);
                    break;
                case "password":
                    UsersTable.updatePassword(param_id_user, param_password);
                    break;
            }
        }

        @Override
        public void answer(HttpServletResponse response, String queryTime) throws IOException {

        }
    }
}