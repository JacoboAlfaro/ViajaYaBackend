package com.viajaYa.viajaYa.repositories;

import com.viajaYa.viajaYa.models.ServicioAdicionalModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface IServicioAdicionalRepository extends JpaRepository<ServicioAdicionalModel, Long>  {
    @Query("SELECT s FROM ServicioAdicionalModel s " +
            "JOIN s.vuelos v " +
            "WHERE v.id = :vueloId")
    ArrayList<ServicioAdicionalModel> findServiciosByVueloId(@Param("vueloId") Long vueloId);

    @Query("SELECT s FROM ServicioAdicionalModel s " +
            "JOIN s.hoteles h " +
            "WHERE h.id = :hotelId")
    ArrayList<ServicioAdicionalModel> findServiciosByHotelId(@Param("hotelId") Long hotelId);

    @Query("SELECT s FROM ServicioAdicionalModel s " +
            "JOIN s.paqueteTuristicos p " +
            "WHERE p.id = :paqueteId")
    ArrayList<ServicioAdicionalModel> findServiciosByPaqueteId(@Param("paqueteId") Long paqueteId);

    /*@Query("SELECT s FROM ServicioAdicionalModel s " +
            "JOIN s.:entidad e " +
            "WHERE e.id = :relacionId")
    ArrayList<ServicioAdicionalModel> findServiciosByRelacion(@Param("entidad") String entidad, @Param("relacionId") Long relacionId)*/;
}
