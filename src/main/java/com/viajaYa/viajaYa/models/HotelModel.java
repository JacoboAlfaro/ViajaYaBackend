package com.viajaYa.viajaYa.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "hotel")
public class HotelModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nombreHotel;
    private String ciudad;
    private String pais;
    private String direccion;
    private int numEstrellas;
    private String tipoHabitacion;
    private float precioNoche;

    @OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<PaqueteTuristicoModel> paquetesTuristicos;

    @ManyToMany
    @JsonBackReference
    @JoinTable(
            name = "hotel_servicio_adicional",
            joinColumns = @JoinColumn(name = "id_hotel"),
            inverseJoinColumns = @JoinColumn(name = "id_servicio")
    )
    private List<ServicioAdicionalModel> serviciosAdicionales;

    //Getters Y Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombreHotel() {
        return nombreHotel;
    }

    public void setNombreHotel(String nombreHotel) {
        this.nombreHotel = nombreHotel;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getNumEstrellas() {
        return numEstrellas;
    }

    public void setNumEstrellas(int numEstrellas) {
        this.numEstrellas = numEstrellas;
    }

    public String getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(String tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public float getPrecioNoche() {
        return precioNoche;
    }

    public void setPrecioNoche(float precioNoche) {
        this.precioNoche = precioNoche;
    }

    public List<PaqueteTuristicoModel> getPaquetesTuristicos() {
        return paquetesTuristicos;
    }

    public void setPaquetesTuristicos(List<PaqueteTuristicoModel> paquetesTuristicos) {
        this.paquetesTuristicos = paquetesTuristicos;
    }

    public List<ServicioAdicionalModel> getServiciosAdicionales() {
        return serviciosAdicionales;
    }

    public void setServiciosAdicionales(List<ServicioAdicionalModel> serviciosAdicionales) {
        this.serviciosAdicionales = serviciosAdicionales;
    }
}