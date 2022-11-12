package ru.skypro.homework.service.logging;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface LoggingService {

    void handleResponse(HttpServletRequest request, HttpServletResponse response, Object body) throws IOException;

}
