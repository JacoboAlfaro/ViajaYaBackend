package com.viajaYa.viajaYa.repositories;

import com.viajaYa.viajaYa.models.HotelModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IHotelRepository extends JpaRepository<HotelModel, Long> {
    @Query("SELECT h FROM HotelModel h WHERE h.ciudad = ?1")
    HotelModel findHotelByCiudad(String ciudad);

    @Query("SELECT h FROM HotelModel h WHERE h.precioNoche = ?1")
    HotelModel findHotelByPrecioNoche(float precioNoche);

    @Query("SELECT h FROM HotelModel h WHERE h.ciudad = ?1 AND h.precioNoche = ?2")
    HotelModel findHotelByCiudadAndPrecioNoche(String ciudad, float precioNoche);
}
