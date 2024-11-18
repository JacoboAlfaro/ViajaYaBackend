package com.viajaYa.viajaYa.services;

import com.viajaYa.viajaYa.models.HotelModel;
import com.viajaYa.viajaYa.models.dtos.HotelDTO;
import com.viajaYa.viajaYa.repositories.IHotelRepository;
import com.viajaYa.viajaYa.services.interfaces.IHotelService;
import com.viajaYa.viajaYa.services.mappers.IMapper;
import com.viajaYa.viajaYa.utils.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HotelService implements IHotelService {

    @Autowired
    IHotelRepository hotelRepository;
    @Autowired
    IMapper<HotelDTO, HotelModel> mapper;



    public HotelModel findHotelByCiudad(String ciudad){
        return hotelRepository.findHotelByCiudad(ciudad);
    }

    public HotelModel findHotelByPrecioNoche(float precioNoche){
        return hotelRepository.findHotelByPrecioNoche(precioNoche);
    }

    public HotelModel findHotelByCiudadAndPrecioNoche(String ciudad, float precioNoche){
        return hotelRepository.findHotelByCiudadAndPrecioNoche(ciudad, precioNoche);
    }

    @Override
    public ArrayList<HotelModel> getHotel(){
        return (ArrayList<HotelModel>) hotelRepository.findAll();
    }

    @Override
    public HotelModel saveHotel(HotelDTO dto){
        HotelModel hotel = mapper.toEntity(dto);
        return hotelRepository.save(hotel);
    }

    @Override
    public Optional<HotelModel> getHotelId(Long id){
        Optional<HotelModel> hotel = hotelRepository.findById(id);
        if (hotel.isEmpty()){
            throw new BusinessException("Hotel con id " + id + " no encontrado");
        }
        return hotel;
    }

    @Override
    public HotelModel updateHotelId(HotelModel request, Long id){
        HotelModel hotel = hotelRepository.findById(id).get();

        hotel.setNombreHotel(request.getNombreHotel());
        hotel.setCiudad(request.getCiudad());
        hotel.setDireccion(request.getDireccion());
        hotel.setNumEstrellas(request.getNumEstrellas());
        hotel.setPais(request.getPais());
        hotel.setTipoHabitacion(request.getTipoHabitacion());
        hotel.setPrecioNoche(request.getPrecioNoche());

        hotelRepository.save(hotel);

        return hotel;
    }

    @Override
    public Boolean deleteHotelId(Long id){
        Optional<HotelModel> hotel = hotelRepository.findById(id);
        if(hotel.isEmpty()){
            throw new BusinessException("Hotel con id " + id + " no encontrado");
        }
        hotelRepository.deleteById(id);
        return true;
    }

    @Override
    public List<HotelModel> getHotelById(List<Long> id) {
        List<HotelModel> hoteles = hotelRepository.findByIdIn(id);

        if (hoteles.isEmpty()){
            throw new BusinessException("Hotel no encontrado con id " + id);
        }
        return hoteles;
    }


}
