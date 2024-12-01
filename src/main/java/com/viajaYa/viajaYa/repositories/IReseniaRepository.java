package com.viajaYa.viajaYa.repositories;

import com.viajaYa.viajaYa.models.ReseniaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface IReseniaRepository extends JpaRepository<ReseniaModel, Long> {
    @Query("SELECT r FROM ReseniaModel r WHERE r.usuario.id = ?1")
    public ArrayList<ReseniaModel> findReseniaByUsuario(Long id);

    @Query("SELECT r FROM ReseniaModel r WHERE r.idProducto = ?1 AND r.idReferencia = ?2")
    public ArrayList<ReseniaModel> findReseniaByProducto(Long idProducto, Long idReferencia);
}
