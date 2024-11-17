package com.viajaYa.viajaYa.services.mappers;

import com.viajaYa.viajaYa.models.ReservaModel;
import com.viajaYa.viajaYa.models.UsuarioModel;
import com.viajaYa.viajaYa.models.VueloModel;
import com.viajaYa.viajaYa.models.dtos.ReservaDTO;
import com.viajaYa.viajaYa.services.interfaces.IUsuarioService;
import com.viajaYa.viajaYa.utils.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReservaDTOMapper implements IMapper<ReservaDTO, ReservaModel> {

    @Autowired
    private IUsuarioService usuarioService;

    @Override
    public ReservaModel toEntity(ReservaDTO dto) {
        ReservaModel reserva = new ReservaModel();
        reserva.setEstado(dto.isEstado());
        reserva.setFechaReserva(dto.getFechaReserva());

        asignarUsuario(dto.getIdUsuario(), reserva);

        return reserva;
    }

    @Override
    public ReservaDTO toDto(ReservaModel model) {
        ReservaDTO dto = new ReservaDTO();
        dto.setEstado(model.isEstado());
        dto.setFechaReserva(model.getFechaReserva());
        if (model.getUsuario() != null) {
            dto.setIdUsuario(model.getUsuario().getId());
        }
        return dto;
    }


    private void asignarUsuario(Long idUsuario, ReservaModel reserva) {
        if (idUsuario != null) {
            UsuarioModel usuario = usuarioService.getUsuarioById(idUsuario)
                    .orElseThrow(() -> new BusinessException("Usuario no encontrado"));
            reserva.setUsuario(usuario);
        }
    }

}
