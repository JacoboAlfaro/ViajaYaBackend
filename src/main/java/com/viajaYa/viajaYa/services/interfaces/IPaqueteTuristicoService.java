package com.viajaYa.viajaYa.services.interfaces;

import com.viajaYa.viajaYa.models.dtos.PaqueteTuristicoDTO;
import com.viajaYa.viajaYa.models.PaqueteTuristicoModel;

import java.util.ArrayList;
import java.util.Optional;

public interface IPaqueteTuristicoService {
    public ArrayList<PaqueteTuristicoModel> getPaquetes();
    public PaqueteTuristicoModel savePaqueteTuristico(PaqueteTuristicoDTO paquete);
    public Optional<PaqueteTuristicoModel> getByid(Long id);
    public PaqueteTuristicoModel updateById(PaqueteTuristicoDTO request, Long id);
    public boolean deletePaquete(Long id);
}
