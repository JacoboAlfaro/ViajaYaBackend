package com.viajaYa.viajaYa.services;

import com.viajaYa.viajaYa.models.HotelModel;
import com.viajaYa.viajaYa.repositories.IHotelRepository;
import com.viajaYa.viajaYa.services.interfaces.IHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class HotelService implements IHotelService {

    @Autowired
    IHotelRepository hotelRepositorio;

    @Override
    public ArrayList<HotelModel> getHotel(){
        return (ArrayList<HotelModel>) hotelRepositorio.findAll();
    }

    @Override
    public HotelModel saveHotel(HotelModel hotel){
        return hotelRepositorio.save(hotel);
    }

    @Override
    public Optional<HotelModel> getHotelId(Long id){
        return hotelRepositorio.findById(id);
    }

    @Override
    public HotelModel updateHotelId(HotelModel request, Long id){
        HotelModel hotel = hotelRepositorio.findById(id).get();

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
