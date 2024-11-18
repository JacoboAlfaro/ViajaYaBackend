package com.viajaYa.viajaYa.controllers;


import java.util.List;
import java.util.Optional;

import com.viajaYa.viajaYa.models.dtos.PaqueteTuristicoResponseDTO;
import com.viajaYa.viajaYa.services.interfaces.IProductoServicioService;
import com.viajaYa.viajaYa.services.mappers.IMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.viajaYa.viajaYa.models.VueloModel;
import com.viajaYa.viajaYa.models.dtos.VueloDTO;
import com.viajaYa.viajaYa.services.interfaces.IVueloService;
import com.viajaYa.viajaYa.utils.responses.ApiResponse;

@RestController
@RequestMapping(path = "vuelo")
public class VueloController { 

    @Autowired
    private IVueloService vueloService;
    @Autowired
    private IProductoServicioService<VueloDTO> productoServicioService;
    @Autowired
    IMapper<VueloDTO, VueloModel> mapper;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<List<VueloModel>>> getVuelos(){
        List<VueloModel> vuelos = this.vueloService.getVuelos();
        ApiResponse<List<VueloModel>> response = new ApiResponse<>(vuelos);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<VueloDTO>> getVueloById(@PathVariable("id") Long id){
        Optional<VueloModel> vuelo = this.vueloService.getVueloById(id);
        ApiResponse<VueloDTO> response = new ApiResponse<>(vuelo.map(mapper::toDto).get());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @PreAuthorize("hasRole(Role.ADMIN)")
    public ResponseEntity<ApiResponse<VueloModel>> saveVuelo(@RequestBody VueloDTO request){
        VueloModel vuelo = this.vueloService.saveVuelo(request);
        ApiResponse<VueloModel> response = new ApiResponse<>(vuelo);
        return ResponseEntity.ok(response);
    }
    
    @PutMapping(path = "/{id}")
    @PreAuthorize("hasRole(Role.ADMIN)")
    public ResponseEntity<ApiResponse<VueloModel>> updateVueloById(@RequestBody VueloDTO request, @PathVariable("id") Long id){
        VueloModel vueloActualizado = this.vueloService.updateVueloById(request, id);
        ApiResponse<VueloModel> response = new ApiResponse<>(vueloActualizado);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasRole(Role.ADMIN)")
    public ResponseEntity<ApiResponse<String>> deleteVueloById(@PathVariable("id") Long id){
        @SuppressWarnings("unused")
        boolean respuesta = this.vueloService.deleteVueloById(id);
        ApiResponse<String> response = new ApiResponse<>("Se borro el vuelo con id " + id);
        return ResponseEntity.ok(response);
    }

    @PutMapping(path = "addService/{idPaquete}/{idServicio}")
    @PreAuthorize("hasRole(Role.ADMIN)")
    public ResponseEntity<ApiResponse<VueloDTO>> addService(@PathVariable("idPaquete") Long idPaquete,
                                                            @PathVariable("idServicio") Long idServicio){
        VueloDTO servicio = this.productoServicioService.addServicioAdicional(idPaquete, idServicio);
        ApiResponse<VueloDTO> response = new ApiResponse<>(servicio, "Se agrega el servicio con exito al vuelo");
        return ResponseEntity.ok(response);
    }

    @PutMapping(path = "removeService/{idPaquete}/{idServicio}")
    @PreAuthorize("hasRole(Role.ADMIN)")
    public ResponseEntity<ApiResponse<VueloDTO>> removeService(@PathVariable("idPaquete") Long idPaquete,
                                                               @PathVariable("idServicio") Long idServicio){
        VueloDTO servicio = this.productoServicioService.removeServicioAdicional(idPaquete, idServicio);
        ApiResponse<VueloDTO> response = new ApiResponse<>(servicio, "Se elimina el servicio con exito del vuelo");
        return ResponseEntity.ok(response);
    }
}
