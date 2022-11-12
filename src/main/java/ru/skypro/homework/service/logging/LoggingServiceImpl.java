package ru.skypro.homework.service.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class LoggingServiceImpl implements LoggingService {

    private static final Logger logger = LoggerFactory.getLogger(LoggingServiceImpl.class);

    @Override
    public void handleResponse(HttpServletRequest request, HttpServletResponse response, Object body) throws IOException {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("method = ").append(request.getMethod()).append(", ");

        stringBuilder.append("request [ ");
        stringBuilder.append("path = { ").append(request.getRequestURI()).append(" } ], ");

        stringBuilder.append("response [ ");
        stringBuilder.append("body = { ").append(body).append(" }]");

        logger.info("logResponse: {}", stringBuilder);
    }

}
