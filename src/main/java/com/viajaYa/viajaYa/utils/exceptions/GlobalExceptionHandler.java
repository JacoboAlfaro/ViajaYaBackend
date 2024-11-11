package com.viajaYa.viajaYa.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusinessException(BusinessException ex) {
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("respuestaExitosa", false);
        responseBody.put("mensaje", "Error con el proceso");

        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("status", HttpStatus.BAD_REQUEST.value());
        errorDetails.put("title", "Bad Request");
        errorDetails.put("detail", ex.getMessage());
        responseBody.put("errors", errorDetails);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

}
