package com.viajaYa.viajaYa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.viajaYa.viajaYa.models.VueloModel;

@Repository
public interface IVueloRepository extends JpaRepository<VueloModel, Long> {
    

}
