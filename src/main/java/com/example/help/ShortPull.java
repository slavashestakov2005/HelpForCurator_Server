/**
 * Интерфейс для классов, в которых должен быть ShortPullServlet (все классы, кроме LongPullServlet).
 * **/

package com.example.help;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ShortPull {
    void init(HttpServletRequest request);
    void pullBody(String queryTime);
    void answer(HttpServletResponse response, String queryTime) throws IOException;
}
