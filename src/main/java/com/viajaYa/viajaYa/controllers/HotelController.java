package com.viajaYa.viajaYa.controllers;

import com.viajaYa.viajaYa.models.HotelModel;
import com.viajaYa.viajaYa.models.dtos.HotelDTO;
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


    @GetMapping("/porciudad/{ciudad}")
    public HotelModel getHotelByCiudad(@PathVariable("ciudad") String ciudad){
        return this.hotelServicio.findHotelByCiudad(ciudad);
    }   

    @GetMapping("/porprecio/{precioNoche}")
    public HotelModel getHotelByPrecioNoche(@PathVariable("precioNoche") float precioNoche){
        return this.hotelServicio.findHotelByPrecioNoche(precioNoche);
    }

    @GetMapping("/porciudadyprecio/{ciudad}/{precioNoche}")
    public HotelModel getHotelByCiudadAndPrecioNoche(@PathVariable("ciudad") String ciudad, @PathVariable("precioNoche") float precioNoche){
        return this.hotelServicio.findHotelByCiudadAndPrecioNoche(ciudad, precioNoche);
    }
    

    @GetMapping("/getHoteles")
    public ArrayList<HotelModel> getHoteles(){
        return this.hotelServicio.getHotel();
    }

    @PostMapping
    public HotelModel saveHotel(@RequestBody HotelDTO dto){
        return this.hotelServicio.saveHotel(dto);
    }


    @GetMapping(path = "{id}")
    public Optional<HotelModel> getHotelId(@PathVariable("id") Long id){
        return this.hotelServicio.getHotelId(id);
    }

    @PutMapping(path = "{id}")
    public HotelModel updateHotelId(@RequestBody HotelModel request, @PathVariable("id") Long id){
        return this.hotelServicio.updateHotelId(request, id);
    }

    @DeleteMapping(path = "{id}")
    public String deleteHotelId(@PathVariable("id") Long id){
        boolean ok = this.hotelServicio.deleteHotelId(id);

        if (ok){
            return "Se elimino el hotel con id: " + id;
        } else {
            return "No se elimino el hotel con id: " + id;
        }
    }
}
