package com.viajaYa.viajaYa.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "paquete_turistico")
public class PaqueteTuristicoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombrePaquete;
    @Column
    private String destino;
    @Column
    private float precio;
    @Column
    private String serviciosIncluidos;
    @Column
    private LocalDate fechaSalida;
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "id_vuelo", nullable = true)
    private VueloModel vuelo;
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "id_hotel", nullable = true)
    private HotelModel hotel;
    @JsonBackReference
    @ManyToMany(mappedBy = "paquetesTuristicos")
    private List<ReservaModel> reservas;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombrePaquete() {
        return nombrePaquete;
    }

    public void setNombrePaquete(String nombrePaquete) {
        this.nombrePaquete = nombrePaquete;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getServiciosIncluidos() {
        return serviciosIncluidos;
    }

    public void setServiciosIncluidos(String serviciosIncluidos) {
        this.serviciosIncluidos = serviciosIncluidos;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public VueloModel getVuelo() {
        return vuelo;
    }

    public void setVuelo(VueloModel vuelo) {
        this.vuelo = vuelo;
    }

    public HotelModel getHotel() {
        return hotel;
    }

    public void setHotel(HotelModel hotel) {
        this.hotel = hotel;
    }

    public List<ReservaModel> getReservas() {
        return reservas;
    }

    public void setReservas(List<ReservaModel> reservas) {
        this.reservas = reservas;
    }
}
