package com.viajaYa.viajaYa.services.interfaces;

import com.viajaYa.viajaYa.models.FacturaModel;
import com.viajaYa.viajaYa.models.dtos.FacturaDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface IFacturaService {
    public FacturaDTO generateFactura(FacturaDTO factura);
    public ArrayList<FacturaModel> getFacturas();
    public Optional<FacturaModel> getFacturaById(Long id);
    public List<FacturaModel> getFacturasByUsuario(Long idUsuario);
}
