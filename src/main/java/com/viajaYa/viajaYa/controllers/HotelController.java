package com.viajaYa.viajaYa.controllers;

import com.viajaYa.viajaYa.models.HotelModel;
import com.viajaYa.viajaYa.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelServicio;

    @GetMapping("/getHoteles")
    public ArrayList<HotelModel> getHoteles(){
        return this.hotelServicio.getHotel();
    }

    @PostMapping("/saveHotel")
    public HotelModel saveHotel(@RequestBody HotelModel hotel){
        return this.hotelServicio.saveHotel(hotel);
    }


    @GetMapping("/getHotel/{id}")
    public Optional<HotelModel> getHotelId(@PathVariable("id") Long id){
        return this.hotelServicio.getHotelId(id);
    }

    @PutMapping("/updateHotel/{id}")
    public HotelModel updateHotelId(@RequestBody HotelModel request, @PathVariable("id") Long id){
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
