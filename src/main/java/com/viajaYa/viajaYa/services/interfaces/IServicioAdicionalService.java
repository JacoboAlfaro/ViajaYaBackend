package com.viajaYa.viajaYa.services.interfaces;

import com.viajaYa.viajaYa.models.ServicioAdicionalModel;
import com.viajaYa.viajaYa.models.dtos.ServicioAdicionalDTO;

import java.util.ArrayList;
import java.util.Optional;

public interface IServicioAdicionalService {
    public ArrayList<ServicioAdicionalDTO> getServicios();
    public Optional<ServicioAdicionalDTO> getServicioById(Long id);
    public ServicioAdicionalModel saveServicio(ServicioAdicionalDTO dto);
    public ServicioAdicionalModel updateServicioById(ServicioAdicionalDTO request, Long id);
    public boolean deleteServicioById(Long id);
    public ArrayList<ServicioAdicionalDTO> getServiciosByRelacionId(String tipoProducto, Long relacionId);
}
