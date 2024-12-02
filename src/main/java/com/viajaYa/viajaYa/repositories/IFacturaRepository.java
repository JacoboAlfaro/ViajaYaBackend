package com.viajaYa.viajaYa.repositories;

import com.viajaYa.viajaYa.models.FacturaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IFacturaRepository extends JpaRepository<FacturaModel, Long> {
    @Query("SELECT f FROM FacturaModel f WHERE f.reserva.id = ?1")
    FacturaModel getFacturaByReservaId(Long reservaId);

}
