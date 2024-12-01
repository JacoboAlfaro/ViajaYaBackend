package com.viajaYa.viajaYa.controllers;

import com.viajaYa.viajaYa.models.FacturaModel;
import com.viajaYa.viajaYa.models.dtos.FacturaDTO;
import com.viajaYa.viajaYa.services.interfaces.IFacturaService;
import com.viajaYa.viajaYa.services.mappers.FacturaDTOMapper;
import com.viajaYa.viajaYa.utils.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/factura")
public class FacturaController {

    @Autowired
    IFacturaService facturaService;

    @Autowired
    FacturaDTOMapper mapper;

    @PostMapping
    @PreAuthorize("hasRole(@roles.ROLE_ADMIN)")
    public ResponseEntity<ApiResponse<FacturaDTO>> generateFactura(@RequestBody FacturaDTO dto) {
        FacturaDTO factura = facturaService.generateFactura(dto);
        ApiResponse<FacturaDTO> response = new ApiResponse<>(factura);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<List<FacturaDTO>>> getFacturas() {
        List<FacturaModel> facturas = this.facturaService.getFacturas();
        List<FacturaDTO> facturasDto = facturas.stream().map(mapper::toDto).toList();
        ApiResponse<List<FacturaDTO>> response = new ApiResponse<>(facturasDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<FacturaDTO>> getFacturaById(@PathVariable("id") Long id) {
        FacturaModel factura = this.facturaService.getFacturaById(id).get();
        FacturaDTO facturaDto = mapper.toDto(factura);
        ApiResponse<FacturaDTO> response = new ApiResponse<>(facturaDto);
        return ResponseEntity.ok(response);
    }
}
