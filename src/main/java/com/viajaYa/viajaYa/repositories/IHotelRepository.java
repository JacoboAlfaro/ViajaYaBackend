package com.viajaYa.viajaYa.repositories;

import com.viajaYa.viajaYa.models.HotelModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHotelRepository extends JpaRepository<HotelModel, Long> {

}
