package com.viajaYa.viajaYa.utils.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, org.springframework.security.access.AccessDeniedException ex) throws IOException {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String responseBody = String.format("""
            {
                "respuestaExitosa": false,
                "mensaje": "No tienes permisos para realizar esta acción",
                "errors": {
                    "status": %d,
                    "title": "Forbidden",
                    "detail": "%s"
                }
            }
            """, HttpStatus.FORBIDDEN.value(), ex.getMessage());

        response.getWriter().write(responseBody);
    }
}