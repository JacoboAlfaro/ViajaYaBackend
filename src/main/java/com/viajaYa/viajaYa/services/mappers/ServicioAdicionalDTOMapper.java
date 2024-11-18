package com.viajaYa.viajaYa.services.mappers;

import com.viajaYa.viajaYa.models.HotelModel;
import com.viajaYa.viajaYa.models.PaqueteTuristicoModel;
import com.viajaYa.viajaYa.models.ServicioAdicionalModel;
import com.viajaYa.viajaYa.models.VueloModel;
import com.viajaYa.viajaYa.models.dtos.HotelDTO;
import com.viajaYa.viajaYa.models.dtos.PaqueteTuristicoDTO;
import com.viajaYa.viajaYa.models.dtos.ServicioAdicionalDTO;
import com.viajaYa.viajaYa.models.dtos.VueloDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ServicioAdicionalDTOMapper implements IMapper<ServicioAdicionalDTO, ServicioAdicionalModel> {

    @Autowired
    IMapper<PaqueteTuristicoDTO, PaqueteTuristicoModel> paqueteTuristicoMapper;
    @Autowired
    IMapper<HotelDTO, HotelModel> hotelMapper;
    @Autowired
    IMapper<VueloDTO, VueloModel> vueloMapper;

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
                .toList();
        entity.setPaqueteTuristicos(paquetes);

        List<HotelModel> hoteles =dto.getHoteles().stream()
                .map(hotelMapper::toEntity)
                .toList();
        entity.setHoteles(hoteles);

        List<VueloModel> vuelos = dto.getVuelos().stream()
                .map(vueloMapper::toEntity)
                .toList();
        entity.setVuelos(vuelos);

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


        List<PaqueteTuristicoDTO> paquetes = entity.getPaqueteTuristicos().stream()
                .map(paqueteTuristicoMapper::toDto)
                .toList();
        dto.setPaqueteTuristicos(paquetes);

        List<HotelDTO> hoteles = entity.getHoteles().stream()
                .map(hotelMapper::toDto)
                .toList();

        dto.setHoteles(hoteles);

        List<VueloDTO> vuelos = entity.getVuelos().stream()
                .map(vueloMapper::toDto)
                .toList();
        dto.setVuelos(vuelos);

        return dto;
    }
}
