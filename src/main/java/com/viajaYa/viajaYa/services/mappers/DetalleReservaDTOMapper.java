package com.viajaYa.viajaYa.services.mappers;

import com.viajaYa.viajaYa.models.DetalleReservaModel;
import com.viajaYa.viajaYa.models.ReservaModel;
import com.viajaYa.viajaYa.models.dtos.DetalleReservaDTO;
import com.viajaYa.viajaYa.services.interfaces.IReservaService;
import com.viajaYa.viajaYa.utils.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DetalleReservaDTOMapper implements IMapper<DetalleReservaDTO, DetalleReservaModel> {

    @Autowired
    private IReservaService reservaService;

    @Override
    public DetalleReservaModel toEntity(DetalleReservaDTO dto) {
        DetalleReservaModel detalleReserva = new DetalleReservaModel();
        detalleReserva.setCantidadP(dto.getCantidadP());
        detalleReserva.setPrecio(dto.getPrecio());
        detalleReserva.setImpuesto(dto.getImpuesto());
        detalleReserva.setDescuento(dto.getDescuento());
        detalleReserva.setTotal(dto.getTotal());
        detalleReserva.setIdProducto(dto.getIdProducto());

        asignarReserva(dto.getIdReserva(), detalleReserva);

        return detalleReserva;
    }

    @Override
    public DetalleReservaDTO toDto(DetalleReservaModel model) {
        DetalleReservaDTO dto = new DetalleReservaDTO();
        dto.setCantidadP(model.getCantidadP());
        dto.setPrecio(model.getPrecio());
        dto.setImpuesto(model.getImpuesto());
        dto.setDescuento(model.getDescuento());
        dto.setTotal(model.getTotal());
        dto.setIdProducto(model.getIdProducto());
        if (model.getReserva() != null) {
            dto.setIdReserva(model.getReserva().getId());
        }
        return dto;
    }

    private void asignarReserva(Long idReserva, DetalleReservaModel detalleReserva) {
        if (idReserva != null) {
            ReservaModel reserva = reservaService.getReservaById(idReserva)
                    .orElseThrow(() -> new BusinessException("Reserva no encontrada"));
            detalleReserva.setReserva(reserva);
        }
    }
}
