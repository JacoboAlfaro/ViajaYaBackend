package com.viajaYa.viajaYa.services.patterns.facade;

import com.viajaYa.viajaYa.utils.responses.ApiResponse;
import org.springframework.stereotype.Component;

//[Aplicando Patron Facade]
@Component
public class ApiResponseFacade implements IApiResponseFacade{
    public <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(data, "Operación exitosa");
    }

    public <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(data, message);
    }

    public ApiResponse<Object> error(String message) {
        ApiResponse<Object> response = new ApiResponse<>();
        response.setRespuestaExitosa(false);
        response.setMensaje(message);
        response.setData(null);
        return response;
    }
}
