package com.viajaYa.viajaYa.models.dtos;

import com.viajaYa.viajaYa.services.patterns.builder.ServicioAdicionalRequestBuilder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ServicioAdicionalRequestDTO {
    private Long id;
    private String nombreServicio;
    private String descripcion;
    private String terminosCondiciones;
    private float precio;
    private String categoriaServicio;

    public ServicioAdicionalRequestDTO() {
    }

    public ServicioAdicionalRequestDTO(ServicioAdicionalRequestBuilder builder) {
        this.id = builder.id;
        this.nombreServicio = builder.nombreServicio;
        this.descripcion = builder.descripcion;
        this.terminosCondiciones = builder.terminosCondiciones;
        this.precio = builder.precio;
        this.categoriaServicio = builder.categoriaServicio;
    }
}
