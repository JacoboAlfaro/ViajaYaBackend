package com.viajaYa.viajaYa.models.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReseniaRequestDTO {
    private Long id;
    private LocalDateTime fecha;
    private int calificacion;
    private String comentario;
    private int idProducto;
    private int idReferencia;
    private Long idUsuario;
}
