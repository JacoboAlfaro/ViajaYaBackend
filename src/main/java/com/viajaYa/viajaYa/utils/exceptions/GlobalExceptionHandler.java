package com.viajaYa.viajaYa.utils.exceptions;

import com.viajaYa.viajaYa.services.patterns.facade.ErrorResponseFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;

//[Se aplica principio DRY]
@ControllerAdvice
public class GlobalExceptionHandler {
    private ErrorResponseFacade errorResponseFacade; //[Aplicando patron FACADE]

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusinessException(BusinessException ex) {
        Map<String, Object> responseBody = ErrorResponseFacade.createErrorResponse(
                HttpStatus.BAD_REQUEST,
                "Bad Request",
                ex.getMessage(),
                "Error con el proceso");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    @ExceptionHandler(TokenException.class)
    public ResponseEntity<Object> handleTokenException(TokenException ex) {
        Map<String, Object> responseBody = ErrorResponseFacade.createErrorResponse(
                HttpStatus.UNAUTHORIZED,
                "Unauthorized",
                ex.getMessage(),
                "Sin autorización");

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        Map<String, Object> responseBody = ErrorResponseFacade.createErrorResponse(
                HttpStatus.NOT_FOUND,
                "Not Found",
                "No se encontró el endpoint: " + ex.getRequestURL(),
                "No se encontró el recurso solicitado");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex) {
        Map<String, Object> responseBody = ErrorResponseFacade.createErrorResponse(
                HttpStatus.FORBIDDEN,
                "Forbidden",
                ex.getMessage(),
                "No tienes permisos para realizar esta acción");

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseBody);
    }

}
