package com.viajaYa.viajaYa.controllers;

import com.viajaYa.viajaYa.models.dtos.PaqueteTuristicoDTO;
import com.viajaYa.viajaYa.models.PaqueteTuristicoModel;
import com.viajaYa.viajaYa.models.dtos.PaqueteTuristicoResponseDTO;
import com.viajaYa.viajaYa.services.interfaces.IPaqueteTuristicoService;
import com.viajaYa.viajaYa.utils.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paqueteTuristico")
public class PaqueteTuristicoController {

    @Autowired
    private IPaqueteTuristicoService paqueteTuristicoService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<PaqueteTuristicoResponseDTO>>> getPaquetesTuristicos(){
        List<PaqueteTuristicoResponseDTO> paquetes = this.paqueteTuristicoService.getPaquetes();
        ApiResponse<List<PaqueteTuristicoResponseDTO>> response = new ApiResponse<>(paquetes);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<PaqueteTuristicoResponseDTO>> getPaqueteTuristico(@PathVariable("id") Long id){
        PaqueteTuristicoResponseDTO paquete = this.paqueteTuristicoService.getByid(id).get();
        ApiResponse<PaqueteTuristicoResponseDTO> response = new ApiResponse<>(paquete);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PaqueteTuristicoModel>> savePaqueteTuristico(@RequestBody PaqueteTuristicoDTO dto){
        PaqueteTuristicoModel paquete = this.paqueteTuristicoService.savePaqueteTuristico(dto);
        ApiResponse<PaqueteTuristicoModel> response =  new ApiResponse<>(paquete);
        return ResponseEntity.ok(response);

    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<PaqueteTuristicoModel>> updatePaqueteTuristico(@RequestBody PaqueteTuristicoDTO request,
                                                                                     @PathVariable("id") Long id){
        PaqueteTuristicoModel paquete = this.paqueteTuristicoService.updateById(request, id);
        ApiResponse<PaqueteTuristicoModel> response = new ApiResponse<>(paquete);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<String>> deletePaqueteTuristico(@PathVariable("id") Long id){
        @SuppressWarnings("unused")
        boolean respuesta = this.paqueteTuristicoService.deletePaquete(id);
        ApiResponse<String> response = new ApiResponse<>("Se borró el paquete con id " + id);
        return ResponseEntity.ok(response);
    }
}
