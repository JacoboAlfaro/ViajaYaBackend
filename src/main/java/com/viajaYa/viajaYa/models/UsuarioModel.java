package com.viajaYa.viajaYa.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
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

    /*@OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private AuthUserModel authUser;*/

}
