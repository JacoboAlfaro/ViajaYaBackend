package com.viajaYa.viajaYa.repositories;

import com.viajaYa.viajaYa.models.PaqueteTuristicoModel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPaqueteTuristicoRepository extends JpaRepository<PaqueteTuristicoModel, Long> {
    List<PaqueteTuristicoModel> findByIdIn(List<Long> id);
    // @Query("SELECT p FROM PaqueteTuristicoModel p WHERE p.precio = ?1")
    // PaqueteTuristicoModel findPaqueteTuristicoByPrecio(float precio);

    // @Query("SELECT p FROM PaqueteTuristicoModel p WHERE p.fecha_salida = ?1")
    // PaqueteTuristicoModel findPaqueteTuristicoByFecha(Date fechaSalida);

    // @Query("SELECT p FROM PaqueteTuristicoModel p WHERE p.precio = ?1 AND p.fecha_salida = ?2")
    // PaqueteTuristicoModel findPaqueteTuristicoByPrecioAndFechaSalida(float precio, Date fechaSalida);
    
}
