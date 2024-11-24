package com.viajaYa.viajaYa.security;

import com.viajaYa.viajaYa.models.AuthUserModel;
import com.viajaYa.viajaYa.models.UsuarioModel;
import com.viajaYa.viajaYa.repositories.IAuthUserRepository;
import com.viajaYa.viajaYa.repositories.IUsuarioRepository;
import com.viajaYa.viajaYa.security.jwt.*;
import com.viajaYa.viajaYa.utils.exceptions.BusinessException;
import com.viajaYa.viajaYa.utils.responses.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final IAuthUserRepository authUserRepository;
    private final IUsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthResponse login(LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            UserDetails user = authUserRepository.findByUsername(request.getUsername())
                    .orElseThrow(() -> new BusinessException("Usuario o contraseña incorrectos"));

            String token = jwtService.getToken(user);
            return AuthResponse.builder()
                    .respuestaExitosa(true)
                    .mensaje("Token generado exitosamente")
                    .token(token)
                    .build();
        } catch (Exception ex) {
            throw new BusinessException("Usuario o contraseña incorrectos");
        }
    }

    public AuthResponse register(RegisterRequest request) {

        authUserRepository.findByUsername(request.getUsername())
            .ifPresent(u -> {
                throw new BusinessException("El usuario ya se encuentra registrado");
            });

        usuarioRepository.findByIdentificacion(request.getIdentificacion())
            .ifPresent(u -> {
                throw new BusinessException("La identificación ya se encuentra registrada");
            });

        usuarioRepository.findByCorreoElectronico(request.getCorreoElectronico())
            .ifPresent(u -> {
                throw new BusinessException("El correo electronico ya se encuentra registrado");
            });

        //En caso de que el rol no sea 0 o 1, es posible agregar nuevos roles
        if(request.getRole() != 0 && request.getRole() != 1){
            throw new BusinessException("El rol ingresado no es válido");
        }

        UsuarioModel usuario = new UsuarioModel();
        usuario.setNombre(request.getNombre());
        usuario.setIdentificacion(request.getIdentificacion());
        usuario.setDireccion(request.getDireccion());
        usuario.setCorreoElectronico(request.getCorreoElectronico());
        usuario.setRol(request.getRole());

        UsuarioModel savedUsuario = usuarioRepository.save(usuario);

        String encodedPassword = passwordEncoder.encode(request.getPassword());
        AuthUserModel authUser = AuthUserModel.builder()
                .username(request.getUsername())
                .password(encodedPassword)
                .role(request.getRole())
                .usuario(savedUsuario)
                .build();

        authUserRepository.save(authUser);

        return AuthResponse.builder()
                .token(jwtService.getToken(authUser))
                .respuestaExitosa(true)
                .mensaje("Usuario registrado exitosamente")
                .build();
    }
}
