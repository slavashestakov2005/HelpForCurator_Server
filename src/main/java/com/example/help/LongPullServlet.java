/**
 * Класс для longPull запросов. Он:
 * 1. Вызывает метод init(...) для создания long запроса.
 * 2. Работает пока временное условие и условие !endLongPull().
 * 3. Каждый раз в цикле вызывает pullBody() и спит 1 секунду.
 * 4. Вызывает answer(...) для окончания long запроса.
 * **/

package com.example.help;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LongPullServlet {
    private LongPull longPull;
    private long startTime;

    public LongPullServlet(LongPull longPull) {
        this.longPull = longPull;
    }

    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        startTime = System.currentTimeMillis();
        long endTime = startTime + 15000;
        longPull.init(request);
        while(endTime > System.currentTimeMillis() && !longPull.endLongPull()){
            longPull.pullBody();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        longPull.answer(response);
    }
}
