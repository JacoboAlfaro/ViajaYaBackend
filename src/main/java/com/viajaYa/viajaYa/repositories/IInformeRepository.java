package com.viajaYa.viajaYa.repositories;

import com.viajaYa.viajaYa.models.ReservaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface IInformeRepository extends JpaRepository<ReservaModel, Long> {
    @Query("SELECT p.nombrePaquete AS nombre, COUNT(r) AS ventas " +
            "FROM ReservaModel r " +
            "JOIN r.paquetesTuristicos p " +
            "WHERE MONTH(r.fechaReserva) = :mes " +
            "AND YEAR(r.fechaReserva) = :anio " +
            "GROUP BY p.nombrePaquete " +
            "ORDER BY ventas DESC")
    List<Map<String, Object>> findPaquetesMasVendidos(@Param("mes") int mes,@Param("anio") int anio);

    @Query("SELECT v.numVuelo AS nombre, COUNT(r) AS ventas " +
            "FROM ReservaModel r " +
            "JOIN r.vuelos v " +
            "WHERE MONTH(r.fechaReserva) = :mes " +
            "AND YEAR(r.fechaReserva) = :anio " +
            "GROUP BY v.numVuelo " +
            "ORDER BY ventas DESC")
    List<Map<String, Object>> findVuelosMasVendidos(@Param("mes") int mes,@Param("anio") int anio);

    @Query("SELECT h.nombreHotel AS nombre, COUNT(r) AS ventas " +
            "FROM ReservaModel r " +
            "JOIN r.hoteles h " +
            "WHERE MONTH(r.fechaReserva) = :mes " +
            "AND YEAR(r.fechaReserva) = :anio " +
            "GROUP BY h.nombreHotel " +
            "ORDER BY ventas DESC")
    List<Map<String, Object>> findHotelesMasVendidos(@Param("mes") int mes,@Param("anio") int anio);
}
