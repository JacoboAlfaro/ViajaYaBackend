package com.viajaYa.viajaYa.services.patterns.builder;

import com.viajaYa.viajaYa.models.dtos.ServicioAdicionalRequestDTO;

public class ServicioAdicionalRequestBuilder {
    public Long id;
    public String nombreServicio;
    public String descripcion;
    public String terminosCondiciones;
    public float precio;
    public String categoriaServicio;

    public ServicioAdicionalRequestBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public ServicioAdicionalRequestBuilder nombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
        return this;
    }

    public ServicioAdicionalRequestBuilder descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public ServicioAdicionalRequestBuilder terminosCondiciones(String terminosCondiciones) {
        this.terminosCondiciones = terminosCondiciones;
        return this;
    }

    public ServicioAdicionalRequestBuilder precio(float precio) {
        this.precio = precio;
        return this;
    }

    public ServicioAdicionalRequestBuilder categoriaServicio(String categoriaServicio) {
        this.categoriaServicio = categoriaServicio;
        return this;
    }

    public ServicioAdicionalRequestDTO build() {
        return new ServicioAdicionalRequestDTO(this);
    }
}
