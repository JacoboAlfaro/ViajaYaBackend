package com.viajaYa.viajaYa.utils.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    boolean respuestaExitosa;
    String mensaje = "Proceso de token exitoso";
    String token;
}

