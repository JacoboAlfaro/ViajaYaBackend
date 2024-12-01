package com.viajaYa.viajaYa.models.dtos;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ReservaResponseDTO {
    private Long id;
    private boolean estado;
    private Long idUsuario;
    private Date fechaReserva;
    private List<PaqueteTuristicoDTO> paquetes;
    private List<VueloDTO> vuelos;
    private List<HotelDTO> hoteles;
    private boolean confirmada;
    private DetalleReservaDTO detalleReserva;
    private FacturaDTO factura;
}
