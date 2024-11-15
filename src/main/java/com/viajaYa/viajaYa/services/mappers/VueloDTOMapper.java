package com.viajaYa.viajaYa.services.mappers;

import org.springframework.stereotype.Component;

import com.viajaYa.viajaYa.models.VueloModel;
import com.viajaYa.viajaYa.models.dtos.VueloDTO;

@Component
public class VueloDTOMapper implements IMapper<VueloDTO, VueloModel> {

    @Override
    public VueloModel toEntity(VueloDTO dto) {
        VueloModel vuelo = new VueloModel();
        vuelo.setNumVuelo(dto.getNumVuelo());
        vuelo.setAerolinea(dto.getAerolinea());
        vuelo.setOrigen(dto.getOrigen());
        vuelo.setDestino(dto.getDestino());
        vuelo.setFechaHoraSalida(dto.getFechaHoraSalida());
        vuelo.setNumEscalas(dto.getNumEscalas());
        vuelo.setModeloAvion(dto.getModeloAvion());
        vuelo.setPrecio(dto.getPrecio());
        vuelo.setClaseServicio(dto.getClaseServicio());
        vuelo.setEquipaje(dto.isEquipaje());
        return vuelo;
    }

    @Override
    public VueloDTO toDto(VueloModel model) {
        VueloDTO dto = new VueloDTO();
        dto.setNumVuelo(model.getNumVuelo());
        dto.setAerolinea(model.getAerolinea());
        dto.setOrigen(model.getOrigen());
        dto.setDestino(model.getDestino());
        dto.setFechaHoraSalida(model.getFechaHoraSalida());
        dto.setNumEscalas(model.getNumEscalas());
        dto.setModeloAvion(model.getModeloAvion());
        dto.setPrecio(model.getPrecio());
        dto.setClaseServicio(model.getClaseServicio());
        dto.setEquipaje(model.isEquipaje());
        return dto;
    }
}
