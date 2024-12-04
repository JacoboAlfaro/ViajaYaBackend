package com.viajaYa.viajaYa.services.patterns.builder;

import com.viajaYa.viajaYa.models.dtos.ReseniaRequestDTO;

import java.time.LocalDateTime;

public class ReseniaRequestBuilder {
    public Long id;
    public LocalDateTime fecha;
    public int calificacion;
    public String comentario;
    public int idProducto;
    public int idReferencia;
    public Long idUsuario;

    public ReseniaRequestBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public ReseniaRequestBuilder fecha(LocalDateTime fecha) {
        this.fecha = fecha;
        return this;
    }

    public ReseniaRequestBuilder calificacion(int calificacion) {
        this.calificacion = calificacion;
        return this;
    }

    public ReseniaRequestBuilder comentario(String comentario) {
        this.comentario = comentario;
        return this;
    }

    public ReseniaRequestBuilder idProducto(int idProducto) {
        this.idProducto = idProducto;
        return this;
    }

    public ReseniaRequestBuilder idReferencia(int idReferencia) {
        this.idReferencia = idReferencia;
        return this;
    }

    public ReseniaRequestBuilder idUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
        return this;
    }

    public ReseniaRequestDTO build() {
        return new ReseniaRequestDTO(this);
    }
}
