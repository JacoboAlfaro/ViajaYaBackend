package com.viajaYa.viajaYa.repositories;

import com.viajaYa.viajaYa.models.PaqueteTuristicoModel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPaqueteTuristicoRepository extends JpaRepository<PaqueteTuristicoModel, Long> {
<<<<<<< HEAD
    List<PaqueteTuristicoModel> findByIdIn(List<Long> id);
    // @Query("SELECT p FROM PaqueteTuristicoModel p WHERE p.precio = ?1")
    // PaqueteTuristicoModel findPaqueteTuristicoByPrecio(float precio);
=======
    @Query("SELECT p FROM PaqueteTuristicoModel p WHERE p.precio = ?1")
    PaqueteTuristicoModel findPaqueteTuristicoByPrecio(float precio);
>>>>>>> smuel

    @Query("SELECT p FROM PaqueteTuristicoModel p WHERE p.fechaSalida = ?1")
    PaqueteTuristicoModel findPaqueteTuristicoByFecha(Date fechaSalida);

    @Query("SELECT p FROM PaqueteTuristicoModel p WHERE p.precio = ?1 AND p.fechaSalida = ?2")
    PaqueteTuristicoModel findPaqueteTuristicoByPrecioAndFechaSalida(float precio, Date fechaSalida);
    
}
