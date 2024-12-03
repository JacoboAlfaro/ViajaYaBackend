package com.viajaYa.viajaYa.services.patterns.factory;

import com.viajaYa.viajaYa.models.FacturaModel;
import com.viajaYa.viajaYa.models.dtos.FacturaDTO;

import java.util.Date;

public class FacturaFactory {
    public static FacturaModel crearFactura(FacturaDTO facturaDTO) {
        FacturaModel factura = new FacturaModel();
        factura.setFecha(new Date());
        factura.setTotal(facturaDTO.getTotal());
        factura.setMetodoPago(facturaDTO.getMetodoPago());
        factura.setEstadoPago(facturaDTO.getEstadoPago());
        return factura;
    }
}
