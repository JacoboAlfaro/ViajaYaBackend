package com.viajaYa.viajaYa.repositorios;

import com.viajaYa.viajaYa.modelos.PaqueteTuristico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPaqueteTuristicoRepository extends JpaRepository<PaqueteTuristico, Integer> {

}
