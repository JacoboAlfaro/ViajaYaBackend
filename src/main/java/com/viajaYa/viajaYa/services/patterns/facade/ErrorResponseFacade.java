package com.viajaYa.viajaYa.services.patterns.facade;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class ErrorResponseFacade{

    public static Map<String, Object> createErrorResponse(HttpStatus status, String title, String detail, String mensaje) {
        Map<String, Object> responseBody = new HashMap<>(); //[Aplicando principio Creador]
        responseBody.put("respuestaExitosa", false);
        responseBody.put("mensaje", mensaje);

        Map<String, Object> errorDetails = new HashMap<>(); //[Aplicando principio Creador]
        errorDetails.put("status", status.value());
        errorDetails.put("title", title);
        errorDetails.put("detail", detail);

        responseBody.put("errors", errorDetails);
        return responseBody;
    }
}
