package com.viajaYa.viajaYa.repositories;

import com.viajaYa.viajaYa.models.PaqueteTuristicoModel;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IPaqueteTuristicoRepository extends JpaRepository<PaqueteTuristicoModel, Long> {
    
    List<PaqueteTuristicoModel> findByIdIn(List<Long> id);
    @Query("SELECT p FROM PaqueteTuristicoModel p WHERE p.precio BETWEEN ?1 AND ?2 ")
    List<PaqueteTuristicoModel> findPaqueteTuristicoByPrecio(float precioMin, float precioMax);

    @Query("SELECT p FROM PaqueteTuristicoModel p WHERE YEAR(p.fechaSalida) = ?1 AND MONTH(p.fechaSalida) = ?2 AND DAY(p.fechaSalida) = ?3")
    List<PaqueteTuristicoModel> findPaqueteTuristicoByFecha(int year, int month, int day);

    @Query("SELECT p FROM PaqueteTuristicoModel p WHERE p.precio BETWEEN ?1 AND ?2 AND YEAR(p.fechaSalida) = ?3 AND MONTH(p.fechaSalida) = ?4 AND DAY(p.fechaSalida) = ?5")
    List<PaqueteTuristicoModel> findPaqueteTuristicoByPrecioAndFechaSalida(float precioMin, float precioMax, int year, int month, int day);
    
}