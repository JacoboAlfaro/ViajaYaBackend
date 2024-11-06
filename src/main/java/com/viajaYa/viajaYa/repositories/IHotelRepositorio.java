package com.viajaYa.viajaYa.repositories;

import com.viajaYa.viajaYa.models.HotelModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHotelRepositorio extends JpaRepository<HotelModelo, Long> {

}
