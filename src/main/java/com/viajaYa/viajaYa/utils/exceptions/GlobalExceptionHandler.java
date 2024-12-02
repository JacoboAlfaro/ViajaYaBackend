package com.viajaYa.viajaYa.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusinessException(BusinessException ex) {
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("respuestaExitosa", false);
        responseBody.put("mensaje", "Error con el proceso");

        Map<String, Object> errorDetails = new HashMap<>(); //[Aplicando principio Creador]
        errorDetails.put("status", HttpStatus.BAD_REQUEST.value());
        errorDetails.put("title", "Bad Request");
        errorDetails.put("detail", ex.getMessage());
        responseBody.put("errors", errorDetails);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    @ExceptionHandler(TokenException.class)
    public ResponseEntity<Object> handleTokenException(TokenException ex) {
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("respuestaExitosa", false);
        responseBody.put("mensaje", "Error con el proceso");

        Map<String, Object> errorDetails = new HashMap<>(); //[Aplicando principio Creador]
        errorDetails.put("status", HttpStatus.UNAUTHORIZED.value());
        errorDetails.put("title", "Unauthorized");
        errorDetails.put("detail", ex.getMessage());
        responseBody.put("errors", errorDetails);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("respuestaExitosa", false);
        responseBody.put("mensaje", "La ruta solicitada no existe");

        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("status", HttpStatus.NOT_FOUND.value());
        errorDetails.put("title", "Not Found");
        errorDetails.put("detail", "No se encontró el endpoint: " + ex.getRequestURL());
        responseBody.put("errors", errorDetails);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex) {
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("respuestaExitosa", false);
        responseBody.put("mensaje", "No tienes permisos para realizar esta acción");

        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("status", HttpStatus.FORBIDDEN.value());
        errorDetails.put("title", "Forbidden");
        errorDetails.put("detail", ex.getMessage());
        responseBody.put("errors", errorDetails);

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseBody);
    }

}
