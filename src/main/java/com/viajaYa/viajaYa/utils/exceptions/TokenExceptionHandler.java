package com.viajaYa.viajaYa.utils.exceptions;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TokenExceptionHandler {

    public void handle(HttpServletResponse response, TokenException ex) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String responseBody = String.format("""
            {
                "respuestaExitosa": false,
                "mensaje": "Error de autenticación",
                "errors": {
                    "status": %d,
                    "title": "Unauthorized",
                    "detail": "%s"
                }
            }
            """, HttpStatus.UNAUTHORIZED.value(), ex.getMessage());

        response.getWriter().write(responseBody);
    }
}
