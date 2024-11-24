package com.viajaYa.viajaYa.services.mappers;

import com.viajaYa.viajaYa.models.HotelModel;
import com.viajaYa.viajaYa.models.PaqueteTuristicoModel;
import com.viajaYa.viajaYa.models.ReservaModel;
import com.viajaYa.viajaYa.models.VueloModel;
import com.viajaYa.viajaYa.models.dtos.*;
import com.viajaYa.viajaYa.services.interfaces.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ReservaResponseDTOMapper implements IMapper<ReservaResponseDTO, ReservaModel> {

    @Autowired
    IUsuarioService usuarioService;
    @Autowired
    IMapper<PaqueteTuristicoDTO, PaqueteTuristicoModel> paqueteMapper;
    @Autowired
    IMapper<VueloDTO, VueloModel> vueloMapper;
    @Autowired
    IMapper<HotelDTO, HotelModel> hotelMapper;

    @Override
    public ReservaModel toEntity(ReservaResponseDTO dto) {
        ReservaModel reserva = new ReservaModel();
        reserva.setId(dto.getId());
        reserva.setEstado(dto.isEstado());
        reserva.setFechaReserva(dto.getFechaReserva());
        reserva.setConfirmada(dto.isConfirmada());
        reserva.setUsuario(usuarioService.getUsuarioById(dto.getIdUsuario()).get());

        reserva.setPaquetesTuristicos(dto.getPaquetes().stream()
                .map(paquete -> paqueteMapper.toEntity(paquete))
                .collect(Collectors.toList()));

        reserva.setVuelos(dto.getVuelos().stream()
                .map(vuelo -> vueloMapper.toEntity(vuelo))
                .collect(Collectors.toList()));

        reserva.setHoteles(dto.getHoteles().stream()
                .map(hotel -> hotelMapper.toEntity(hotel))
                .collect(Collectors.toList()));

        return reserva;
    }

    @Override
    public ReservaResponseDTO toDto(ReservaModel entity) {
        ReservaResponseDTO dto = new ReservaResponseDTO();
        dto.setId(entity.getId());
        dto.setEstado(entity.isEstado());
        dto.setFechaReserva(entity.getFechaReserva());
        dto.setConfirmada(entity.isConfirmada());
        dto.setIdUsuario(entity.getUsuario().getId());

        dto.setPaquetes(entity.getPaquetesTuristicos().stream()
                .map(paquete -> paqueteMapper.toDto(paquete))
                .collect(Collectors.toList()));

        dto.setVuelos(entity.getVuelos().stream()
                .map(vuelo -> vueloMapper.toDto(vuelo))
                .collect(Collectors.toList()));

        dto.setHoteles(entity.getHoteles().stream()
                .map(hotel -> hotelMapper.toDto(hotel))
                .collect(Collectors.toList()));

        return dto;
    }
}
