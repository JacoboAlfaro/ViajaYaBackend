package com.viajaYa.viajaYa.repositories;

import com.viajaYa.viajaYa.models.HotelModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IHotelRepository extends JpaRepository<HotelModel, Long> {
    List<HotelModel> findByIdIn(List<Long> id);

    // @Query("SELECT h FROM HotelModel h WHERE h.ciudad = ?1")
    // HotelModel findHotelByCiudad(String ciudad);

    // @Query("SELECT h FROM HotelModel h WHERE h.precioNoche = ?1")
    // HotelModel findHotelByPrecioNoche(float precioNoche);

    // @Query("SELECT h FROM HotelModel h WHERE h.ciudad = ?1 AND h.precio_noche = ?2")
    // HotelModel findHotelByCiudadAndPrecioNoche(String ciudad, float precioNoche);
}
