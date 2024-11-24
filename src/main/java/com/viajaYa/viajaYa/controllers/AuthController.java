package com.viajaYa.viajaYa.controllers;

import com.viajaYa.viajaYa.security.IAuthService;
import com.viajaYa.viajaYa.utils.responses.AuthResponse;
import com.viajaYa.viajaYa.security.jwt.LoginRequest;
import com.viajaYa.viajaYa.security.jwt.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/auth")
public class AuthController {

    // [Aplica principio de inversión de dependencias DIP]
    private final IAuthService authService;

    public AuthController(IAuthService authService) {
        this.authService = authService;
    }

    @PostMapping (value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping (value=  "register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authService.register(request));
    }
}
