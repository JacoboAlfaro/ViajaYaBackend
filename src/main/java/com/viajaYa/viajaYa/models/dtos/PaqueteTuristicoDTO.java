package com.viajaYa.viajaYa.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter @Getter
public class PaqueteTuristicoDTO {
    private Long id;
    private String nombrePaquete;
    private String destino;
    private float precio;
    private String serviciosIncluidos;
    private LocalDate fechaSalida;
    @JsonProperty("idVuelo")
    private Long idVuelo;
    @JsonProperty("idHotel")
    private Long idHotel;
}
