package com.viajaYa.viajaYa.services.patterns.factory;

import com.viajaYa.viajaYa.models.HotelModel;
import com.viajaYa.viajaYa.models.dtos.HotelDTO;

import java.util.ArrayList;

public class HotelFactory {
    public static HotelModel crearHotel(HotelDTO hotelDTO) {
        HotelModel hotel = new HotelModel();
        hotel.setNombreHotel(hotelDTO.getNombreHotel());
        hotel.setPrecioNoche(hotelDTO.getPrecioNoche());
        hotel.setNumEstrellas(hotelDTO.getNumEstrellas());
        hotel.setCiudad(hotelDTO.getCiudad());
        hotel.setPais(hotelDTO.getPais());
        hotel.setServiciosAdicionales(new ArrayList<>());
        return hotel;
    }
}
