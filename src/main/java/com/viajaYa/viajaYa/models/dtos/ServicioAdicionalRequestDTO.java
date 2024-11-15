package com.viajaYa.viajaYa.models.dtos;

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
}
