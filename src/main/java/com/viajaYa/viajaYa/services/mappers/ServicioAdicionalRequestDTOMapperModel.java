package com.viajaYa.viajaYa.services.mappers;

import com.viajaYa.viajaYa.models.ServicioAdicionalModel;
import com.viajaYa.viajaYa.models.dtos.ServicioAdicionalRequestDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ServicioAdicionalRequestDTOMapperModel implements IMapper<ServicioAdicionalRequestDTO, ServicioAdicionalModel> {

    @Override
    public ServicioAdicionalModel toEntity(ServicioAdicionalRequestDTO dto) {
        ServicioAdicionalModel entity = new ServicioAdicionalModel();
        entity.setId(dto.getId());
        entity.setNombreServicio(dto.getNombreServicio());
        entity.setDescripcion(dto.getDescripcion());
        entity.setTerminosCondiciones(dto.getTerminosCondiciones());
        entity.setPrecio(dto.getPrecio());
        entity.setCategoriaServicio(dto.getCategoriaServicio());

        entity.setPaqueteTuristicos(new ArrayList<>());
        entity.setHoteles(new ArrayList<>());
        entity.setVuelos(new ArrayList<>());

        return entity;
    }

    @Override
    public ServicioAdicionalRequestDTO toDto(ServicioAdicionalModel entity) {
        if (entity == null) {
            return null;
        }

        ServicioAdicionalRequestDTO dto = new ServicioAdicionalRequestDTO();
        dto.setId(entity.getId());
        dto.setNombreServicio(entity.getNombreServicio());
        dto.setDescripcion(entity.getDescripcion());
        dto.setTerminosCondiciones(entity.getTerminosCondiciones());
        dto.setPrecio(entity.getPrecio());
        dto.setCategoriaServicio(entity.getCategoriaServicio());
        return dto;
    }
}
