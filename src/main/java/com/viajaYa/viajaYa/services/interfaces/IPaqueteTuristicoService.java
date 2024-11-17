package com.viajaYa.viajaYa.services.interfaces;

import com.viajaYa.viajaYa.models.dtos.PaqueteTuristicoDTO;
import com.viajaYa.viajaYa.models.PaqueteTuristicoModel;
import com.viajaYa.viajaYa.models.dtos.PaqueteTuristicoResponseDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface IPaqueteTuristicoService {
    public ArrayList<PaqueteTuristicoResponseDTO> getPaquetes();
    public PaqueteTuristicoModel savePaqueteTuristico(PaqueteTuristicoDTO paquete);
    public Optional<PaqueteTuristicoResponseDTO> getByid(Long id);
    public PaqueteTuristicoModel updateById(PaqueteTuristicoDTO request, Long id);
    public boolean deletePaquete(Long id);
    public List<PaqueteTuristicoModel> getPaqueteById(List<Long> id);
}
