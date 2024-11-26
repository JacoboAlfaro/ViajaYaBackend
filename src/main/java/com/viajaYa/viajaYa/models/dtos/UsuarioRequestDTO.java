package com.viajaYa.viajaYa.models.dtos;

import lombok.Data;

@Data
public class UsuarioRequestDTO {
    private Long id;
    private String  nombre;
    private String  username;
    private String  identificacion;
    private String  direccion;
    private String  correoElectronico;
    private int     rol;
}
