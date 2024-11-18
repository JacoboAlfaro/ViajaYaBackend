package com.viajaYa.viajaYa.security;

import com.viajaYa.viajaYa.models.AuthUserModel;
import com.viajaYa.viajaYa.models.UsuarioModel;
import com.viajaYa.viajaYa.models.dtos.UsuarioDTO;
import com.viajaYa.viajaYa.repositories.IAuthUserRepository;
import com.viajaYa.viajaYa.repositories.IUsuarioRepository;
import com.viajaYa.viajaYa.security.jwt.*;
import com.viajaYa.viajaYa.services.mappers.IMapper;
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
public class AuthService {

    private final IAuthUserRepository authUserRepository;
    private final IUsuarioRepository usuarioRepository;
    private final IMapper<UsuarioDTO, UsuarioModel> mapper;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder passwordEncoder;


    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user = authUserRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new BusinessException("Usuario no encontrado"));
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse register(RegisterRequest request) {
//        if (request.getUser() == null) {
//            throw new BusinessException("El usuario es nulo");
//        }
//
//        UsuarioModel userModel = mapper.toEntity(request.getUser());

        String encodedPassword = passwordEncoder.encode(request.getPassword());
        AuthUserModel authUser = AuthUserModel.builder()
            .username(request.getUsername())
            .password(encodedPassword)
            .role(request.getRole())
//                .usuario(userModel)
            .build();

        authUserRepository.findByUsername(authUser.getUsername())
            .ifPresent(u -> {
                throw new BusinessException("El usuario ya existe");
            });
//        usuarioRepository.save(userModel);
//        userModel.setAuthUser(authUser);
        authUserRepository.save(authUser);


        return AuthResponse.builder()
                .token(jwtService.getToken(authUser))
                .build();
    }

}
