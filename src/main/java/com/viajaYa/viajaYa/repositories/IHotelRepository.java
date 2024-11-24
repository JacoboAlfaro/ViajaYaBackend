package com.viajaYa.viajaYa.repositories;

import com.viajaYa.viajaYa.models.HotelModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IHotelRepository extends JpaRepository<HotelModel, Long> {
    List<HotelModel> findByIdIn(List<Long> id);

    @Query("SELECT h FROM HotelModel h WHERE h.ciudad = ?1")
    List<HotelModel> findHotelByCiudad(String ciudad);

    @Query("SELECT h FROM HotelModel h WHERE h.precioNoche BETWEEN ?1 AND ?2 ")
    List<HotelModel> findHotelByPrecioNoche(float precioNocheMin, float precioNocheMax);

    @Query("SELECT h FROM HotelModel h WHERE h.ciudad = ?1 AND h.precioNoche BETWEEN ?2 AND ?3")
    List<HotelModel> findHotelByCiudadAndPrecioNoche(String ciudad, float precioNocheMin, float precioNocheMax);
}
