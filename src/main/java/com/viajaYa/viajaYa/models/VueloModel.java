package com.viajaYa.viajaYa.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vuelo")
public class VueloModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String numVuelo;
    private String aerolinea;
    private String origen;
    private String destino;
    private LocalDateTime fechaHoraSalida;
    private int numEscalas;
    private String modeloAvion;
    private double precio;
    @Enumerated(EnumType.STRING)
    private ClaseServicio claseServicio; // enum  {ECONOMICA, PREMIUM, BUSISNESS}
    private boolean equipaje;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
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
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
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
