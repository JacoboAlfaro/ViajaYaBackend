package com.viajaYa.viajaYa.controllers;

import com.viajaYa.viajaYa.models.ServicioAdicionalModel;
import com.viajaYa.viajaYa.models.dtos.ServicioAdicionalDTO;
import com.viajaYa.viajaYa.models.dtos.ServicioAdicionalRequestDTO;
import com.viajaYa.viajaYa.models.enums.TipoProdcuto;
import com.viajaYa.viajaYa.services.interfaces.IServicioAdicionalService;
import com.viajaYa.viajaYa.services.mappers.IMapper;
import com.viajaYa.viajaYa.utils.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/servicioAdicional")
public class ServicioAdicionalController {

    // [Aplica principio de inversión de dependencias DIP]
    private IServicioAdicionalService servicioAdicionalService;
    private IMapper<ServicioAdicionalRequestDTO, ServicioAdicionalDTO> mapper;

    public ServicioAdicionalController(IServicioAdicionalService servicioAdicionalService,
                                       IMapper<ServicioAdicionalRequestDTO, ServicioAdicionalDTO> mapper) {
        this.servicioAdicionalService = servicioAdicionalService;
        this.mapper = mapper;
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<List<ServicioAdicionalDTO>>> getPaquetesTuristicos(){
        List<ServicioAdicionalDTO> servicios = this.servicioAdicionalService.getServicios();
        ApiResponse<List<ServicioAdicionalDTO>> response = new ApiResponse<>(servicios);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<ServicioAdicionalDTO>> getPaqueteTuristico(@PathVariable("id") Long id){
        ServicioAdicionalDTO servicio = this.servicioAdicionalService.getServicioById(id).get();
        ApiResponse<ServicioAdicionalDTO> response = new ApiResponse<>(servicio);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @PreAuthorize("hasRole(@roles.ROLE_ADMIN)")
    public ResponseEntity<ApiResponse<ServicioAdicionalModel>> savePaqueteTuristico(@RequestBody ServicioAdicionalRequestDTO dto){
        ServicioAdicionalModel servicio = this.servicioAdicionalService.saveServicio(mapper.toEntity(dto));
        ApiResponse<ServicioAdicionalModel> response =  new ApiResponse<>(servicio);
        return ResponseEntity.ok(response);
    }

    @PutMapping(path = "/{id}")
    @PreAuthorize("hasRole(@roles.ROLE_ADMIN)")
    public ResponseEntity<ApiResponse<ServicioAdicionalModel>> updatePaqueteTuristico(@RequestBody ServicioAdicionalRequestDTO request,
                                                                                     @PathVariable("id") Long id){
        ServicioAdicionalModel servicio = this.servicioAdicionalService.updateServicioById(mapper.toEntity(request), id);
        ApiResponse<ServicioAdicionalModel> response = new ApiResponse<>(servicio);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasRole(@roles.ROLE_ADMIN)")
    public ResponseEntity<ApiResponse<String>> deletePaqueteTuristico(@PathVariable("id") Long id){
        boolean respuesta = this.servicioAdicionalService.deleteServicioById(id);
        ApiResponse<String> response = new ApiResponse<>("Se borró el servicio con id " + id);
        return ResponseEntity.ok(response);
    }

    //Metodos para obtener servicicos adicionales por diferentes ids de productos
    @GetMapping(path = "getByVueloId/{vueloId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<ArrayList<ServicioAdicionalRequestDTO>>> getServicioIdsByVueloId(@PathVariable("vueloId") Long vueloId){
        ArrayList<ServicioAdicionalRequestDTO> servicios = this.servicioAdicionalService.getServiciosByRelacionId(TipoProdcuto.vuelos.name(), vueloId);
        ApiResponse<ArrayList<ServicioAdicionalRequestDTO>> response = new ApiResponse<>(servicios);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "getByHotelId/{hotelId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<ArrayList<ServicioAdicionalRequestDTO>>> getServicioIdsByHotelId(@PathVariable("hotelId") Long hotelId){
        ArrayList<ServicioAdicionalRequestDTO> servicios = this.servicioAdicionalService.getServiciosByRelacionId(TipoProdcuto.hoteles.name(), hotelId);
        ApiResponse<ArrayList<ServicioAdicionalRequestDTO>> response = new ApiResponse<>(servicios);
        return ResponseEntity.ok(response);
    }
    @GetMapping(path = "getByPaqueteId/{paqueteTuristicoId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<ArrayList<ServicioAdicionalRequestDTO>>> getServicioIdsByPaqueteTuristicoId(@PathVariable("paqueteTuristicoId") Long paqueteTuristicoId){
        ArrayList<ServicioAdicionalRequestDTO> servicios = this.servicioAdicionalService.getServiciosByRelacionId(TipoProdcuto.paqueteTuristicos.name(), paqueteTuristicoId);
        ApiResponse<ArrayList<ServicioAdicionalRequestDTO>> response = new ApiResponse<>(servicios);
        return ResponseEntity.ok(response);
    }
}
