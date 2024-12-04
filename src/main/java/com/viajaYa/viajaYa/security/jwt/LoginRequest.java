package com.viajaYa.viajaYa.security.jwt;

import com.viajaYa.viajaYa.services.patterns.builder.LoginRequestBuilder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    private String username;
    private String password;

    public LoginRequest(LoginRequestBuilder loginRequestBuilder) {
        this.username = loginRequestBuilder.username;
        this.password = loginRequestBuilder.password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
