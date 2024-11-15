package com.viajaYa.viajaYa.models.dtos;

import com.viajaYa.viajaYa.models.HotelModel;
import com.viajaYa.viajaYa.models.VueloModel;

import java.util.List;

public class ServicioAdicionalDTO {
    private Long id;
    private String nombreServicio;
    private String descripcion;
    private String terminosCondiciones;
    private float precio;
    private String categoriaServicio;
    private List<PaqueteTuristicoDTO> paqueteTuristicos;
    private List<HotelModel> hoteles;
    private List<VueloModel> vuelos;

    //Getters y setters

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

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

    public String getCategoriaServicio() {
        return categoriaServicio;
    }

    public void setCategoriaServicio(String categoriaServicio) {
        this.categoriaServicio = categoriaServicio;
    }

    public List<PaqueteTuristicoDTO> getPaqueteTuristicos() {
        return paqueteTuristicos;
    }

    public void setPaqueteTuristicos(List<PaqueteTuristicoDTO> paqueteTuristicos) {
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
