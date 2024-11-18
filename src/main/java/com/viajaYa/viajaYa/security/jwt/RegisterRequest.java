package com.viajaYa.viajaYa.security.jwt;

import com.viajaYa.viajaYa.models.dtos.UsuarioDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String username;
    private String password;
    private int role;
//    private UsuarioDTO user;
}
