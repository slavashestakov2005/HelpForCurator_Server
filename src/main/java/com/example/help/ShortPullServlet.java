/**
 * Класс для ShortPull запросов. Он:
 * 1. Вызывает метод init(...) для создания long запроса.
 * 2. Вызывает pullBody(String time) для запросов к БД.
 * 3. Вызывает answer(...) для окончания long запроса.
 * **/

package com.example.help;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShortPullServlet {
    private ShortPull shortPull;
    private String queryTime;

    public ShortPullServlet(ShortPull shortPull) {
        this.shortPull = shortPull;
    }

    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        queryTime = Helper.getCurrentTimeAsMicroseconds();
        shortPull.init(request);
        shortPull.pullBody(queryTime);
        shortPull.answer(response, queryTime);
    }
}
