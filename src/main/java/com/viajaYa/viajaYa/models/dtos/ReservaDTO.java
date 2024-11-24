package com.viajaYa.viajaYa.models.dtos;

import java.util.Date;
import java.util.List;

public class ReservaDTO {
    private Long id;
    private boolean estado;
    private Long idUsuario;
    private Date fechaReserva;
    private List<Long> idPaquetes;
    private List<Long> idVuelos;
    private List<Long> idHoteles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Date getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<Long> getIdPaquetes() {
        return idPaquetes;
    }

    public void setIdPaquetes(List<Long> idPaquetes) {
        this.idPaquetes = idPaquetes;
    }

    public List<Long> getIdVuelos() {
        return idVuelos;
    }

    public void setIdVuelos(List<Long> idVuelos) {
        this.idVuelos = idVuelos;
    }

    public List<Long> getIdHoteles() {
        return idHoteles;
    }

    public void setIdHoteles(List<Long> idHoteles) {
        this.idHoteles = idHoteles;
    }
}
