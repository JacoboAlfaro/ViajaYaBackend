package com.viajaYa.viajaYa.services;

import com.viajaYa.viajaYa.models.HotelModelo;

import java.util.ArrayList;
import java.util.Optional;

//Princio SOLID

public interface IHotelServicio {
    public ArrayList<HotelModelo> getHotel();
    public HotelModelo saveHotel(HotelModelo hotel);
    public Optional<HotelModelo> getHotelId(Long id);
    public HotelModelo updateHotelId(HotelModelo request, Long id);
    public Boolean deleteHotelId(Long id);
}
