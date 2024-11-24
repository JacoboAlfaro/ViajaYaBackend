package com.viajaYa.viajaYa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.viajaYa.viajaYa.models.UsuarioModel;

import java.util.Optional;

@Repository
public interface IUsuarioRepository extends JpaRepository<UsuarioModel, Long> {
    @Query("SELECT u FROM UsuarioModel u WHERE u.identificacion = ?1")
    Optional<UsuarioModel> findByIdentificacion(@Param("identificacion") String identificacion);

    @Query("SELECT u FROM UsuarioModel u WHERE u.correoElectronico = ?1")
    Optional<UsuarioModel> findByCorreoElectronico(@Param("email") String email);
}
