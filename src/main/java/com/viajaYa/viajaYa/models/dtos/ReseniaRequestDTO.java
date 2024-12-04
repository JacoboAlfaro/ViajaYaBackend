package com.viajaYa.viajaYa.models.dtos;

import com.viajaYa.viajaYa.services.patterns.builder.ReseniaRequestBuilder;
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

    public ReseniaRequestDTO() {
    }

    public ReseniaRequestDTO(ReseniaRequestBuilder builder) {
        this.id = builder.id;
        this.fecha = builder.fecha;
        this.calificacion = builder.calificacion;
        this.comentario = builder.comentario;
        this.idProducto = builder.idProducto;
        this.idReferencia = builder.idReferencia;
        this.idUsuario = builder.idUsuario;
    }
}
