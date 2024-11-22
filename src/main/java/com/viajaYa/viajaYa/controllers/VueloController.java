package com.viajaYa.viajaYa.controllers;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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


    @GetMapping(path = "/porprecio/{preciomin}/{preciomax}")
    public ResponseEntity<ApiResponse<List<VueloModel>>> getVueloByPrecio(@PathVariable("preciomin") double precioMin, @PathVariable("preciomax") double precioMax){
        List<VueloModel> vuelo = this.vueloService.findVueloByPrecio(precioMin, precioMax);
        ApiResponse<List<VueloModel>> response = new ApiResponse<>(vuelo);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/porfechahorasalida/{fechaHoraSalida}")
    public ResponseEntity<ApiResponse<List<VueloModel>>> findVueloByFecha(@PathVariable("fechaHoraSalida") LocalDateTime fechaHoraSalida){
        List<VueloModel> vuelo = this.vueloService.findVueloByFecha(fechaHoraSalida);
        ApiResponse<List<VueloModel>> response = new ApiResponse<>(vuelo);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/porprecioyfechahorasalida/{preciomin}/{preciomax}/{fechaHoraSalida}")
    public ResponseEntity<ApiResponse<List<VueloModel>>> getVueloByPrecioAndFechaHoraSalida(@PathVariable("preciomin") double precioMin,@PathVariable("preciomax") double precioMax, @PathVariable("fechaHoraSalida") LocalDateTime fechaHoraSalida){
        List<VueloModel> vuelo = this.vueloService.findVueloByPrecioAndFecha(precioMin, precioMax, fechaHoraSalida);
        ApiResponse<List<VueloModel>> response = new ApiResponse<>(vuelo);
        return ResponseEntity.ok(response);
    }


    @GetMapping
    public ResponseEntity<ApiResponse<List<VueloModel>>> getVuelos(){
        List<VueloModel> vuelos = this.vueloService.getVuelos();
        ApiResponse<List<VueloModel>> response = new ApiResponse<>(vuelos);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<VueloModel>> getVueloById(@PathVariable("id") Long id){
        Optional<VueloModel> vuelo = this.vueloService.getVueloById(id);
        ApiResponse<VueloModel> response = new ApiResponse<>(vuelo.get());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<VueloModel>> saveVuelo(@RequestBody VueloDTO request){
        VueloModel vuelo = this.vueloService.saveVuelo(request);
        ApiResponse<VueloModel> response = new ApiResponse<>(vuelo);
        return ResponseEntity.ok(response);
    }
    
    @PutMapping(path = "/{id}") 
    public ResponseEntity<ApiResponse<VueloModel>> updateVueloById(@RequestBody VueloDTO request, @PathVariable("id") Long id){
        VueloModel vueloActualizado = this.vueloService.updateVueloById(request, id);
        ApiResponse<VueloModel> response = new ApiResponse<>(vueloActualizado);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<String>> deleteVueloById(@PathVariable("id") Long id){
        @SuppressWarnings("unused")
        boolean respuesta = this.vueloService.deleteVueloById(id);
        ApiResponse<String> response = new ApiResponse<>("Se borro el vuelo con id " + id);
        return ResponseEntity.ok(response);
    }
}
