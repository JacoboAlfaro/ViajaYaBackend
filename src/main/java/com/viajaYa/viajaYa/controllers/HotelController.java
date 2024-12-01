package com.viajaYa.viajaYa.controllers;

import com.viajaYa.viajaYa.models.HotelModel;
import com.viajaYa.viajaYa.models.dtos.HotelDTO;
import com.viajaYa.viajaYa.services.interfaces.IHotelService;
import com.viajaYa.viajaYa.services.interfaces.IProductoServicioService;
import com.viajaYa.viajaYa.services.mappers.HotelDTOMapper;
import com.viajaYa.viajaYa.utils.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    // [Aplica principio de inversión de dependencias DIP]
    private IHotelService hotelServicio;
    private IProductoServicioService<HotelDTO> productoServicioService;
    // [Aplica segregacion de interfaz principio de ISP]
    private HotelDTOMapper mapper;

    public HotelController(IHotelService hotelServicio,
                           IProductoServicioService<HotelDTO> productoServicioService,
                           HotelDTOMapper mapper){
        this.hotelServicio = hotelServicio;
        this.productoServicioService = productoServicioService;
        this.mapper = mapper;
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<List<HotelDTO>>> getHoteles(){
        ArrayList<HotelModel> hoteles = this.hotelServicio.getHotel();
        ApiResponse<List<HotelDTO>> response = new ApiResponse<>(mapper.toDtoList(hoteles));
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<HotelDTO>> getHotelId(@PathVariable("id") Long id) {
        Optional<HotelModel> hotel = this.hotelServicio.getHotelId(id);
        ApiResponse<HotelDTO> response = new ApiResponse<>(hotel.map(mapper::toDto).get());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/porciudad/{ciudad}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<List<HotelDTO>>> getHotelByCiudad(@PathVariable("ciudad") String ciudad){
        List<HotelModel> hoteles = this.hotelServicio.findHotelByCiudad(ciudad);
        ApiResponse<List<HotelDTO>> response = new ApiResponse<>(mapper.toDtoList(hoteles));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/porprecio/{precioNochemin}/{precioNochemax}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<List<HotelDTO>>> getHotelByPrecioNoche(@PathVariable("precioNochemin") float precioNocheMin, @PathVariable("precioNochemax") float precioNocheMax){
        List<HotelModel> hoteles = this.hotelServicio.findHotelByPrecioNoche(precioNocheMin, precioNocheMax);
        ApiResponse<List<HotelDTO>> response = new ApiResponse<>(mapper.toDtoList(hoteles));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/porciudadyprecio/{ciudad}/{precioNochemin}/{precioNochemax}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<List<HotelDTO>>> getHotelByCiudadAndPrecioNoche(@PathVariable("ciudad") String ciudad, @PathVariable("precioNochemin") float precioNocheMin, @PathVariable("precioNochemax") float precioNocheMax){
        List<HotelModel> hoteles = this.hotelServicio.findHotelByCiudadAndPrecioNoche(ciudad, precioNocheMin, precioNocheMax);
        ApiResponse<List<HotelDTO>> response = new ApiResponse<>(mapper.toDtoList(hoteles));
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @PreAuthorize("hasRole(@roles.ROLE_ADMIN)")
    public ResponseEntity<ApiResponse<HotelDTO>> saveHotel(@RequestBody HotelDTO dto){
        HotelModel hotel = this.hotelServicio.saveHotel(dto);
        ApiResponse<HotelDTO> response = new ApiResponse<>(mapper.toDto(hotel));
        return ResponseEntity.ok(response);
    }
    
    @PutMapping(path = "{id}")
    @PreAuthorize("hasRole(@roles.ROLE_ADMIN)")
    public ResponseEntity<ApiResponse<HotelDTO>> updateHotelId(@RequestBody HotelDTO request, @PathVariable("id") Long id){
        HotelModel hotel = this.hotelServicio.updateHotelId(request, id);
        ApiResponse<HotelDTO> response = new ApiResponse<>(mapper.toDto(hotel));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "{id}")
    @PreAuthorize("hasRole(@roles.ROLE_ADMIN)")
    public ResponseEntity<ApiResponse<String>> deleteHotelId(@PathVariable("id") Long id){
        boolean respuesta = this.hotelServicio.deleteHotelId(id);
        ApiResponse<String> response = new ApiResponse<>("Se borro el hotel con id " + id);
        return ResponseEntity.ok(response);
    }

    @PutMapping(path = "addService/{idPaquete}/{idServicio}")
    @PreAuthorize("hasRole(@roles.ROLE_ADMIN)")
    public ResponseEntity<ApiResponse<HotelDTO>> addService(@PathVariable("idPaquete") Long idPaquete,
                                                            @PathVariable("idServicio") Long idServicio){
        HotelDTO servicio = this.productoServicioService.addServicioAdicional(idPaquete, idServicio);
        ApiResponse<HotelDTO> response = new ApiResponse<>(servicio, "Se agrega el servicio con exito al hotel");
        return ResponseEntity.ok(response);
    }

    @PutMapping(path = "removeService/{idPaquete}/{idServicio}")
    @PreAuthorize("hasRole(@roles.ROLE_ADMIN)")
    public ResponseEntity<ApiResponse<HotelDTO>> removeService(@PathVariable("idPaquete") Long idPaquete,
                                                               @PathVariable("idServicio") Long idServicio){
        HotelDTO servicio = this.productoServicioService.removeServicioAdicional(idPaquete, idServicio);
        ApiResponse<HotelDTO> response = new ApiResponse<>(servicio, "Se elimina el servicio con exito del hotel");
        return ResponseEntity.ok(response);
    }
}
