/**
 * Интерфейс для классов, в которых должен быть LongPullServlet.
 * **/

package com.example.help;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface LongPull {
    void init(HttpServletRequest request);
    void pullBody();
    boolean endLongPull();
    void answer(HttpServletResponse response) throws IOException;
}
