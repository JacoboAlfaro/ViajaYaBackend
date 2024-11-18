package com.viajaYa.viajaYa.repositories;

import com.viajaYa.viajaYa.models.AuthUserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAuthUserRepository extends JpaRepository<AuthUserModel, Long> {
    Optional<AuthUserModel> findByUsername(String username);
}
