package com.viajaYa.viajaYa.services.interfaces;

import com.viajaYa.viajaYa.models.HotelModel;
import com.viajaYa.viajaYa.models.dtos.HotelDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//Princio SOLID

public interface IHotelService {
    public ArrayList<HotelModel> getHotel();
    public HotelModel saveHotel(HotelDTO dto);
    public Optional<HotelModel> getHotelId(Long id);
    public HotelModel updateHotelId(HotelModel request, Long id);
    public Boolean deleteHotelId(Long id);
<<<<<<< HEAD
    public List<HotelModel> getHotelById(List<Long> id);
=======
    public HotelModel findHotelByCiudad(String ciudad);
    public HotelModel findHotelByPrecioNoche(float precioNoche);
    public HotelModel findHotelByCiudadAndPrecioNoche(String ciudad, float precioNoche);
>>>>>>> smuel
}
