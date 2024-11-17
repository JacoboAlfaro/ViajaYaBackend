package com.viajaYa.viajaYa.repositories;

import com.viajaYa.viajaYa.models.ReservaModel;
import com.viajaYa.viajaYa.models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IReservaRepository extends JpaRepository<ReservaModel, Long> {
    List<ReservaModel> findByUsuarioId(Long idUsuario);
    List<ReservaModel> findByUsuario(UsuarioModel usuario);
}
