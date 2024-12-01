package com.viajaYa.viajaYa.services.mappers;

import com.viajaYa.viajaYa.models.FacturaModel;
import com.viajaYa.viajaYa.models.ReservaModel;
import com.viajaYa.viajaYa.models.dtos.FacturaDTO;
import com.viajaYa.viajaYa.services.interfaces.IReservaService;
import com.viajaYa.viajaYa.utils.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FacturaDTOMapper implements IMapper<FacturaDTO, FacturaModel> {

    @Autowired
    private IReservaService reservaService;


    @Override
    public FacturaModel toEntity(FacturaDTO dto) {
        FacturaModel factura = new FacturaModel();
        factura.setId(dto.getId());
        factura.setFecha(dto.getFecha());
        factura.setTotal(dto.getTotal());
        factura.setMetodoPago(dto.getMetodoPago());
        factura.setEstadoPago(dto.getEstadoPago());
        factura.setXml(dto.getXml());

        asignarReserva(dto.getIdReserva(), factura);

        return factura;
    }

    @Override
    public FacturaDTO toDto(FacturaModel model) {
        FacturaDTO dto = new FacturaDTO();
        dto.setId(model.getId());
        dto.setFecha(model.getFecha());
        dto.setTotal(model.getTotal());
        dto.setMetodoPago(model.getMetodoPago());
        dto.setEstadoPago(model.getEstadoPago());
        dto.setXml(model.getXml());
        if (model.getReserva() != null) {
            dto.setIdReserva(model.getReserva().getId());
        }
        return dto;
    }

    private void asignarReserva(Long idReserva, FacturaModel factura) {
        if (idReserva != null) {
            ReservaModel reserva = reservaService.getReservaById(idReserva)
                    .orElseThrow(() -> new BusinessException("Reserva no encontrada"));
            factura.setReserva(reserva);
        }
    }
}
