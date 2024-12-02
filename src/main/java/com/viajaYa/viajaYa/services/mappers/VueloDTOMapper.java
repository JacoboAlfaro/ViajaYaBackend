package com.viajaYa.viajaYa.services.mappers;

import com.viajaYa.viajaYa.models.ServicioAdicionalModel;
import com.viajaYa.viajaYa.models.dtos.ServicioAdicionalRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.viajaYa.viajaYa.models.VueloModel;
import com.viajaYa.viajaYa.models.dtos.VueloDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VueloDTOMapper implements IMapper<VueloDTO, VueloModel> {

    @Autowired
    private IMapper<ServicioAdicionalRequestDTO, ServicioAdicionalModel> servicioAdicionalDTOMapper;

    //[Aplicando principio DRY]
    @Override
    public VueloModel toEntity(VueloDTO dto) {
        VueloModel vuelo = new VueloModel();
        vuelo.setId(dto.getId());
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
        dto.setId(model.getId());
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
        List<ServicioAdicionalRequestDTO> servicios = model.getServiciosAdicionales().stream()
                .map(servicioAdicionalDTOMapper::toDto)
                .collect(Collectors.toList());
        dto.setServiciosAdicionales(servicios);
        return dto;
    }
}
