package com.viajaYa.viajaYa.repositories;

import com.viajaYa.viajaYa.models.FacturaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFacturaRepository extends JpaRepository<FacturaModel, Long> {
}
