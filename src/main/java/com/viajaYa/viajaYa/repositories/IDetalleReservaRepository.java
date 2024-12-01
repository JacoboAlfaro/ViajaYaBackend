package com.viajaYa.viajaYa.repositories;

import com.viajaYa.viajaYa.models.DetalleReservaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IDetalleReservaRepository extends JpaRepository<DetalleReservaModel, Long> {
    Optional<DetalleReservaModel> findByReservaId(Long id);
}
