package ru.skypro.homework.component.interceptor;

import org.jetbrains.annotations.NotNull;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import ru.skypro.homework.service.logging.LoggingService;

import java.io.IOException;

@ControllerAdvice
public class ResponseLoggingInterceptor implements ResponseBodyAdvice<Object> {

    private final LoggingService loggingService;

    public ResponseLoggingInterceptor(LoggingService loggingService) {
        this.loggingService = loggingService;
    }

    @Override
    public boolean supports(
            @NotNull MethodParameter returnType,
            @NotNull Class<? extends HttpMessageConverter<?>> converterType
    ) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(
            Object body,
            @NotNull MethodParameter returnType,
            @NotNull MediaType selectedContentType,
            @NotNull Class<? extends HttpMessageConverter<?>> selectedConverterType,
            @NotNull ServerHttpRequest request,
            @NotNull ServerHttpResponse response
    ) {
        try {
            loggingService.handleResponse(
                    ((ServletServerHttpRequest) request).getServletRequest(),
                    ((ServletServerHttpResponse) response).getServletResponse(),
                    body
            );
        } catch (IOException ignored) {
        }

        return body;
    }

}
