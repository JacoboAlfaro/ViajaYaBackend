package com.viajaYa.viajaYa.repositories;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.viajaYa.viajaYa.models.VueloModel;

import java.util.List;

@Repository
public interface IVueloRepository extends JpaRepository<VueloModel, Long> {
<<<<<<< HEAD
    List<VueloModel> findByIdIn(List<Long> id);
    // @Query("SELECT v FROM VueloModel v WHERE v.precio = ?1")
    // VueloModel findVueloByPrecio(double precio);
=======
    @Query("SELECT v FROM VueloModel v WHERE v.precio = ?1")
    VueloModel findVueloByPrecio(double precio);
>>>>>>> smuel

    @Query("SELECT v FROM VueloModel v WHERE v.fechaHoraSalida = ?1")
    VueloModel findVueloByFechaHoraSalida(LocalDateTime fechaHoraSalida);

    @Query("SELECT v FROM VueloModel v WHERE v.precio = ?1 AND v.fechaHoraSalida = ?2")
    VueloModel findVueloByPrecioAndFechaHoraSalida(double precio, LocalDateTime fechaHoraSalida);
}
