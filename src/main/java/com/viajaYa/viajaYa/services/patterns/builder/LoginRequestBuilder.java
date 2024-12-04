package com.viajaYa.viajaYa.services.patterns.builder;

import com.viajaYa.viajaYa.security.jwt.LoginRequest;

public class LoginRequestBuilder {
    public String username;
    public String password;

    public LoginRequestBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public LoginRequestBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public LoginRequest build() {
        return new LoginRequest(this);
    }
}
