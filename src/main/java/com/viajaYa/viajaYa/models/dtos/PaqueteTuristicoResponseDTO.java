package com.viajaYa.viajaYa.models.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter @Setter
public class PaqueteTuristicoResponseDTO {
    private Long id;
    private String nombrePaquete;
    private String destino;
    private float precio;
    private String serviciosIncluidos;
    private LocalDate fechaSalida;
    private Long idVuelo;
    private Long idHotel;
    private List<ServicioAdicionalRequestDTO> serviciosAdicionales;
}
