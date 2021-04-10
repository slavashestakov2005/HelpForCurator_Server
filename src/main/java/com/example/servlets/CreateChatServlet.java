/**
 * Сервлет создаёт чат.
 * Может возвращать "error".
 * **/

package com.example.servlets;

import com.example.help.Helper;
import com.example.help.ShortPull;
import com.example.help.ShortPullServlet;
import com.example.tables.ChatTable;
import com.example.tables.rows.Chat;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/create_chat")
public class CreateChatServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        new ShortPullServlet(new Query()).execute(request, response);
    }

    class Query implements ShortPull{
        /** parameters **/
        String param_name;
        /** result **/
        int idFromDataBase;

        @Override
        public void init(HttpServletRequest request) {
            param_name = request.getParameter("name");
        }

        @Override
        public void pullBody(String queryTime) {
            idFromDataBase = ChatTable.idFromName(param_name);
        }

        @Override
        public void answer(HttpServletResponse response, String queryTime) throws IOException {
            if (idFromDataBase == 0){
                response.setContentType(Helper.ANSWER_HTML_TEXT);
                PrintWriter writer = response.getWriter();
                writer.println(Helper.ANSWER_ERROR);
            }
            else{
                Chat chat = new Chat(param_name);
                ChatTable.insert(chat);
                int id = ChatTable.idFromName(param_name);
                response.setContentType(Helper.ANSWER_HTML_TEXT);
                PrintWriter writer = response.getWriter();
                writer.println(id);
            }
        }
    }
}
