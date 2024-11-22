package com.viajaYa.viajaYa.controllers;

import com.viajaYa.viajaYa.models.HotelModel;
import com.viajaYa.viajaYa.models.dtos.HotelDTO;
import com.viajaYa.viajaYa.services.HotelService;
import com.viajaYa.viajaYa.utils.responses.ApiResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelServicio;


    @GetMapping("/porciudad/{ciudad}")
    public ResponseEntity<ApiResponse<List<HotelModel>>> getHotelByCiudad(@PathVariable("ciudad") String ciudad){
        List<HotelModel> hotel = this.hotelServicio.findHotelByCiudad(ciudad);
        ApiResponse<List<HotelModel>> response = new ApiResponse<>(hotel);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/porprecio/{precioNochemin}/{precioNochemax}")
    public ResponseEntity<ApiResponse<List<HotelModel>>> getHotelByPrecioNoche(@PathVariable("precioNochemin") float precioNocheMin, @PathVariable("precioNochemax") float precioNocheMax){
        List<HotelModel> hotel = this.hotelServicio.findHotelByPrecioNoche(precioNocheMin, precioNocheMax);
        ApiResponse<List<HotelModel>> response = new ApiResponse<>(hotel);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/porciudadyprecio/{ciudad}/{precioNochemin}/{precioNochemax}")
    public ResponseEntity<ApiResponse<List<HotelModel>>> getHotelByCiudadAndPrecioNoche(@PathVariable("ciudad") String ciudad, @PathVariable("precioNochemin") float precioNocheMin, @PathVariable("precioNochemax") float precioNocheMax){
        List<HotelModel> hotel = this.hotelServicio.findHotelByCiudadAndPrecioNoche(ciudad, precioNocheMin, precioNocheMax);
        ApiResponse<List<HotelModel>> response = new ApiResponse<>(hotel);
        return ResponseEntity.ok(response);
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
