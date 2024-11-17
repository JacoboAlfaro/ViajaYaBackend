package com.viajaYa.viajaYa.controllers;


import com.viajaYa.viajaYa.models.ReservaModel;
import com.viajaYa.viajaYa.models.dtos.ReservaDTO;
import com.viajaYa.viajaYa.services.interfaces.IReservaService;
import com.viajaYa.viajaYa.utils.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reserva")
public class ReservaController {

    @Autowired
    private IReservaService reservaService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ReservaModel>>> getReservas(){
        List<ReservaModel> reservas = this.reservaService.getReservas();
        ApiResponse<List<ReservaModel>> response = new ApiResponse<>(reservas);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<ReservaModel>> getReserva(@PathVariable("id") Long id){
        ReservaModel reserva = this.reservaService.getReservaById(id).get();
        ApiResponse<ReservaModel> response = new ApiResponse<>(reserva);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ReservaModel>> saveReserva(@RequestBody ReservaDTO dto){
        ReservaModel reserva = this.reservaService.saveReserva(dto);
        ApiResponse<ReservaModel> response =  new ApiResponse<>(reserva);
        return ResponseEntity.ok(response);
    }
    @PutMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<ReservaModel>> updateReserva(@RequestBody ReservaDTO request,
                                                                   @PathVariable("id") Long id){
        ReservaModel reserva = this.reservaService.updateReservaById(request, id);
        ApiResponse<ReservaModel> response = new ApiResponse<>(reserva);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<String>> deleteReserva(@PathVariable("id") Long id){
        @SuppressWarnings("unused")
        boolean respuesta = this.reservaService.deleteReservaById(id);
        ApiResponse<String> response = new ApiResponse<>("Se borro la reserva con id " + id);
        return ResponseEntity.ok(response);
    }
    @GetMapping(path = "/usuario/{idUsuario}")
    public ResponseEntity<ApiResponse<List<ReservaModel>>> getReservasByUsuario(@PathVariable("idUsuario") Long idUsuario){
        List<ReservaModel> reservas = this.reservaService.getReservasByUsuario(idUsuario);
        ApiResponse<List<ReservaModel>> response = new ApiResponse<>(reservas);
        return ResponseEntity.ok(response);
    }

    @PutMapping(path = "/{id}/confirmar")
    public ResponseEntity<ApiResponse<String>> confirmarReserva(@PathVariable("id") Long id) {
        boolean confirmada = this.reservaService.confirmarReserva(id);
        ApiResponse<String> response = new ApiResponse<>("Reserva con id " + id + " confirmada correctamente.");
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/usuario/{idUsuario}/historial")
    public ResponseEntity<ApiResponse<List<ReservaModel>>> getHistorialReservas(@PathVariable("idUsuario") Long idUsuario) {
        List<ReservaModel> reservas = reservaService.getHistorialReservasByUsuario(idUsuario);
        ApiResponse<List<ReservaModel>> response = new ApiResponse<>(reservas);
        return ResponseEntity.ok(response);
    }
}
