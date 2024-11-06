package com.viajaYa.viajaYa.services.interfaces;

import com.viajaYa.viajaYa.models.HotelModel;

import java.util.ArrayList;
import java.util.Optional;

//Princio SOLID

public interface IHotelService {
    public ArrayList<HotelModel> getHotel();
    public HotelModel saveHotel(HotelModel hotel);
    public Optional<HotelModel> getHotelId(Long id);
    public HotelModel updateHotelId(HotelModel request, Long id);
    public Boolean deleteHotelId(Long id);
}
