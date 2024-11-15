package com.viajaYa.viajaYa.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "servicio_adicional")
public class ServicioAdicionalModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombreServicio;
    @Column
    private String descripcion;
    @Column
    private String terminosCondiciones;
    @Column
    private float precio;
    @Column
    private String categoriaServicio;

    @ManyToMany(mappedBy = "serviciosAdicionales")
    @JsonManagedReference
    private List<PaqueteTuristicoModel> paqueteTuristicos;

    @ManyToMany(mappedBy = "serviciosAdicionales")
    @JsonManagedReference
    private List<HotelModel> hoteles;

    @ManyToMany(mappedBy = "serviciosAdicionales")
    @JsonManagedReference
    private List<VueloModel> vuelos;

    //Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTerminosCondiciones() {
        return terminosCondiciones;
    }

    public void setTerminosCondiciones(String terminosCondiciones) {
        this.terminosCondiciones = terminosCondiciones;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getCategoriaServicio() {
        return categoriaServicio;
    }

    public void setCategoriaServicio(String categoriaServicio) {
        this.categoriaServicio = categoriaServicio;
    }

    public List<PaqueteTuristicoModel> getPaqueteTuristicos() {
        return paqueteTuristicos;
    }

    public void setPaqueteTuristicos(List<PaqueteTuristicoModel> paqueteTuristicos) {
        this.paqueteTuristicos = paqueteTuristicos;
    }

    public List<HotelModel> getHoteles() {
        return hoteles;
    }

    public void setHoteles(List<HotelModel> hoteles) {
        this.hoteles = hoteles;
    }

    public List<VueloModel> getVuelos() {
        return vuelos;
    }

    public void setVuelos(List<VueloModel> vuelos) {
        this.vuelos = vuelos;
    }
}
