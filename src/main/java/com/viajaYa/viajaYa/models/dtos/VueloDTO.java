package com.viajaYa.viajaYa.models.dtos;

import java.time.LocalDateTime;
import com.viajaYa.viajaYa.models.ClaseServicio;

public class VueloDTO {
    private String numVuelo;
    private String aerolinea;
    private String origen;
    private String destino;
    private LocalDateTime fechaHoraSalida;
    private int numEscalas;
    private String modeloAvion;
    private float precio;    
    private ClaseServicio claseServicio;
    private boolean equipaje;
    
    public String getNumVuelo() {
        return numVuelo;
    }
    public void setNumVuelo(String numVuelo) {
        this.numVuelo = numVuelo;
    }
    public String getAerolinea() {
        return aerolinea;
    }
    public void setAerolinea(String aerolinea) {
        this.aerolinea = aerolinea;
    }
    public String getOrigen() {
        return origen;
    }
    public void setOrigen(String origen) {
        this.origen = origen;
    }
    public String getDestino() {
        return destino;
    }
    public void setDestino(String destino) {
        this.destino = destino;
    }
    public LocalDateTime getFechaHoraSalida() {
        return fechaHoraSalida;
    }
    public void setFechaHoraSalida(LocalDateTime fechaHoraSalida) {
        this.fechaHoraSalida = fechaHoraSalida;
    }
    public int getNumEscalas() {
        return numEscalas;
    }
    public void setNumEscalas(int numEscalas) {
        this.numEscalas = numEscalas;
    }
    public String getModeloAvion() {
        return modeloAvion;
    }
    public void setModeloAvion(String modeloAvion) {
        this.modeloAvion = modeloAvion;
    }
    public float getPrecio() {
        return precio;
    }
    public void setPrecio(float precio) {
        this.precio = precio;
    }
    public ClaseServicio getClaseServicio() {
        return claseServicio;
    }
    public void setClaseServicio(ClaseServicio claseServicio) {
        this.claseServicio = claseServicio;
    }
    public boolean isEquipaje() {
        return equipaje;
    }
    public void setEquipaje(boolean equipaje) {
        this.equipaje = equipaje;
    }

    
}
