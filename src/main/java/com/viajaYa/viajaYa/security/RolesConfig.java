package com.viajaYa.viajaYa.security;

import com.viajaYa.viajaYa.models.enums.Roles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RolesConfig  {

    @Bean
    public com.viajaYa.viajaYa.models.enums.Roles roles() {
        return new Roles();
    }
}
