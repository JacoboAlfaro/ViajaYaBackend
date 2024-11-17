package com.viajaYa.viajaYa.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "usuario")
public class UsuarioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;
    private String identificacion;
    private String contrasena;
    private String direccion;
    private String correoElectronico;
    private int rol;
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<ReservaModel> reservas;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
    public String getContraseña() {
        return contrasena;
    }
    public void setContraseña(String contraseña) {
        this.contrasena = contraseña;
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
    public List<ReservaModel> getReservas() {
        return reservas;
    }
    public void setReservas(List<ReservaModel> reservas) {
        this.reservas = reservas;
    }
}
