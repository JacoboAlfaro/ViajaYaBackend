package com.viajaYa.viajaYa.models.dtos;

import com.viajaYa.viajaYa.services.patterns.builder.UsuarioBuilder;

public class UsuarioDTO {
    private String  nombre;
    private String  identificacion;
    private String  direccion;
    private String  correoElectronico;
    private int     rol;

    public UsuarioDTO(UsuarioBuilder builder) {
        this.nombre = builder.nombre;
        this.identificacion = builder.identificacion;
        this.direccion = builder.direccion;
        this.correoElectronico = builder.correoElectronico;
        this.rol = builder.rol;
    }

    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }    
}
