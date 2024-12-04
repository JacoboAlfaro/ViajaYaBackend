package com.viajaYa.viajaYa.controllers;


import com.viajaYa.viajaYa.models.ReservaModel;
import com.viajaYa.viajaYa.models.dtos.ReservaDTO;
import com.viajaYa.viajaYa.models.dtos.ReservaResponseDTO;
import com.viajaYa.viajaYa.services.interfaces.IReservaService;
import com.viajaYa.viajaYa.services.mappers.IMapper;
import com.viajaYa.viajaYa.utils.responses.ApiResponse;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reserva")
public class ReservaController {

    @Autowired
    private IReservaService reservaService; //[Aplicando Polimorfismo]
    @Autowired
    private IMapper<ReservaResponseDTO, ReservaModel> mapper; //[Aplicando Polimorfismo]

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<List<ReservaResponseDTO>>> getReservas(){
        List<ReservaModel> reservas = this.reservaService.getReservas();
        List<ReservaResponseDTO> reservasDto = reservas.stream().map(mapper::toDto).toList();
        ApiResponse<List<ReservaResponseDTO>> response = new ApiResponse<>(reservasDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<ReservaResponseDTO>> getReserva(@PathVariable("id") Long id){
        ReservaModel reserva = this.reservaService.getReservaById(id).get();
        ApiResponse<ReservaResponseDTO> response = new ApiResponse<>(mapper.toDto(reserva));
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<ReservaResponseDTO>> saveReserva(@RequestBody ReservaDTO dto){
        ReservaModel reserva = this.reservaService.saveReserva(dto);
        ApiResponse<ReservaResponseDTO> response =  new ApiResponse<>(mapper.toDto(reserva));
        return ResponseEntity.ok(response);
    }
    @PutMapping(path = "/{id}")
    @PreAuthorize("hasRole(@roles.ROLE_ADMIN)")
    public ResponseEntity<ApiResponse<ReservaResponseDTO>> updateReserva(@RequestBody ReservaDTO request,
                                                                   @PathVariable("id") Long id){
        ReservaModel reserva = this.reservaService.updateReservaById(request, id);
        ApiResponse<ReservaResponseDTO> response = new ApiResponse<>(mapper.toDto(reserva));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasRole(@roles.ROLE_ADMIN)")
    public ResponseEntity<ApiResponse<String>> deleteReserva(@PathVariable("id") Long id){
        @SuppressWarnings("unused")
        boolean respuesta = this.reservaService.deleteReservaById(id);
        ApiResponse<String> response = new ApiResponse<>("Se borro la reserva con id " + id);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/usuario/{idUsuario}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<List<ReservaResponseDTO>>> getReservasByUsuario(@PathVariable("idUsuario") Long idUsuario){
        List<ReservaModel> reservas = this.reservaService.getReservasByUsuario(idUsuario);
        List<ReservaResponseDTO> reservasDto = reservas.stream().map(mapper::toDto).toList();
        ApiResponse<List<ReservaResponseDTO>> response = new ApiResponse<>(reservasDto);
        return ResponseEntity.ok(response);
    }

    @PutMapping(path = "/{id}/confirmar")
    @PreAuthorize("hasRole(@roles.ROLE_ADMIN)")
    public ResponseEntity<ApiResponse<String>> confirmarReserva(@PathVariable("id") Long id) {
        boolean confirmada = this.reservaService.confirmarReserva(id);
        ApiResponse<String> response = new ApiResponse<>("Reserva con id " + id + " confirmada correctamente.");
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/usuario/{idUsuario}/historial")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<List<ReservaResponseDTO>>> getHistorialReservas(@PathVariable("idUsuario") Long idUsuario) {
        List<ReservaModel> reservas = reservaService.getHistorialReservasByUsuario(idUsuario);
        List<ReservaResponseDTO> reservasDto = reservas.stream().map(mapper::toDto).toList();
        ApiResponse<List<ReservaResponseDTO>> response = new ApiResponse<>(reservasDto);
        return ResponseEntity.ok(response);
    }
    @GetMapping(path = "{id}/total")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<List<Integer>>> getTotalReserva(@PathVariable("id") Long id){
        List<Integer> total = reservaService.calcularTotalPorReserva(id);
        ApiResponse<List<Integer>> response = new ApiResponse<>(total);
        return ResponseEntity.ok(response);
    }

}
