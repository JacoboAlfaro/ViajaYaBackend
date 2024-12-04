package com.viajaYa.viajaYa.services.mappers;

import com.viajaYa.viajaYa.models.dtos.ServicioAdicionalDTO;
import com.viajaYa.viajaYa.models.dtos.ServicioAdicionalRequestDTO;
import com.viajaYa.viajaYa.services.patterns.builder.ServicioAdicionalRequestBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ServicioAdicionalRequestDTOMapper implements IMapper<ServicioAdicionalRequestDTO, ServicioAdicionalDTO> {

    //[Aplicando principio DRY]
    @Override
    public ServicioAdicionalDTO toEntity(ServicioAdicionalRequestDTO dto) {
        ServicioAdicionalDTO entity = new ServicioAdicionalDTO(); //[Aplicando principio Creador]
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

        ServicioAdicionalRequestDTO dto = new ServicioAdicionalRequestBuilder() //[Aplicando principio Creador y Builder]
                .id(entity.getId())
                .nombreServicio(entity.getNombreServicio())
                .descripcion(entity.getDescripcion())
                .terminosCondiciones(entity.getTerminosCondiciones())
                .precio(entity.getPrecio())
                .categoriaServicio(entity.getCategoriaServicio())
                .build();

        return dto;
    }
}
