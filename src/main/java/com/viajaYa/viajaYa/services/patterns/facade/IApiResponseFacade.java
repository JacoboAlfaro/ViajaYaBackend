package com.viajaYa.viajaYa.services.patterns.facade;

import com.viajaYa.viajaYa.utils.responses.ApiResponse;

public interface IApiResponseFacade {
    public <T> ApiResponse<T> success(T data);
    public <T> ApiResponse<T> success(T data, String message);
    public ApiResponse<Object> error(String message);
}
