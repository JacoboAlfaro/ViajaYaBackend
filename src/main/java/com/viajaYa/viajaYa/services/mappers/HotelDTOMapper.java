package com.viajaYa.viajaYa.services.mappers;

import com.viajaYa.viajaYa.models.HotelModel;
import com.viajaYa.viajaYa.models.ServicioAdicionalModel;
import com.viajaYa.viajaYa.models.dtos.HotelDTO;
import com.viajaYa.viajaYa.models.dtos.ServicioAdicionalRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class HotelDTOMapper implements IMapper<HotelDTO, HotelModel> {

    @Autowired
    private IMapper<ServicioAdicionalRequestDTO, ServicioAdicionalModel> servicioAdicionalDTOMapper;

    @Override
    public HotelModel toEntity(HotelDTO dto) {
        HotelModel hotel = new HotelModel();
        hotel.setId(dto.getId());
        hotel.setNombreHotel(dto.getNombreHotel());
        hotel.setCiudad(dto.getCiudad());
        hotel.setPais(dto.getPais());
        hotel.setDireccion(dto.getDireccion());
        hotel.setNumEstrellas(dto.getNumEstrellas());
        hotel.setTipoHabitacion(dto.getTipoHabitacion());
        hotel.setPrecioNoche(dto.getPrecioNoche());
        hotel.setServiciosAdicionales(new ArrayList<>());

        return hotel;
    }

    @Override
    public HotelDTO toDto(HotelModel model) {
        HotelDTO dto = new HotelDTO();
        dto.setId(model.getId());
        dto.setNombreHotel(model.getNombreHotel());
        dto.setCiudad(model.getCiudad());
        dto.setPais(model.getPais());
        dto.setDireccion(model.getDireccion());
        dto.setNumEstrellas(model.getNumEstrellas());
        dto.setTipoHabitacion(model.getTipoHabitacion());
        dto.setPrecioNoche(model.getPrecioNoche());
        List<ServicioAdicionalRequestDTO> servicios = model.getServiciosAdicionales().stream()
                .map(servicioAdicionalDTOMapper::toDto)
                .collect(Collectors.toList());
        dto.setServiciosAdicionales(servicios);

        return dto;
    }
}
