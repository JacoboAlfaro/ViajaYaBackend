package com.viajaYa.viajaYa.repositories;

import com.viajaYa.viajaYa.models.ProductoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductoRepository extends JpaRepository<ProductoModel, Integer> {
    @Query("SELECT p.id FROM ProductoModel p WHERE p.tipoProducto = :tipoProducto")
    public Long findIdProductoByTipo(@Param("tipoProducto") String tipoProducto);
}
