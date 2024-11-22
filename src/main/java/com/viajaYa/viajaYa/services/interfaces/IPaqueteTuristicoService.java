package com.viajaYa.viajaYa.services.interfaces;

import com.viajaYa.viajaYa.models.dtos.PaqueteTuristicoDTO;
import com.viajaYa.viajaYa.models.PaqueteTuristicoModel;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface IPaqueteTuristicoService {
    public ArrayList<PaqueteTuristicoModel> getPaquetes();
    public PaqueteTuristicoModel savePaqueteTuristico(PaqueteTuristicoDTO paquete);
    public Optional<PaqueteTuristicoModel> getByid(Long id);
    public PaqueteTuristicoModel updateById(PaqueteTuristicoDTO request, Long id);
    public boolean deletePaquete(Long id);
    public List<PaqueteTuristicoModel> getPaqueteById(List<Long> id);
    public List<PaqueteTuristicoModel> findPaqueteTuristicoByPrecio(float precioMin, float precioMax);
    public List<PaqueteTuristicoModel> findPaqueteTuristicoByFecha(LocalDate fechaSalida);
    public List<PaqueteTuristicoModel> findPaqueteTuristicoByPrecioAndFechaSalida(float precioMin, float precioMax, LocalDate fechaSalida);
}
