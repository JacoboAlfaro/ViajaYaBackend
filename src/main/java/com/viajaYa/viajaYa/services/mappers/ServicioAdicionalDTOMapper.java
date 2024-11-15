package com.viajaYa.viajaYa.services.mappers;

import com.viajaYa.viajaYa.models.PaqueteTuristicoModel;
import com.viajaYa.viajaYa.models.ServicioAdicionalModel;
import com.viajaYa.viajaYa.models.dtos.PaqueteTuristicoDTO;
import com.viajaYa.viajaYa.models.dtos.ServicioAdicionalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ServicioAdicionalDTOMapper implements IMapper<ServicioAdicionalDTO, ServicioAdicionalModel> {

    @Autowired
    IMapper<PaqueteTuristicoDTO, PaqueteTuristicoModel> paqueteTuristicoMapper;
    @Override
    public ServicioAdicionalModel toEntity(ServicioAdicionalDTO dto) {
        ServicioAdicionalModel entity = new ServicioAdicionalModel();
        entity.setId(dto.getId());
        entity.setNombreServicio(dto.getNombreServicio());
        entity.setDescripcion(dto.getDescripcion());
        entity.setTerminosCondiciones(dto.getTerminosCondiciones());
        entity.setPrecio(dto.getPrecio());
        entity.setCategoriaServicio(dto.getCategoriaServicio());

        List<PaqueteTuristicoModel> paquetes = dto.getPaqueteTuristicos().stream()
                .map(paqueteTuristicoMapper::toEntity)
                .collect(Collectors.toList());
        entity.setPaqueteTuristicos(paquetes);

        entity.setHoteles(dto.getHoteles());
        entity.setVuelos(dto.getVuelos());

        return entity;
    }

    @Override
    public ServicioAdicionalDTO toDto(ServicioAdicionalModel entity) {
        if (entity == null) {
            return null;
        }

        ServicioAdicionalDTO dto = new ServicioAdicionalDTO();
        dto.setId(entity.getId());
        dto.setNombreServicio(entity.getNombreServicio());
        dto.setDescripcion(entity.getDescripcion());
        dto.setTerminosCondiciones(entity.getTerminosCondiciones());
        dto.setPrecio(entity.getPrecio());
        dto.setCategoriaServicio(entity.getCategoriaServicio());
        dto.setHoteles(entity.getHoteles());
        dto.setVuelos(entity.getVuelos());

        List<PaqueteTuristicoDTO> paquetes = entity.getPaqueteTuristicos().stream()
                .map(paqueteTuristicoMapper::toDto)
                .collect(Collectors.toList());
        dto.setPaqueteTuristicos(paquetes);

        return dto;
    }
}
