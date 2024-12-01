package com.viajaYa.viajaYa.services.interfaces;

import com.viajaYa.viajaYa.models.DetalleReservaModel;
import com.viajaYa.viajaYa.models.ReservaModel;
import com.viajaYa.viajaYa.models.dtos.DetalleReservaDTO;

import java.util.ArrayList;

public interface IDetalleReservaService {
    public DetalleReservaModel saveDetalleReserva(DetalleReservaDTO dto);
    public DetalleReservaModel getDetalleReservaByReserva(Long idReserva);
}
