/**
 * Сервлет создаёт чат.
 * Может возвращать "error".
 * **/

package com.example.servlets;

import com.example.Helper;
import com.example.tables.ChatTable;
import com.example.tables.rows.Chat;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/create_chat")
public class Create_chatServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /** Получение данных. **/
        String name = request.getParameter("name");
        /** Запросы и ответ. **/
        String idFromDataBase = ChatTable.idFromName(name);
        if (!idFromDataBase.equals("")){
            response.setContentType(Helper.ANSWER_HTML_TEXT);
            PrintWriter writer = response.getWriter();
            writer.println(Helper.ANSWER_ERROR);
        }
        else{
            Chat chat = new Chat(name);
            ChatTable.insert(chat);
            String id = ChatTable.idFromName(name);
            response.setContentType(Helper.ANSWER_HTML_TEXT);
            PrintWriter writer = response.getWriter();
            writer.println(id);
        }
    }
}
