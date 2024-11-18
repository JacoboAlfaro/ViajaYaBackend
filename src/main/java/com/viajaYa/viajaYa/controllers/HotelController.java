package com.viajaYa.viajaYa.controllers;

import com.viajaYa.viajaYa.models.HotelModel;
import com.viajaYa.viajaYa.models.VueloModel;
import com.viajaYa.viajaYa.models.dtos.HotelDTO;
import com.viajaYa.viajaYa.models.dtos.PaqueteTuristicoResponseDTO;
import com.viajaYa.viajaYa.models.dtos.VueloDTO;
import com.viajaYa.viajaYa.services.HotelService;
import com.viajaYa.viajaYa.services.interfaces.IProductoServicioService;
import com.viajaYa.viajaYa.services.mappers.IMapper;
import com.viajaYa.viajaYa.utils.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelServicio;
    @Autowired
    private IProductoServicioService<HotelDTO> productoServicioService;
    @Autowired
    IMapper<HotelDTO, HotelModel> mapper;

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
    }

    @PostMapping
    @PreAuthorize("hasRole(Role.ADMIN)")
    public ResponseEntity<ApiResponse<HotelDTO>> saveHotel(@RequestBody HotelDTO dto){
        HotelModel hotel = this.hotelServicio.saveHotel(dto);
        ApiResponse<HotelDTO> response = new ApiResponse<>(mapper.toDto(hotel));
        return ResponseEntity.ok(response);
    }
    
    @PutMapping(path = "{id}")
    @PreAuthorize("hasRole(Role.ADMIN)")
    public ResponseEntity<ApiResponse<HotelDTO>> updateHotelId(@RequestBody HotelDTO request, @PathVariable("id") Long id){
        HotelModel hotel = this.hotelServicio.updateHotelId(request, id);
        ApiResponse<HotelDTO> response = new ApiResponse<>(mapper.toDto(hotel));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "{id}")
    @PreAuthorize("hasRole(Role.ADMIN)")
    public ResponseEntity<ApiResponse<String>> deleteHotelId(@PathVariable("id") Long id){
        @SuppressWarnings("unused")
        boolean respuesta = this.hotelServicio.deleteHotelId(id);
        ApiResponse<String> response = new ApiResponse<>("Se borro el hotel con id " + id);
        return ResponseEntity.ok(response);
    }

    @PutMapping(path = "addService/{idPaquete}/{idServicio}")
    @PreAuthorize("hasRole(Role.ADMIN)")
    public ResponseEntity<ApiResponse<HotelDTO>> addService(@PathVariable("idPaquete") Long idPaquete,
                                                            @PathVariable("idServicio") Long idServicio){
        HotelDTO servicio = this.productoServicioService.addServicioAdicional(idPaquete, idServicio);
        ApiResponse<HotelDTO> response = new ApiResponse<>(servicio, "Se agrega el servicio con exito al hotel");
        return ResponseEntity.ok(response);
    }

    @PutMapping(path = "removeService/{idPaquete}/{idServicio}")
    @PreAuthorize("hasRole(Role.ADMIN)")
    public ResponseEntity<ApiResponse<HotelDTO>> removeService(@PathVariable("idPaquete") Long idPaquete,
                                                               @PathVariable("idServicio") Long idServicio){
        HotelDTO servicio = this.productoServicioService.removeServicioAdicional(idPaquete, idServicio);
        ApiResponse<HotelDTO> response = new ApiResponse<>(servicio, "Se elimina el servicio con exito del hotel");
        return ResponseEntity.ok(response);
    }
}
