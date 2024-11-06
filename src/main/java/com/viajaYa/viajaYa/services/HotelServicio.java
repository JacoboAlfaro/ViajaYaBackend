package com.viajaYa.viajaYa.services;

import com.viajaYa.viajaYa.models.HotelModelo;
import com.viajaYa.viajaYa.repositories.IHotelRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class HotelServicio implements IHotelServicio {

    @Autowired
    IHotelRepositorio hotelRepositorio;

    @Override
    public ArrayList<HotelModelo> getHotel(){
        return (ArrayList<HotelModelo>) hotelRepositorio.findAll();
    }

    @Override
    public HotelModelo saveHotel(HotelModelo hotel){
        return hotelRepositorio.save(hotel);
    }

    @Override
    public Optional<HotelModelo> getHotelId(Long id){
        return hotelRepositorio.findById(id);
    }

    @Override
    public HotelModelo updateHotelId(HotelModelo request, Long id){
        HotelModelo hotel = hotelRepositorio.findById(id).get();

        hotel.setNombreHotel(request.getNombreHotel());
        hotel.setCiudad(request.getCiudad());
        hotel.setDireccion(request.getDireccion());
        hotel.setNumEstrellas(request.getNumEstrellas());
        hotel.setPais(request.getPais());
        hotel.setTipoHabitacion(request.getTipoHabitacion());
        hotel.setPrecioNoche(request.getPrecioNoche());

        hotelRepositorio.save(hotel);

        return hotel;
    }

    @Override
    public Boolean deleteHotelId(Long id){
        try{
            hotelRepositorio.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
