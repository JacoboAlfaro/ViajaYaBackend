package com.viajaYa.viajaYa.controllers;

import com.viajaYa.viajaYa.models.HotelModelo;
import com.viajaYa.viajaYa.services.HotelServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/hotel")
public class HotelControlador {

    @Autowired
    private HotelServicio hotelServicio;

    @GetMapping("/getHoteles")
    public ArrayList<HotelModelo> getHoteles(){
        return this.hotelServicio.getHotel();
    }

    @PostMapping("/saveHotel")
    public HotelModelo saveHotel(@RequestBody HotelModelo hotel){
        return this.hotelServicio.saveHotel(hotel);
    }


    @GetMapping("/getHotel/{id}")
    public Optional<HotelModelo> getHotelId(@PathVariable("id") Long id){
        return this.hotelServicio.getHotelId(id);
    }

    @PutMapping("/updateHotel/{id}")
    public HotelModelo updateHotelId(@RequestBody HotelModelo request, @PathVariable("id") Long id){
        return this.hotelServicio.updateHotelId(request, id);
    }

    @DeleteMapping("/deleteHotel/{id}")
    public String deleteHotelId(@PathVariable("id") Long id){
        boolean ok = this.hotelServicio.deleteHotelId(id);

        if (ok){
            return "Se elimino el hotel con id: " + id;
        } else {
            return "No se elimino el hotel con id: " + id;
        }
    }
}
