package com.viajaYa.viajaYa.services.mappers;

import com.viajaYa.viajaYa.models.HotelModel;
import com.viajaYa.viajaYa.models.dtos.HotelDTO;
import org.springframework.stereotype.Component;

@Component
public class HotelDTOMapper implements IMapper<HotelDTO, HotelModel> {

    @Override
    public HotelModel toEntity(HotelDTO dto) {
        HotelModel hotel = new HotelModel();
        hotel.setNombreHotel(dto.getNombreHotel());
        hotel.setCiudad(dto.getCiudad());
        hotel.setPais(dto.getPais());
        hotel.setDireccion(dto.getDireccion());
        hotel.setNumEstrellas(dto.getNumEstrellas());
        hotel.setTipoHabitacion(dto.getTipoHabitacion());
        hotel.setPrecioNoche(dto.getPrecioNoche());

        return hotel;
    }

    @Override
    public HotelDTO toDto(HotelModel model) {
        HotelDTO dto = new HotelDTO();
        dto.setNombreHotel(model.getNombreHotel());
        dto.setCiudad(model.getCiudad());
        dto.setPais(model.getPais());
        dto.setDireccion(model.getDireccion());
        dto.setNumEstrellas(model.getNumEstrellas());
        dto.setTipoHabitacion(model.getTipoHabitacion());
        dto.setPrecioNoche(model.getPrecioNoche());

        return dto;
    }
}
