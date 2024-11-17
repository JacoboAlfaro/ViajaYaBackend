package com.viajaYa.viajaYa.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "reserva")
public class ReservaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private boolean estado;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "id_usuario", nullable = true)
    private UsuarioModel usuario;

    @Column(name = "fecha_reserva", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaReserva;

    @JsonManagedReference
    @ManyToMany
    @JoinTable(
            name = "reserva_paquete",
            joinColumns = @JoinColumn(name = "id_reserva", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_paquete", referencedColumnName = "id")
    )
    private List<PaqueteTuristicoModel> paquetesTuristicos;

    @JsonManagedReference
    @ManyToMany
    @JoinTable(
            name = "reserva_vuelo",
            joinColumns = @JoinColumn(name = "id_reserva", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_vuelo", referencedColumnName = "id")
    )
    private List<VueloModel> vuelos;

    @JsonManagedReference
    @ManyToMany
    @JoinTable(
            name = "reserva_hotel",
            joinColumns = @JoinColumn(name = "id_reserva", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_hotel", referencedColumnName = "id")
    )
    private List<HotelModel> hoteles;

    @Column
    private boolean confirmada;

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

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }

    public boolean isConfirmada() {
        return confirmada;
    }

    public void setConfirmada(boolean confirmada) {
        this.confirmada = confirmada;
    }

    public List<VueloModel> getVuelos() {
        return vuelos;
    }

    public void setVuelos(List<VueloModel> vuelos) {
        this.vuelos = vuelos;
    }

    public List<HotelModel> getHoteles() {
        return hoteles;
    }

    public void setHoteles(List<HotelModel> hoteles) {
        this.hoteles = hoteles;
    }

    public List<PaqueteTuristicoModel> getPaquetesTuristicos() {
        return paquetesTuristicos;
    }

    public void setPaquetesTuristicos(List<PaqueteTuristicoModel> paquetesTuristicos) {
        this.paquetesTuristicos = paquetesTuristicos;
    }
}
