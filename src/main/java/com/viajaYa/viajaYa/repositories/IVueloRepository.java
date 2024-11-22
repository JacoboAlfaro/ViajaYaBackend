package com.viajaYa.viajaYa.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.viajaYa.viajaYa.models.VueloModel;

import java.util.List;

@Repository
public interface IVueloRepository extends JpaRepository<VueloModel, Long> {
    List<VueloModel> findByIdIn(List<Long> id);

    @Query("SELECT v FROM VueloModel v WHERE v.precio BETWEEN ?1 AND ?2 ")
    List<VueloModel> findVueloByPrecio(double precioMin, double precioMax);

    @Query("SELECT v FROM VueloModel v " +
    "WHERE FUNCTION('YEAR', v.fechaHoraSalida) = ?1 " +
    "AND FUNCTION('MONTH', v.fechaHoraSalida) = ?2 " +
    "AND FUNCTION('DAY', v.fechaHoraSalida) = ?3")
    List<VueloModel> findVueloByFecha(int year, int month, int day);
    
    @Query("SELECT v FROM VueloModel v " +
    "WHERE v.precio BETWEEN ?1 AND ?2 " +
    "AND FUNCTION('YEAR', v.fechaHoraSalida) = ?3 " +
    "AND FUNCTION('MONTH', v.fechaHoraSalida) = ?4 " +
    "AND FUNCTION('DAY', v.fechaHoraSalida) = ?5")
    List<VueloModel> findVueloByPrecioAndFecha(double precioMin, double precioMax, int year, int month, int day);

}
