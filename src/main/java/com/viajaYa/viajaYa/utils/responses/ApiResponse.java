package com.viajaYa.viajaYa.utils.responses;

public class ApiResponse<T> {

    private boolean respuestaExitosa;
    private String mensaje;
    private T data;

    public ApiResponse(T data) {
        this.respuestaExitosa = true;
        this.mensaje = "Operación exitosa";
        this.data = data;
    }

    public ApiResponse() {
        this.respuestaExitosa = true;
    }

    public boolean isRespuestaExitosa() {
        return respuestaExitosa;
    }

    public void setRespuestaExitosa(boolean respuestaExitosa) {
        this.respuestaExitosa = respuestaExitosa;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}