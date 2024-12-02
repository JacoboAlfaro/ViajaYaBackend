package com.viajaYa.viajaYa.services.mappers;

import com.viajaYa.viajaYa.models.dtos.ServicioAdicionalDTO;
import com.viajaYa.viajaYa.models.dtos.ServicioAdicionalRequestDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ServicioAdicionalRequestDTOMapper implements IMapper<ServicioAdicionalRequestDTO, ServicioAdicionalDTO> {

    //[Aplicando principio DRY]
    @Override
    public ServicioAdicionalDTO toEntity(ServicioAdicionalRequestDTO dto) {
        ServicioAdicionalDTO entity = new ServicioAdicionalDTO();
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
    public ServicioAdicionalRequestDTO toDto(ServicioAdicionalDTO entity) {
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
