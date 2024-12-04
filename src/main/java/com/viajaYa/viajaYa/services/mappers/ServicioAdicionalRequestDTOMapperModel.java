package com.viajaYa.viajaYa.services.mappers;

import com.viajaYa.viajaYa.models.ServicioAdicionalModel;
import com.viajaYa.viajaYa.models.dtos.ServicioAdicionalRequestDTO;
import com.viajaYa.viajaYa.services.patterns.builder.ServicioAdicionalRequestBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ServicioAdicionalRequestDTOMapperModel implements IMapper<ServicioAdicionalRequestDTO, ServicioAdicionalModel> {

    @Override
    public ServicioAdicionalModel toEntity(ServicioAdicionalRequestDTO dto) {
        ServicioAdicionalModel entity = new ServicioAdicionalModel(); //[Aplicando principio Creador]
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
