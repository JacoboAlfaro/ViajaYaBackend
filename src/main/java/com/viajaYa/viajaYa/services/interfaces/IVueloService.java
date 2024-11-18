package com.viajaYa.viajaYa.services.interfaces;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.viajaYa.viajaYa.models.VueloModel;
import com.viajaYa.viajaYa.models.dtos.VueloDTO;

public interface IVueloService {
    public ArrayList<VueloModel> getVuelos();
    public VueloModel saveVuelo(VueloDTO dto);
    public Optional<VueloModel> getVueloById(Long id);
    public VueloModel updateVueloById(VueloDTO vuelo, Long id);
    public boolean deleteVueloById(Long id);
    public List<VueloModel> getVueloById(List<Long> id);

    public VueloModel findVueloByPrecio(double precio);
    public VueloModel findVueloByFechaHoraSalida(LocalDateTime fechaHoraSalida);
    public VueloModel findVueloByPrecioAndFechaHoraSalida(double precio, LocalDateTime fechaHoraSalida);
}
