package com.viajaYa.viajaYa.controllers;

import com.viajaYa.viajaYa.models.HotelModel;
import com.viajaYa.viajaYa.models.dtos.HotelDTO;
import com.viajaYa.viajaYa.services.interfaces.IHotelService;
import com.viajaYa.viajaYa.services.interfaces.IProductoServicioService;
import com.viajaYa.viajaYa.services.mappers.IMapper;
import com.viajaYa.viajaYa.utils.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    // [Aplica principio de inversión de dependencias DIP]
    private IHotelService hotelServicio;
    private IProductoServicioService<HotelDTO> productoServicioService;
    private IMapper<HotelDTO, HotelModel> mapper;

    public HotelController(IHotelService hotelServicio,
                           IProductoServicioService<HotelDTO> productoServicioService,
                           IMapper<HotelDTO, HotelModel> mapper){
        this.hotelServicio = hotelServicio;
        this.productoServicioService = productoServicioService;
        this.mapper = mapper;
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<ArrayList<HotelDTO>>> getHoteles(){
        ArrayList<HotelModel> hoteles = this.hotelServicio.getHotel();
        ArrayList<HotelDTO> hotelesDto = hoteles.stream()
                .map(mapper::toDto)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

        ApiResponse<ArrayList<HotelDTO>> response = new ApiResponse<>(hotelesDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<HotelDTO>> getHotelId(@PathVariable("id") Long id){
        Optional<HotelModel> hotel = this.hotelServicio.getHotelId(id);
        ApiResponse<HotelDTO> response = new ApiResponse<>(hotel.map(mapper::toDto).get());
        return ResponseEntity.ok(response);

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
