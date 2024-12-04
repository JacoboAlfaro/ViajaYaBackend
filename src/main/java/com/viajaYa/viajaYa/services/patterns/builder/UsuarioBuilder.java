package com.viajaYa.viajaYa.services.patterns.builder;

import com.viajaYa.viajaYa.models.dtos.UsuarioDTO;

public class UsuarioBuilder {
    public String nombre;
    public String identificacion;
    public String direccion;
    public String correoElectronico;
    public int rol;

    public UsuarioBuilder setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public UsuarioBuilder setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
        return this;
    }

    public UsuarioBuilder setDireccion(String direccion) {
        this.direccion = direccion;
        return this;
    }

    public UsuarioBuilder setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
        return this;
    }

    public UsuarioBuilder setRol(int rol) {
        this.rol = rol;
        return this;
    }

    public UsuarioDTO build() {
        return new UsuarioDTO(this);
    }
}
