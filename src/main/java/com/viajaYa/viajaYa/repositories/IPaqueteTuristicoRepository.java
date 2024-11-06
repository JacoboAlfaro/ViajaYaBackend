package com.viajaYa.viajaYa.repositories;

import com.viajaYa.viajaYa.models.PaqueteTuristicoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPaqueteTuristicoRepository extends JpaRepository<PaqueteTuristicoModel, Integer> {

}
