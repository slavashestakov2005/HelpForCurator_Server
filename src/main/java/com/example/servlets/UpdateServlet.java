/**
 * Сервлет обновляет данные пользователя.
 * **/

package com.example.servlets;

import com.example.tables.UsersTable;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /** Получение части данных. **/
        String type = request.getParameter("type");
        int id = Integer.parseInt(request.getParameter("id"));
        /** Получение остальных данных и создание запросов. **/
        switch (type){
            case "name" :
                String name = request.getParameter("name");
                String surname = request.getParameter("surname");
                String middleName = request.getParameter("middlename");
                UsersTable.updateName(id, name, surname, middleName);
                break;
            case "contacts" :
                String phone = request.getParameter("phone");
                String email = request.getParameter("email");
                UsersTable.updateContacts(id, phone, email);
                break;
            case "password" :
                String password = request.getParameter("password");
                UsersTable.updatePassword(id, password);
                break;
        }
    }
}