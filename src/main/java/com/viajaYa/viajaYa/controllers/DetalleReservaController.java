package com.viajaYa.viajaYa.controllers;

import com.viajaYa.viajaYa.models.DetalleReservaModel;
import com.viajaYa.viajaYa.models.FacturaModel;
import com.viajaYa.viajaYa.models.ReservaModel;
import com.viajaYa.viajaYa.models.dtos.DetalleReservaDTO;
import com.viajaYa.viajaYa.models.dtos.FacturaDTO;
import com.viajaYa.viajaYa.models.dtos.ReservaDTO;
import com.viajaYa.viajaYa.models.dtos.ReservaResponseDTO;
import com.viajaYa.viajaYa.services.interfaces.IDetalleReservaService;
import com.viajaYa.viajaYa.services.mappers.IMapper;
import com.viajaYa.viajaYa.utils.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/detalleReserva")
public class DetalleReservaController {

    @Autowired
    private IDetalleReservaService detalleReservaService; //[Aplicando Polimorfismo]

    @Autowired
    private IMapper<DetalleReservaDTO, DetalleReservaModel> mapper;//[Aplicando Polimorfismo]


    @GetMapping(path = "/{idReserva}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<DetalleReservaDTO>> getDetalleReservaByReserva(@PathVariable("id") Long idReserva) {
        DetalleReservaModel detalleReserva = this.detalleReservaService.getDetalleReservaByReserva(idReserva);
        DetalleReservaDTO detalleDto = mapper.toDto(detalleReserva);
        ApiResponse<DetalleReservaDTO> response = new ApiResponse<>(detalleDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @PreAuthorize("hasRole(@roles.ROLE_ADMIN)")
    public ResponseEntity<ApiResponse<DetalleReservaDTO>> saveDetalleReserva(@RequestBody DetalleReservaDTO dto) {
        DetalleReservaModel detalleReserva = this.detalleReservaService.saveDetalleReserva(dto);
        ApiResponse<DetalleReservaDTO> response = new ApiResponse<>(mapper.toDto(detalleReserva));
        return ResponseEntity.ok(response);
    }
}
