package com.viajaYa.viajaYa.models;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

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

    @OneToMany(mappedBy = "vuelo", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<PaqueteTuristicoModel> paquetesTuristicos;

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
    public List<PaqueteTuristicoModel> getPaquetesTuristicos() {
        return paquetesTuristicos;
    }
    public void setPaquetesTuristicos(List<PaqueteTuristicoModel> paquetesTuristicos) {
        this.paquetesTuristicos = paquetesTuristicos;
    }
}
