package com.viajaYa.viajaYa.security;

import com.viajaYa.viajaYa.security.jwt.LoginRequest;
import com.viajaYa.viajaYa.security.jwt.RegisterRequest;
import com.viajaYa.viajaYa.utils.responses.AuthResponse;

public interface IAuthService {
    public AuthResponse login(LoginRequest request);
    public AuthResponse register(RegisterRequest request);
}
