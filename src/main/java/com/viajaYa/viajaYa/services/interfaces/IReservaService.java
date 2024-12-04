package com.viajaYa.viajaYa.services.interfaces;

import com.viajaYa.viajaYa.models.ReservaModel;
import com.viajaYa.viajaYa.models.dtos.ReservaDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface IReservaService {
    public ArrayList<ReservaModel> getReservas();
    public ReservaModel saveReserva(ReservaDTO dto);
    public Optional<ReservaModel> getReservaById(Long id);
    public ReservaModel updateReservaById(ReservaDTO request, Long id);
    public Boolean deleteReservaById(Long id);
    public List<ReservaModel> getReservasByUsuario(Long idUsuario);
    public boolean confirmarReserva(Long idReserva);
    List<ReservaModel> getHistorialReservasByUsuario(Long idUsuario);
    public List<Integer> calcularTotalPorReserva(Long id);
}
