package com.viajaYa.viajaYa.models.dtos;

import lombok.Data;

import java.util.List;

@Data
public class ServicioAdicionalDTO {
    private Long id;
    private String nombreServicio;
    private String descripcion;
    private String terminosCondiciones;
    private float precio;
    private String categoriaServicio;
    private List<PaqueteTuristicoDTO> paqueteTuristicos;
    private List<HotelDTO> hoteles;
    private List<VueloDTO> vuelos;
}
