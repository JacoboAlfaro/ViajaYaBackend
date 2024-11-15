package com.viajaYa.viajaYa.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Date;
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
    private Date fechaSalida;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "id_vuelo", nullable = true)
    private VueloModel vuelo;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "id_hotel", nullable = true)
    private HotelModel hotel;

    @ManyToMany
    @JoinTable(
            name = "paquete_servicio_adicional",
            joinColumns = @JoinColumn(name = "id_paquete"),
            inverseJoinColumns = @JoinColumn(name = "id_servicio")
    )
    private List<ServicioAdicionalModel> serviciosAdicionales;


    //Getters y setters
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

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
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

    public List<ServicioAdicionalModel> getServiciosAdicionales() {
        return serviciosAdicionales;
    }

    public void setServiciosAdicionales(List<ServicioAdicionalModel> serviciosAdicionales) {
        this.serviciosAdicionales = serviciosAdicionales;
    }
}
